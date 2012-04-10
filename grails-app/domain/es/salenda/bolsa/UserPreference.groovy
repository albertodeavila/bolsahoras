package es.salenda.bolsa

class UserPreference {

	String name
	
	String value
	
	static belongsTo = {user : User}

    static constraints = {
    }
}
