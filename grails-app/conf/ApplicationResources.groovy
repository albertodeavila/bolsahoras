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
		
	}

		
	overrides {
		'jquery-theme' {
			resource id:'theme', url:'/css/blitzer/jquery-ui-1.8.18.custom.css'
		}
	}
}