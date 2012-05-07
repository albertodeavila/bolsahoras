package es.salenda.bolsa

import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_USER'])
class PriceController {

    def index() {
		render (view: 'showPrice')	
	}
}
