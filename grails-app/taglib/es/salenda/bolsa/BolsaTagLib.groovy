package es.salenda.bolsa

class BolsaTagLib {
	
	def springSecurityService
	
	def nameUserLogged = { attrs, body ->
		out << body() << springSecurityService.getCurrentUser().name
	 }
}
