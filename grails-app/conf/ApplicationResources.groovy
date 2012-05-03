modules = {
    bolsa {
        dependsOn 'jquery'	
		resource url:'/css/colorbox.css'
		resource url:'/css/general.css'
		resource url:'/css/login.css'
		resource url:'/js/users.js'
		resource url:'/js/jquery.colorbox.js', disposition:'head'
		resource url:'/js/jeditable.js', disposition:'head'
		resource url:'/js/menu.js'
		
    }
	buttons{
		dependsOn 'jquery-ui'
	}
	
	integration{
		resource url:'/js/integration.js'
		resource url:'/css/general.css'
	}

	roundedBox{
		resource url:'/js/roundedBox.js'
		resource url:'/js/niftycube.js'
		resource url:'/css/niftyCorners.css'
	}	
	
	overrides {
		'jquery-theme' {
			resource id:'theme', url:'/css/blitzer/jquery-ui-1.8.18.custom.css'
		}
	}
	
	projects {
		resource url:'/js/project.js'
		dependsOn 'jquery-ui'
		resource url:'/js/niftycube.js'
		resource url:'/css/niftyCorners.css'
	}
	
	movements{
		resource url:'/js/movements.js'
	}
	
	tipsy{
		resource url:'/js/tipsy.js'
		resource url:'/css/tipsy.css'
	}
	
	tooltip{
		resource url:'/js/tooltip.js'
	}
}