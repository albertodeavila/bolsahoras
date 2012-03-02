import es.salenda.Role
import es.salenda.User
import es.salenda.UserRole

class BootStrap {

    def init = { servletContext ->
		
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
  
		def testUser = new User(username: 'alberto', enabled: true, password: '1234', name: 'Albero', surname: 'De Avila')
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
