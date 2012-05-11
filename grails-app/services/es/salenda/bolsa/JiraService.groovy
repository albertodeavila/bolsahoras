package es.salenda.bolsa

import wslite.http.*
import grails.converters.*

import wslite.http.auth.*
import wslite.rest.*
import org.grails.plugins.settings.*
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_ADMIN'])
class JiraService {

	def passwordManagerService
	def notificationService
	def jira
	def lowerLimitIssue = 0
	def topLimitIssue = 99999999
	// Possible values: *all, *navigate, names of fields separated by comma or preceded by - to exclude this field
	def fieldsIssue = '*all'

	/**
	 * Method that request the projects of the jira configured by the settings
	 * @return error if throws an exception or null if all is ok
	 */
	def loadProjects(){
		jira = jira?: initJiraConnection()
		def projects = []
		def restResponse
		def result 
		if(jira){
			try{
				restResponse = jira.get(path:'/project')
				projects = restResponse?.json
				projects?.each { project ->
					def key = project.get('key')
					def p = Project.findByKey(key)
					if(!p){
						p = new Project()
						p.key = project.get('key')
						p.name = project.get('name')
						p.save(flush: true)
					}
				}
			}catch(Exception e){
				log.error ("Error cargando los proyectos de JIRA: ${e.getStackTrace()}")
				result = 'error'
			}
		}
		result
	}

	/**
	* Method that request the worklogs of the jira configured by the settings
	* @return error if throws an exception or null if all is ok
	*/
	def loadWorklogs(){
		def result 
		jira = jira?: initJiraConnection()
		def zendeskLabel = Setting.findByCode('jiraZendesk')?.value

		def jql = Setting.findByCode('integration.jiraQuery')?.value
		def issuesByProject = []
		def restResponse
		if (jira) {
			try{
				if(jql){
					restResponse = jira.get(path: "/search?jql=${jql.encodeAsURL()}&maxResults=900000&${fieldsIssue}")
				}else{
					restResponse = jira.get(path: "/search?&maxResults=900000&${fieldsIssue}")
				}
				issuesByProject = restResponse?.json.get('issues')
				issuesByProject?.each {issue ->
					def m = Movement.findByJiraIdAndManualMovement(issue.get('key'), false)
					def projectKey = issue.get('fields').get('project').get('key')
					def project = Project.findByKey(projectKey)
					def totalWorklogInSeconds = issue.get('fields').get('timespent')
					if(totalWorklogInSeconds){
						def restWorklog = (totalWorklogInSeconds % 3600 )
						if(restWorklog!= 0){
							if(restWorklog <= 3600/2){
								totalWorklogInSeconds = totalWorklogInSeconds - restWorklog + 3600/2
							}else{
								totalWorklogInSeconds = totalWorklogInSeconds - restWorklog + 3600
							}
						}
						totalWorklogInSeconds = -1 * totalWorklogInSeconds
					}else{
						totalWorklogInSeconds = 0
					}
					
					if(m && totalWorklogInSeconds > m.timeSpent){
						project.removeFromMovements(m)
						project.save(flush:true)
						m.delete(flush:true)
					}
					if(!m){
						m = new Movement()
						m.jiraId = issue.get('key')
						if(zendeskLabel){
							m.zendeskTicket = issue.get('fields').get("${zendeskLabel}")
						}
						m.updateDate = Date.parse("yyyy-MM-dd", issue.get('fields').get('updated'))
						m.title = issue.get('fields').get('summary')
						m.issueType = issue.get('fields').get('issuetype').get('name')
						m.timeSpent = totalWorklogInSeconds
						m.project = project
						m.manualMovement = false
						if(project){
							if(project.bag){
								m.bag = project.bag
							}
							project.addToMovements(m)
							project.save(flush: true)
						}
						m.save(flush: true)
					}
				}
			}catch(Exception e){
				log.error ("Error cargando Worklogs de JIRA: ${e.getStackTrace()}")
				result = 'error'
			}
		}
		result
	}

	/**
	* Method that initialize the jira connection if it isn't 
	* @return the jira connection or null in case of error
	*/
	def initJiraConnection(){
		def jira
		def jiraUrl = Setting.findByCode('integration.jira')?.value
		if(jiraUrl){
			try{
				jira = new RESTClient("${jiraUrl}/rest/api/latest")
				if(jira){
					def jiraUsername = Setting.findByCode('integration.jiraUsername')?.value
					def jiraPassword = passwordManagerService.retrieve('jiraPass', 'jiraPass')
					jira.authorization = new HTTPBasicAuthorization(jiraUsername, jiraPassword)
					jira.defaultContentTypeHeader = 'application/json'
				}
			}catch(Exception e){
				log.error ("Error creando la conexi—n con JIRA: ${e.getStackTrace()}")
				jira = null
			}
		}
		return jira
	}
	
	
	def sendNotificationLimitHours(){
		def clients = Client.findAll()
		def bagLimit = Setting.findByCode("integration.bagLimit")?.value
		if(bagLimit){
			clients.each{ client->
				def movements = Movement.findAllByClient(client)
				def remaining = 0
				movements.each {movement->
					remaining += movement.timeSpent
				}
				remaining = remaining / 3600
				if(remaining <= bagLimit){
					def user = User.findByClient (client)
					notificationService.sendLimitBagNotification(user)
				}
			}
		}
	}
	
}
