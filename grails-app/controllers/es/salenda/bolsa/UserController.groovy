package es.salenda.bolsa

import grails.plugins.springsecurity.Secured
import groovy.json.JsonBuilder

@Secured(['ROLE_ADMIN'])
class UserController {

    def index() { 
		def roles = [:]
		Role.findAll().unique().each {
			roles.put(it.id, it.name)
		}
	
		def rolesJson = new JsonBuilder()
		rolesJson.call(roles)
		render (view: "listUsers", model: [users: User.findAll(), roles: rolesJson])
	}
	
	
	def edit = {
		def userAttribute = params.id.split('_')[0]
		def userId = params.id.split('_')[1]
		def user = User.findById(userId)
		if(userAttribute == "roles"){
			def values = params.get("value[]")
			
			UserRole.removeAll(user)
			values.each{
				UserRole.create (user, Role.findById(it), true)
			}
			render user.authorities*.name.join(', ')
		}else{
			user."${userAttribute}" = params.value
			user.save(flush: true)
			render (params.value)
		}
		
	}
	
	def create = {
		def user = new User(username: params.email, enabled: true, password: params.password, name: params.name, surname: params.surname)
		user.save(flush: true)
		params.userRoles.each{
			UserRole.create user, Role.findById(it), true
		}
		index()
	}
	
	def delete = {
		def user = User.findById(params.userId)
		
		UserRole.removeAll user
		user.delete(flush: true)
		index()
	}
	
	def add = {
		render (template: 'addUser', model:[userRoles: Role.findAll()])
	}
	
}
