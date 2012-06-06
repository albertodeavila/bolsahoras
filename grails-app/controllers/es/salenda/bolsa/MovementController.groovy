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
		def bag = Bag.findById(params.bag)
		if(bag){
			mov.bag = bag
		} 
		mov.manualMovement = true
		mov.save(flush: true)
		
		showMovements()
	}
	
	def add() {
		render (template: 'addMovement', model:[clientCif: params.clientCif, bagId:params.bag])
	}
	
	def subtract(){
		def movementToSubstract = Movement.findById(params.movementId)
		def timeSpentSum = calculateTimeToPayBack(movementToSubstract)
		if (timeSpentSum != 0){
			timeSpentSum = timeSpentSum / -3600
		}
		render (template: 'payBack', model:[clientCif: params.clientCif, movementId: params.movementId, timeCanSpent: timeSpentSum, bagId:params.bag])
	}
	
	def payBack(){
		def bag = Bag.findById(params.bag)
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
				if(bag){
					mov.bag = bag
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
	
	def showBags(){
		def client = Client.findByCif(params.clientCif)
		def bagsByUser = Bag.findAllByClient(client).sort{it.id}
		render (view: "listClients", model: [clients: Client.findAll(), clientSelected: client])
	}
	
	def showMovements(){
 		def offset= (params.offset) ? params.offset.toInteger() : 0
		def bag = Bag.findById(params.bagId)
		def client = Client.findByCif(params.clientCif)
		def bagsByUser = Bag.findAllByClient(client).sort{it.id}
		def movements = []
		def movementsSize
		def totalBalance = 0
		def accumulatedBalance = []
		if(bag){
			params.sort = "updateDate"
			params.order = "asc" 
			movements = Movement.findAllByBag(bag).sort{it.updateDate}
			movements.each { movement->
				totalBalance += movement.timeSpent
				accumulatedBalance.add(totalBalance / 3600)
			}
			totalBalance = totalBalance / 3600
			movementsSize = movements.size()
			movements = Movement.findAllByBag(bag, [max:10, offset:offset, sort:params.sort, order:params.order])
		}
		render (view: "listClients", model: [clients: Client.findAll(), movements: movements, offset: offset, bagSelected: bag,
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
	
	def moveToBag(){
		def client = Client.findByCif(params.clientCif)
		def bag = Bag.findById(params.bagId)
		def bagsByUser = Bag.findAllByClient(client).sort{it.id}
		bagsByUser.remove(bag)
		render (template: 'moveMovement', model:[clientCif: params.clientCif, bags: bagsByUser, bagId: params.bagId, movementId: params.movementId])
	}
	
	def move(){
		def mov = Movement.findById(params.movementId)
		def bag = Bag.findById(params.bagToMove)
		if(mov && bag){
			mov.bag = bag
			mov.manualMovement = true
			def project = mov.project
			if(project){
				project.removeFromMovements(mov)
				project.save(flush:true)
			}
			mov.save(flush:true)
		}
		showMovements()
	}
}
