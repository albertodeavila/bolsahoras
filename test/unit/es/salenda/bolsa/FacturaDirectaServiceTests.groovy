package es.salenda.bolsa

import grails.test.mixin.*

import org.grails.plugins.settings.Setting
import org.junit.*

@TestFor(FacturaDirectaService)
@Mock ([Setting, PasswordManagerService, EncryptedData, Client])
class FacturaDirectaServiceTests {

    void testLoadClients() {
		
		def setting = new Setting()
		setting.code = "integration.facturaDirecta"
		setting.type = "string"
		setting.value = "YOUR_FD_URL"
		setting.save()
		def setting2 = new Setting()
		setting2.code = "integration.facturaDirectaUsername"
		setting2.type = "string"
		setting2.value = "YOUR_FD_USERNAME"
		setting2.save()
		
		def passwordManagerService = new PasswordManagerService()
		passwordManagerService.store("facturaDirectaPass", "YOUR_FD_PASSWORD", "facturaDirectaPass")
		
		service.loadCients()
		
		assert Client.findAll().size() > 0
		
		service.facturaDirecta = null
		service.loadCients()
	}
}
