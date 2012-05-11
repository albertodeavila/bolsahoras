package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_USER'])
class UserBagController {

	def springSecurityService
	
    def index() {
		showBags()
	}
	
	def showMovements(){
		def user = springSecurityService.currentUser
		def bags = []
		def offset= (params.offset) ? params.offset.toInteger() : 0
		def movements = []
		def movementsSize = 0
		def totalBalance = 0
		def accumulatedBalance = []
		def bag
		if(user.client){
			bags = Bag.findAllByClient(user.client).sort{it.id}
			bag = Bag.findById(params.bag)
			movements += Movement.findAllByBag(bag).reverse()
			movements.each { movement->
				totalBalance += movement.timeSpent
				accumulatedBalance.add(totalBalance / 3600)
			}
			totalBalance = totalBalance / 3600
			movementsSize = movements.size()
			int max = offset+10<movementsSize?offset+10:movements.size
				
			movements = movements.subList(offset, max).sort{it.updateDate}.reverse()
		}
		render (view: "listUserMovements", model: [clientSelected: user.client,bags: bags, bagSelected:bag, movements:movements, 
			movementCount:movementsSize, balance: totalBalance, offset: offset, accumulatedBalance: accumulatedBalance])
	}
	
	def showBags(){
		def user = springSecurityService.currentUser
		def bags = []
		if(user.client){
			bags = Bag.findAllByClient(user.client)
		}
		render (view: "listUserMovements", model: [clientSelected: user.client])
	}
}
