package es.salenda.bolsa


import org.grails.plugins.settings.*
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_ADMIN'])
class IntegrationController {
	
	/**
	 * Dependency injection for the simplePasswordManagerService.
	 */
	def simplePasswordManagerService

	def jiraService
	
	def facturaDirectaService
	
	
    def index() {
		def jira = Setting.findByCode("integration.jira")?.value
		def bagLimit = Setting.findByCode("integration.bagLimit")?.value
		def facturaDirecta = Setting.findByCode("integration.facturaDirecta")?.value
		def jiraUsername = Setting.findByCode("integration.jiraUsername")?.value
		def jiraPass = simplePasswordManagerService.retrieve("jiraPass", "jiraPass")?.replaceAll('.', '*')
		def jiraQuery = Setting.findByCode("integration.jiraQuery")?.value
		def jiraZendesk = Setting.findByCode("jiraZendesk")?.value
		def facturaDirectaUsername = Setting.findByCode("integration.facturaDirectaUsername")?.value
		def facturaDirectaPass = simplePasswordManagerService.retrieve("facturaDirectaPass", "facturaDirectaPass")?.replaceAll('.', '*')
		render (view: "integration", model: [jira : jira, facturaDirecta : facturaDirecta,
			 jiraUsername: jiraUsername, jiraPass : jiraPass, jiraQuery : jiraQuery, jiraZendesk : jiraZendesk,
			 facturaDirectaUsername : facturaDirectaUsername, facturaDirectaPass : facturaDirectaPass])	 
	}
	
	def edit = {
		def setting = Setting.findByCode("integration.${params.id}")
		if(params.id.contains("jira")){
			jiraService.jira = null
		}else if(params.id.contains("facturaDirecta")){
			facturaDirectaService.facturaDirecta = null
		}
		
		if(params.id.contains("Pass")){
			simplePasswordManagerService.store(params.id, params.value, params.id)
			render (params.value?.replaceAll('.', '*'))
		}else{
			if (!setting){
				setting = new Setting()
				setting.code = "integration.${params.id}"
				setting.type = "string"
			}
			setting.value = params.value
			setting.save(flush: true)
			render (params.value)
		}
	}
	
	
	def refreshJIRA = {
		def result = jiraService.loadProjects()
		def result2 = jiraService.loadWorklogs()
		
		if(!result && !result2){
			flash.message = "Se han cargado los proyectos y los worklogs de JIRA"
		}else{
			println "${result} \n ${result2}"
			flash.message = "Se ha producido un error al traer los datos de JIRA"
			flash.messageType="error"
		}
		index()
	}
	
	def refreshFacturaDirecta = {
		def result = facturaDirectaService.loadCients()
		if(!result){
			flash.message="Se han cargado los datos de los clientes de Factura Directa"
		}else{
			flash.message=result
			flash.messageType="error"
		}
		index()
	}
	
}
