package es.salenda.bolsa

import wslite.http.*
import groovy.util.slurpersupport.GPathResult
import groovyx.net.http.*
import wslite.http.auth.*
import wslite.rest.*
import org.grails.plugins.settings.*
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_ADMIN'])
class FacturaDirectaService {

    def restClient
	
	def passwordManagerService
	
	def facturaDirecta
	
	/**
	* Method that request the data of the clients presents in FD configured by the settings
	* @return error if throws an exception or null if all is ok
	*/
	def loadCients(){
		def result
		facturaDirecta = facturaDirecta?: initFacturaDirectaConnection()
		if(facturaDirecta){
			try{
				def restResponse = facturaDirecta.get(path:'/clients.xml')
				def clients = new XmlSlurper().parseText(restResponse?.text)
				clients?.children()?.each { client ->			
					def cif = client.taxCode.text().toString()
					def c = Client.findByCif(cif)
					if(!c){
						c = new Client()
						c.name = client.name.text()
						c.cif = cif
						c.save(flush:true)
					}
				}
			}catch(Exception e){
				result = 'Error cargando los datos de los clientes de Factura Directa'
				log.error ("Error cargando los datos de los clientes de Factura Directa: ${e.getStackTrace()}")
			}
		}
		result
	}
	
	/**
	* Method that initialize the factura directa (FD) connection if it isn't 
	* @return the FD connection or null if error
	*/
	def initFacturaDirectaConnection(){
		def facturaDirectaUrl = Setting.findByCode('integration.facturaDirecta')?.value
		def facturaDirectaConnection
		if(facturaDirectaUrl){
			try{
				facturaDirectaConnection = new RESTClient("${facturaDirectaUrl}/api/")
				if(facturaDirectaConnection){
					def facturaDirectaUsername = Setting.findByCode('integration.facturaDirectaUsername')?.value
					def facturaDirectaPassword = passwordManagerService.retrieve('facturaDirectaPass', 'facturaDirectaPass')
					facturaDirectaConnection.authorization = new HTTPBasicAuthorization(facturaDirectaUsername, facturaDirectaPassword)
					facturaDirectaConnection.defaultContentTypeHeader = 'text/xml'
				}
			}catch(Exception e){
				facturaDirectaConnection = null
				log.error ("Error creando la conexi—n con Factura Directa: ${e.getStackTrace()}")
			}
		}
		return facturaDirectaConnection
	}
	
	
}
