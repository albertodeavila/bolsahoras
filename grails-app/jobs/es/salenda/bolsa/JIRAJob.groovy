package es.salenda.bolsa


class JIRAJob {
	
    def jiraService
	
	static triggers = {
		cron name: 'JIRAJobTrigger', cronExpression: "0 10 6 * * ?"
	}

    def execute() {
		jiraService.loadProjects()
		jiraService.loadWorklogs()
		jiraService.sendNotificationLimitHours()
	}
}
