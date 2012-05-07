package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_USER'])
class UserBagController {

	def springSecurityService
	
    def index() {
		showMovements()
	}
	
	def showMovements(){
		def user = springSecurityService.currentUser
		def offset= (params.offset) ? params.offset.toInteger() : 0
		def movements = []
		def movementsSize = 0
		def totalBalance = 0
		def accumulatedBalance = []
		if(user.client){
			movements += Movement.findAllByClient(user.client).reverse()
			movements.each { movement->
				totalBalance += movement.timeSpent
				accumulatedBalance.add(totalBalance / 3600)
			}
			totalBalance = totalBalance / 3600
			movementsSize = movements.size()
			int max = offset+10<movementsSize?offset+10:movements.size
				
			movements = movements.subList(offset, max).sort{it.updateDate}.reverse()
		}
		render (view: "listUserMovements", model: [clientSelected: user.client,movements:movements, movementCount:movementsSize,
			 balance: totalBalance, offset: offset, accumulatedBalance: accumulatedBalance])
	}
}
