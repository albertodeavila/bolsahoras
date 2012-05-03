import org.grails.plugins.settings.Setting

import es.salenda.bolsa.PasswordManagerService
import es.salenda.bolsa.Role
import es.salenda.bolsa.User
import es.salenda.bolsa.UserRole


class BootStrap {
	
	def jiraService
	def facturaDirectaService
	
    def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN', name: 'Administrador').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER', name: 'Usuario').save(flush: true)
  
		def testUser = new User(username: 'alberto.deavila@salenda.es', enabled: true, password: '1234', name: 'Alberto', surname: 'De Avila')
		testUser.save(flush: true)
  
		println "${adminRole} - ${testUser} "
		
		UserRole.create testUser, adminRole, true
  
		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1
		
		def setting = new Setting()
		setting.code = "integration.facturaDirecta"
		setting.type = "string"
		setting.value = "https://bolsa.facturadirecta.com"
		setting.save()
		def setting2 = new Setting()
		setting2.code = "integration.facturaDirectaUsername"
		setting2.type = "string"
		setting2.value = "09fabd17179578742896c2248e2c1842"
		setting2.save()
		
		def passwordManagerService = new PasswordManagerService()
		passwordManagerService.store("facturaDirectaPass", "vamNecky13", "facturaDirectaPass")
		
		def jiraURL = new Setting()
		jiraURL.code = 'integration.jira'
		jiraURL.type = 'string'
		jiraURL.value = 'http://localhost:8090'
		jiraURL.save()
		
		def jiraUsername = new Setting()
		jiraUsername.code = 'integration.jiraUsername'
		jiraUsername.type = 'string'
		jiraUsername.value = 'alberto.deavila'
		jiraUsername.save()
		
		passwordManagerService.store('jiraPass', '1234', 'jiraPass')
		
		jiraService.loadProjects()
		jiraService.loadWorklogs()
		facturaDirectaService.loadCients()
    }
	
    def destroy = {
    }
}
