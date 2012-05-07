package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured
@Secured(['ROLE_USER'])
class StatisticsController {

	def springSecurityService
	
    def index() { 
		def user = springSecurityService.currentUser
		def offset= (params.offset) ? params.offset.toInteger() : 0
		def statistics = [:]
		def statisticsList = []
		def graphicsName = 'Incidencias por tipo'
		def columnName = 'Tipo de incidencia'
		def columnData = 'Numero de incidencias'
		if(user.client){
			def movements = Movement.findAllByClientAndManualMovement(user.client, false)
			movements.each { movement->
				if(statistics.containsKey(movement.issueType)){
					statistics."${movement.issueType}"++
				}else{
					statistics."${movement.issueType}" = 1
				}
			}
			statistics.each {
				statisticsList.add("'${it.key}:${it.value}'")
			}
		}
		render (view: "showStatistics", model: [columnName: columnName, columnData: columnData, statistics: statisticsList, graphicsName: graphicsName])
	}
}
