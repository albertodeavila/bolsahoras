package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN'])
class ProjectController {

    def index() {
		render (view: "assignProject", model: [projects: Project.findAllWhere(bag: null), clients: Client.findAll()])
	}
	
	def assign(){
		def result = 'Los proyectos han sido asignados correctamente'
		try{
			def allProjects = Project.findAll()
			allProjects.each {
				it.bag = null
				it.save(flush: true)
				Movement.findAllByProject(it).each { movement->
					movement.bag = null
					movement.save(flush: true)
				}
			}
			def bags = Bag.findAll()
			bags.each { bag->
				def client = bag.client
				def projects = params."${bag.id}"

				if(projects instanceof String){
					def project = Project.findByKey(projects)
					project.bag = bag
					Movement.findAllByProject(project).each { movement->
						movement.bag = bag
						movement.save(flush: true)
					}
					project.save(flush: true)
				}else{
					projects.each{
						def project = Project.findByKey(it)
						if(client){
							project.bag = bag
							Movement.findAllByProject(project).each { movement->
								movement.bag = bag
								movement.save(flush: true)
							}
							project.save(flush: true)
						}
					}
				}
			}
		}catch(Exception e){
			result = 'Se ha producido un error al asignar proyectos a clientes'	
			flash.messageType = 'error'
			println e.getStackTrace()
		}
		flash.message = result
		index()
	}
	
	def createBag(){
		def result
		def client = Client.findByCif(params.clientCif)
		def bag = new Bag(name:params.name, client:client)
		if(!bag.save(flush:true)){
			result = 'Se ha producido un error al crear una bolsa'
			flash.messageType = 'error'
		}else{
			result = 'Se ha creado correctamente la bolsa'
		}
		flash.message = result
		
		index()
	}
	
	def deleteBag(){
		def result
		def bag = Bag.findById(params.bag)
		def movements = Movement.findAllByBag(bag)
		movements.each { movement->
			movement.bag = null
		}
		bag.delete(flush:true)
		if(!bag.errors.allErrors.empty){
			result = 'Se ha producido un error al borrar una bolsa'
			flash.messageType = 'error'
		}else{
			result = 'Se ha borrado correctamente la bolsa'
		}
		flash.message = result
		index()
	}
	
	def addBag(){
		render (template: 'addBag', model:[clientCif: params.clientCif])
	}
}
