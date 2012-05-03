package es.salenda.bolsa


class Movement {
	
	String jiraId
	
	String zendeskTicket
		
	Date updateDate
	
	String title
	
	Double timeSpent
	
	Project project
	
	Client client
	
	boolean manualMovement
	
	String issueType
	
	static mapping = {
		version false
	}
	
    static constraints = {
		jiraId nullable:true
		timeSpent blanck:false
		zendeskTicket nullable:true
		project nullable:true
		client nullable:true
		title blank:false
		issueType nullable:true
    }
}
