

$(".editUserValue").editable("edit", {
	type : 'text',
	submitdata : {
		_method : "put"
	},
	submit : 'OK',
	cancel : 'cancel',
	style : 'max-width:100px;',
	cssclass : "editable"
});



$(document).ready( function(){
	$('.editUserSelect').editable('edit', {
		data : $('#roles').val(),
		type : 'multiselect',
		submit : 'OK', 
		style : 'max-width: 150px;'
	});

	$("input:submit", ".button").button();
	$(".button").button();
	$("#addUser").colorbox({
		href : "add",
		width : "75%"
	});
});