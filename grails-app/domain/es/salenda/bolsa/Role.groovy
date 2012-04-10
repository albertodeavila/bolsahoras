package es.salenda.bolsa

class Role {

	String authority
	String name
	
	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
		name blanck: false, unique: true
	}
}
