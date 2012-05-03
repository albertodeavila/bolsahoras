

$(".editUserValue").editable("edit", {
	type : 'text',
	submitdata : {
		_method : "put"
	},
	style : 'width:100%;',
	cssclass : "editable"
});

$(".editValuePassword").editable("edit", {
	type : 'password',
	submitdata : {
		_method : "put"
	},
	cssclass : "editable"
});

$(document).ready( function(){
	$('.editUserSelect').editable('edit', {
		data : $('#roles').val(),
		type : 'multiselect',
		submit : 'OK', 
		style : 'width:100% !important;'
	});
	
	$('.editClientSelect').editable('edit', {
		data : $('#clients').val(),
		type : 'select',
		submit : 'OK', 
		style : 'max-width: 150px; position:relative; z-index: 1000;'
	});

	$("input:submit", ".button").button();
	$(".button").button();
	$("#addUser").colorbox({
		href : "add",
		width : "42%"
	});
});