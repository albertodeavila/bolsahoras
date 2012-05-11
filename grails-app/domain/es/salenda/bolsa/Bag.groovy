package es.salenda.bolsa

class Bag {

	String name
	Client client
	
	static hasMany = [projects: Project]
	
    static constraints = {
		name blank:false
		client nullable:true
    }
	
	static mapping = {
		sort 'id'
	}
}
