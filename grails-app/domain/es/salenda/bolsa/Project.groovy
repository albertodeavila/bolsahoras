package es.salenda.bolsa


class Project {

	String key
	
	String name
	
	static belongsTo = {client : User}
	
	static hasMany = {movements : Movement}
	
    static constraints = {
		key blank: false, unique: true
		name blank: false
    }
	
	static mapping = {
		id name: 'key'
	}
}
