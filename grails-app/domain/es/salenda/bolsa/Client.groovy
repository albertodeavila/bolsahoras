package es.salenda.bolsa


class Client {
	
	String cif
	String name
	
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
	
	def getProjects(){
		def projects = Project.findAllByClient(this)
		projects
	}
}
