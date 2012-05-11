package es.salenda.bolsa


class Client {
	
	String cif
	String name
	
	static hasMany = [bags: Bag]
	
    static constraints = {
		cif blank: false
		name blank: false
    }
	
	static mapping = {
		id name: 'cif'
        version false
        id generator: 'assigned'
	}
	
	def getFullInfo(){
		"${name} - ${cif}"
	}
	
}
