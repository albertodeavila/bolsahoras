package es.salenda.bolsa

class User {
	transient springSecurityService

	String username
	String password
	String name
	String surname
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Client client
	
	static hasMany = [bags: Bag]

	static constraints = {
		username email:true, blank: false, unique: true
		password blank: false
		name blank: false
		surname blank: false
		client nullable: true 
	}

	static mapping = {
		password column: '`password`'
		bags cascade: 'all-delete-orphan'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}
	
	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
	
}
