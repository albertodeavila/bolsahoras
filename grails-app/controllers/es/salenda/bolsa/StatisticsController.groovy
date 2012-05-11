package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured
@Secured(['ROLE_USER'])
class StatisticsController {

	def springSecurityService
	
    def index() { 
		def user = springSecurityService.currentUser
		def statisticsPie = [:]
		def statisticsPieList = []
		def statisticsColumn = [:]
		def statisticsColumnList = []
		def issueTypes = []
		def pieGraphicName = 'Incidencias por tipo'
		def pieColumnName = 'Tipo de incidencia'
		def pieColumnData = 'Numero de incidencias'
		if(user.client){
			def bags = Bag.findAllByClient(user.client)
			def movements = []
			bags.each { bag->
				movements += Movement.findAllByBagAndManualMovement(bag, false)
			}
		
			
			statisticsColumn."De 0 a 4 horas" = 0
			statisticsColumn."De 4 a 8 horas" = 0
			statisticsColumn."De 8 a 16 horas" = 0
			statisticsColumn."+ 16 horas" = 0
			
			movements.each { movement->
				if(statisticsPie.containsKey(movement.issueType)){
					statisticsPie."${movement.issueType}"++
				}else{
					statisticsPie."${movement.issueType}" = 1
				}
				
				if(!issueTypes.contains("'${movement.issueType}'")){
					issueTypes.add("'${movement.issueType}'")
				}
				
				if(movement.timeSpent > -4*3600){
						statisticsColumn."De 0 a 4 horas"++
				}else if(movement.timeSpent > -8*3600){
						statisticsColumn."De 4 a 8 horas"++
				}else if(movement.timeSpent > -16*3600){
						statisticsColumn."De 8 a 16 horas"++
				}else{
						statisticsColumn."+ 16 horas"++
				}
			}
			statisticsPie.each {
				statisticsPieList.add "'${it.key}:${it.value}'" 
			}
			
			statisticsColumn.each {
				statisticsColumnList.add "'${it.key}:${it.value}'"
			}			
		}
		render (view: "showStatistics", model: [columnName: pieColumnName, columnData: pieColumnData, statistics: statisticsPieList,
			 graphicsName: pieGraphicName, statisticsColumn: statisticsColumnList, issueTypes: issueTypes])
	}
}
