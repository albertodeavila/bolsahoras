import org.grails.plugins.settings.Setting

import es.salenda.bolsa.PasswordManagerService
import es.salenda.bolsa.Role
import es.salenda.bolsa.User
import es.salenda.bolsa.UserRole


class BootStrap {
	
	def jiraService
	def facturaDirectaService
	
    def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN', name: 'Administrador').save([flush: true, failOnError: true])
		def userRole = new Role(authority: 'ROLE_USER', name: 'Usuario').save([flush: true, failOnError: true])
  
		def testUser = new User(username: 'your@mail.com', enabled: true, password: '1234', name: 'name', surname: 'surname')
		testUser.save(flush: true)
  
		println "${adminRole} - ${testUser} "
		
		UserRole.create testUser, adminRole, true
  
		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1
		
		def setting = new Setting()
		setting.code = "integration.facturaDirecta"
		setting.type = "string"
		setting.value = "YOUR_FACTURADIRECTA_URL"
		setting.save()
		def setting2 = new Setting()
		setting2.code = "integration.facturaDirectaUsername"
		setting2.type = "string"
		setting2.value = "YOUR_FACTURADIRECTA_USERNAME"
		setting2.save()
		
		def passwordManagerService = new PasswordManagerService()
		passwordManagerService.store("facturaDirectaPass", "YOUR_FACTURADIRECTA_PASS", "facturaDirectaPass")
		
		def jiraURL = new Setting()
		jiraURL.code = 'integration.jira'
		jiraURL.type = 'string'
		jiraURL.value = 'YOUR_JIRA_URL'
		jiraURL.save()
		
		def jiraUsername = new Setting()
		jiraUsername.code = 'integration.jiraUsername'
		jiraUsername.type = 'string'
		jiraUsername.value = 'YOUR_JIRA_USERNAME'
		jiraUsername.save()
		
		def jiraQuery = new Setting()
		jiraQuery.code = 'integration.jiraQuery'
		jiraQuery.type = 'string'
		jiraQuery.value = 'resolved >= -1d and resolution is not EMPTY AND status = Terminada'
		jiraQuery.save()
		
		passwordManagerService.store('jiraPass', 'YOUR_JIRA_PASS', 'jiraPass')
		
		jiraService.loadProjects()
		jiraService.loadWorklogs()
		facturaDirectaService.loadCients()
    }
	
    def destroy = {
    }
}
