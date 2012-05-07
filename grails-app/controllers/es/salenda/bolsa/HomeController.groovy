package es.salenda.bolsa

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils


class HomeController {

	def springSecurityService
	
    def index() { 
		if (springSecurityService.isLoggedIn()) {
			def user = springSecurityService.getCurrentUser()
			session.name = user.name
			
			def adminRol = Role.findByAuthority('ROLE_ADMIN')
			def isAdmin = UserRole.get(user.id, adminRol.id)
			if(isAdmin){
				redirect controller: "integration"
			}else{
				redirect controller: "userBag"
			}
		}else{
			redirect controller: "login"
		}
	}
}
