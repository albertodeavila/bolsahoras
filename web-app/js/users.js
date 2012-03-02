
	 $(".editValue").editable("http://localhost:8080/bolsa/account/edit", { 
	  indicator : "<img src='images/icons/add.png'>",
	  type   : 'textarea',
	  submitdata: { _method: "put" },
	  submit : 'OK',
	  cancel : 'cancel',
	  cssclass : "editable"
	});
