package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN'])
class ProjectController {

    def index() {
		render (view: "assignProject", model: [projects: Project.findAllWhere(client: null), clients: Client.findAll()])
	}
	
	def assign(){
		def result = 'Los proyectos han sido asignados correctamente'
		try{
			def allProjects = Project.findAll()
			allProjects.each {
				it.client = null
				it.save(flush: true)
				Movement.findAllByProject(it).each { movement->
					movement.client = null
					movement.save(flush: true)
				}
			}
			def clients = Client.findAll()
			clients.each {
				def cif = it.cif
				def projects = params."${cif}"

				if(projects instanceof String){
					def project = Project.findByKey(projects)
					def client = Client.findByCif(cif)
					if(client){
						project.client = client
						Movement.findAllByProject(project).each { movement->
							movement.client = client
							movement.save(flush: true)
						}
						project.save(flush: true)
					}
				}else{
					projects.each{
						def project = Project.findByKey(it)
						def client = Client.findByCif(cif)
						if(client){
							project.client = client
							Movement.findAllByProject(project).each { movement->
								movement.client = client
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
		}
		flash.message = result
		index()
	}
}
