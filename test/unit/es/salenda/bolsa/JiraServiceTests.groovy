package es.salenda.bolsa

import org.grails.plugins.settings.Setting


import es.salenda.bolsa.JiraService;
import es.salenda.bolsa.PasswordManagerService;
import grails.test.mixin.*


@TestFor(JiraService)
@Mock ([Setting, PasswordManagerService, EncryptedData, Project, Movement])
class JiraServiceTests {

	void testLoadProjects() {
		
		 def jiraURL = new Setting()
		 jiraURL.code = 'integration.jira'
		 jiraURL.type = 'string'
		 jiraURL.value = 'http://localhost:8090'
		 jiraURL.save()
		 
		 def jiraUsername = new Setting()
		 jiraUsername.code = 'integration.jiraUsername'
		 jiraUsername.type = 'string'
		 jiraUsername.value = 'your_username'
		 jiraUsername.save()
		 
		 def passwordManagerService = new PasswordManagerService()
		 passwordManagerService.store('jiraPass', '1234', 'jiraPass')
		 
		 service.loadProjects()
		 assert Project.findAll().size() > 0
		 service.loadWorklogs()
		 
	}
	
}
