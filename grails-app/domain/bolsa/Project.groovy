package bolsa

class Project {

	String name
	
	String key
	
	static belongsTo = {client : User}
	
	static hasMany = {issues : Movement}
	
    static constraints = {
    }
}
