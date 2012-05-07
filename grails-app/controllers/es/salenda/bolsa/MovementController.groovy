package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN'])
class MovementController {

    def index() {
		render (view: "listClients", model: [clients: Client.findAll()])
	}
	
	def create() {
		def mov = new Movement()
		mov.jiraId = params.JIRAKey?: null
		mov.zendeskTicket = params.ZDTicket?: null
		mov.updateDate = Date.parse("dd/MM/yyyy", params.date)
		mov.title = params.title
		mov.timeSpent = Double.parseDouble(params.hours) * 3600
		def client = Client.findByCif(params.clientCif)
		if(client){
			mov.client = client
		} 
		mov.manualMovement = true
		mov.save(flush: true)
		
		showMovements()
	}
	
	def add() {
		render (template: 'addMovement', model:[clientCif: params.clientCif])
	}
	
	def subtract(){
		def movementToSubstract = Movement.findById(params.movementId)
		def timeSpentSum = calculateTimeToPayBack(movementToSubstract)
		if (timeSpentSum != 0){
			timeSpentSum = timeSpentSum / -3600
		}
		render (template: 'payBack', model:[clientCif: params.clientCif, movementId: params.movementId, timeCanSpent: timeSpentSum])
	}
	
	def payBack(){
		def client = Client.findByCif(params.clientCif)
		def timeSpent = Double.parseDouble(params.payBackHours) * 3600
		if(timeSpent > 0){
			def movementRefferred = Movement.findById(params.movementId)
			
			def timeSpentSum = calculateTimeToPayBack(movementRefferred)
			
			if((timeSpentSum + timeSpent) <= 0){
				def mov = new Movement()
				mov.updateDate = new Date()
				mov.jiraId = movementRefferred.jiraId
				mov.title = "Reembolso de ${movementRefferred.jiraId}"
				mov.timeSpent = timeSpent
				mov.manualMovement = true
				if(client){
					mov.client = client
				}
				mov.save(flush: true)
				
				flash.message = "Se han reembolsado las horas correspondientes"
			}else{
				flash.messageType = 'error'
				flash.message = "No se permite reembolsar m&aacute;s horas de las que una tarea ha gastado"
			}
		}else{
			flash.messageType = 'error'
			flash.message = "Debe indicar un reembolso de horas positivo"
		}
		showMovements()
	}
	
	def showMovements(){
 		def offset= (params.offset) ? params.offset.toInteger() : 0
		def client = Client.findByCif(params.clientCif)
		def movements = []
		def movementsSize
		def totalBalance = 0
		def accumulatedBalance = []
		if(client){
			params.sort = params.sort ?: "updateDate"
			params.order = params.order ?: "desc" 
			movements = Movement.findAllByClient(client).sort{it.updateDate}.reverse()
			movements.each { movement->
				totalBalance += movement.timeSpent
				accumulatedBalance.add(totalBalance / 3600)
			}
			totalBalance = totalBalance / 3600
			movementsSize = movements.size()
			movements = Movement.findAllByClient(client, [max:10, offset:offset, sort:params.sort, order:params.order])
		}
		render (view: "listClients", model: [clients: Client.findAll(), movements: movements, offset: offset,
			clientSelected: client, movementCount: movementsSize, balance: totalBalance, accumulatedBalance: accumulatedBalance])
	}
	
	private def calculateTimeToPayBack(def movement){
		def payBacks = Movement.findAllByJiraId(movement.jiraId)
		def timeSpentSum = movement.manualMovement? movement.timeSpent : 0
		payBacks.each { mov->
			timeSpentSum += mov.timeSpent
		}
		timeSpentSum
	}
}
