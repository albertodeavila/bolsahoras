package es.salenda.bolsa


class FacturaDirectaJob {
	
	def facturaDirectaService
	
	static triggers = {
		cron name: 'facturaDirectaJobTrigger', cronExpression: "0 0 6 * * ?"
	}

    def execute() {
		facturaDirectaService.loadCients()
	}
}
