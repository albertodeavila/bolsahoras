$(document).ready( function(){
//	Nifty("li.roundedBox","big");
//	Nifty("div.movements", "small");

//	Nifty("li#split h3","top");
//	Nifty("li#split div","bottom same-height");
	
	
	Nifty("div.roundedBox", "big");

	$(".button").button();
	$("#addMovement").colorbox({
		href : "add?clientCif=" + $("#clientCif").val(),
		width : "25%"
	});	
	
});

function showColorbox(id){
	  $.fn.colorbox({
		  width:"50%", 
		  href : "subtract?clientCif=" + $("#clientCif").val() + "&movementId=" + id,
	  });
}

