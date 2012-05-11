package es.salenda.bolsa

class BagTagLib {
	
	def springSecurityService
	
	def nameUserLogged = { attrs, body ->
		out << body() << springSecurityService.getCurrentUser().name
	 }
}
