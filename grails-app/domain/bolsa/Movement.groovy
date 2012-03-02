package bolsa

class Movement {
	
	String id
	
	String zendeskTicket
	
	Date creation
	
	Date closeDate
	
	String description
	
	Double timeSpent
	
	static belongsTo = {project : Project}
	
	
    static constraints = {
    }
}
