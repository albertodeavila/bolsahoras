package es.salenda.bolsa

class NotificationService {

	def mailService
	def passwordManagerService
	
    def sendNewUserNotification(User user, def pass){
		try{
			mailService.sendMail {
				to user.username
				subject "Nuevo usuario en Bolsa de horas"
				body (view:"/email/newUserMail", model:[user: user, pass: pass])
			}
		}catch(Exception e){
			log.error(e)
		}
	}
	
	def sendLimitBagNotification(User user){
		try{	
			mailService.sendMail {
				to user.username
				subject "Bolsa de horas de Salenda a punto de acabar"
				body (view:"/email/limitBagMail", model:[user:user])
	
			}
		}catch(Exception e){
			log.error(e)
		}
	}
}
