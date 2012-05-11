package es.salenda.bolsa


class Project {

	String key
	String name
	Bag bag
	
	static hasMany = [movements: Movement]
		
    static constraints = {
		key blank: false
		name blank: false
		bag nullable: true
    }
	
	static mapping = {
		id name: 'key'
        version false
        id generator: 'assigned'
	}
}
