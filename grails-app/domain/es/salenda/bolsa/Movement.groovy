package es.salenda.bolsa


class Movement {
	
	String jiraId
	
	String zendeskTicket
	
	Date creation
	
	Date updateDate
	
	String description
	
	Double timeSpent
	
	static belongsTo = {project : Project}
	
	
    static constraints = {
		jiraId blank:false, unique:true
		timeSpent blanck:false
    }
}
