package es.salenda.bolsa


class Project {

	String key
	String name
	Client client
	
	static hasMany = [movements: Movement]
		
    static constraints = {
		key blank: false
		name blank: false
		client nullable: true
    }
	
	static mapping = {
		id name: 'key'
        version false
        id generator: 'assigned'
	}
}
