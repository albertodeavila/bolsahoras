$(document).ready( function(){
	Nifty("div.roundedBox", "big");

	$(".button").button();
	$("#addMovement").colorbox({
		href : "add?clientCif=" + $("#clientCif").val() + "&bag=" + $("#bag").val(),
		width : "25%"
	});	
	
});

function showColorboxPayback(id){
	  $.fn.colorbox({
		  width:"50%", 
		  href : "subtract?clientCif=" + $("#clientCif").val() + "&movementId=" + id + "&bagId=" + $("#bagId").val(),
	  });
}

function showColorboxMove(id){
	  $.fn.colorbox({
		  width:"50%", 
		  href : "moveToBag?clientCif=" + $("#clientCif").val() + "&movementId=" + id + "&bagId=" + $("#bagId").val(),
	  });
}

