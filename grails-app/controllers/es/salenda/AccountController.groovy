package es.salenda

import grails.plugins.springsecurity.Secured
import es.salenda.User

class AccountController {

	@Secured(['ROLE_ADMIN'])
    def index() { 
		render (view: "listAccounts", model: [users: User.findAll()])
	}
	
	def edit = {
		//Value es el valor cambiado, e id el nombre del div
		println "${params.value} + ${params.id}"
		def userAttribute = params.id.split('_')[0]
		def userId = params.id.split('_')[1]
		User user = User.findById(userId)
		user."${userAttribute}" = params.value
		render (params.value)
	}
	
}
