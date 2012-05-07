$(".editIntegrationURL").editable("edit", {
	type : 'text',
	submitdata : {
		_method : "put"
	},
	cssclass : "editable", 
	style : "width:100%;"
});

$(".editIntegrationUsername").editable("edit", {
	type : 'text',
	submitdata : {
		_method : "put"
	},
	cssclass : "editable"
});

$(".editIntegrationValuePassword").editable("edit", {
	type : 'password',
	submitdata : {
		_method : "put"
	},
	cssclass : "editable"
});



