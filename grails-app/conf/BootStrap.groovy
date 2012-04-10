import es.salenda.bolsa.Role
import es.salenda.bolsa.User
import es.salenda.bolsa.UserRole


class BootStrap {
	
	
    def init = { servletContext ->
		
		def adminRole = new Role(authority: 'ROLE_ADMIN', name: 'Administrador').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER', name: 'Usuario').save(flush: true)
  
		def testUser = new User(username: 'alberto', enabled: true, password: '1234', name: 'Alberto', surname: 'De Avila')
		testUser.save(flush: true)
  
		println "${adminRole} - ${testUser} "
		
		UserRole.create testUser, adminRole, true
  
		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1
		
		
		
		
    }
    def destroy = {
    }
}
