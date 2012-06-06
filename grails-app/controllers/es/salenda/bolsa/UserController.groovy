package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured

import groovy.json.JsonBuilder

@Secured(['ROLE_ADMIN'])
class UserController {

	def notificationService
	def springSecurityService
	
    def index() { 
		show()
	}
	
	def show(){
		def offset= (params.offset) ? params.offset.toInteger() : 0
		
		def roles = [:]
		Role.findAll().unique().each {
			roles.put(it.id, it.name)
		}
		
		def clients = [:]
		clients.'0' = '- Sin cliente -'
		Client.findAll().unique().each {
			clients."${it.cif}" = it.name
		}
		
		def rolesJson = new JsonBuilder()
		rolesJson.call(roles)
		def clientJson = new JsonBuilder()
		clientJson.call(clients)

		params.sort = params.sort ?: "name"
		params.order = params.order ?: "desc"
		render (view: "listUsers", model: [users: User.list(max:10, offset:offset, sort:params.sort, order:params.order), roles: rolesJson, clients: clientJson, userTotal: User.count])
	}
	
	
	def edit (){
		def userAttribute = params.id.split('_')[0]
		def userId = params.id.split('_')[1]
		def user = User.findById(userId)
		if(userAttribute == "roles"){
			def roles = params.get("value[]")
			
			UserRole.removeAll(user)
			roles.each{
				UserRole.create (user, Role.findById(it), true)
			}
			render user.authorities*.name.join(', ')
		}else if(userAttribute == "password"){
			user."${userAttribute}" = params.value
			user.save(flush: true)
			render (params.value.replaceAll('.', '*'))
		}else if(userAttribute == "client"){
			if(params.value == '0'){
				user.client = null
				user.save(flush: true)
				render ''
			}else{
				user.client = Client.findByCif(params.value)
				user.save(flush: true)
				render (user.client?.name)
			}
		}else{
			user."${userAttribute}" = params.value
			user.save(flush: true)
			render (params.value)
		}
		
	}
	
	def create (){
		def client = Client.findByCif(params.client)
		def user = new User(username: params.email, enabled: true, password: params.password, name: params.name, surname: params.surname, client: client)
		user.save(flush: true)
		
		if(!user.errors.allErrors.empty){
			flash.messageType = 'error'
			flash.message = 'No se ha creado el usuario'
		}else{
			params.userRoles.each{
				UserRole.create user, Role.findById(it.value.toString()), true
			}
			flash.message = 'Usuario creado correctamente'
			notificationService.sendNewUserNotification(user, params.password)
		}
		show()
	}
	
	def delete (){
		def user = User.findById(params.userId)
		def me = false
		if(user == springSecurityService.currentUser){
			me = true
		}
		UserRole.removeAll user
		user.delete(flush: true)
		if(!user.errors.allErrors.empty){
			flash.messageType = 'error'
			flash.message = 'No se ha podido borrado el usuario'
		}else{
			flash.message = 'Usuario borrado correctamente'
		}
		if(me){
			redirect (controller: 'logout')
		}else{
			index()
		}
	}
	
	def add (){
		render (template: 'addUser', model:[userRoles: Role.findAll(), clients: Client.findAll()])
	}
	
	def bags(){
		def user = User.findById(params.userId)
		def bags = []
		if(user.client){
			bags = Bag.findAllByClient(user.client)
		}
		render (template: 'bagVisible', model:[bags:bags, user:user])
	}
	
	def editBagVisible(){
		def bags = params.bagSelected
		def user = User.findById(params.userId)
		def adminRol = Role.findByAuthority('ROLE_USER')
		def isUser = UserRole.get(user.id, adminRol.id)
		if(isUser){
			user.bags.clear()
			
			if(params.bagSelected.contains("0")){
				def allBags = Bag.findAllByClient(user.client)
				allBags.each {bag->
					user.addToBags (bag)
				}
			}else{
				bags.each {bagId->
					def bag = Bag.findById(bagId)
					user.addToBags(bag)
				}
			}
			user.save(flush: true)
			flash.message = 'Se han guardado correctamente las bolsas que puede ver el usuario'
		}else{
			flash.messageType = 'error'
			flash.message = 'El usuario debe tener rol de cliente'
		}
		show()
	}
}
