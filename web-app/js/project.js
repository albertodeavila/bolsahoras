$(document).ready( function(){
		$(".placeholder div").draggable({
			appendTo: "body",
			helper: "original", 
			revert: "invalid", 
			zIndex: 50000
		});
		$(".placeholder").droppable({
			activeClass: "ui-state-custom",
			hoverClass: "ui-state-hover",
			accept: ":not(.ui-sortable-helper)",
			drop: function( event, ui ) {
				$("#" + ui.draggable.attr('id')).remove();
				$("input[value='" + ui.draggable.attr('id') + "']").remove();
				$( "<div id='" + ui.draggable.attr('id') + "' class='roundedBox paddingLeft' onmouseover='cursorOver(this)' ondrag='cursorClick(this)'></div>")
				.text( ui.draggable.text() ).appendTo( this ).draggable({
					appendTo: "body",
					helper: "original", 
					revert: "invalid"
				});
				Nifty("div.roundedBox","small");
				var parent = $("#" + ui.draggable.attr('id')).parent();
				$("<input type='hidden' name='" + parent.attr('id') + "' value='" + ui.draggable.attr('id') + "'/>").appendTo(this);
			}
		});
		$(".projectsByClient").accordion({
			collapsible: true,
			active: false 
		});
		Nifty("div.roundedBox","small");
		

		$("#projectsWithoutClient").accordion({
			collapsible: true
		});
});

function cursorClick(div){
	$("#" + div.id + "").css('cursor', '-moz-grabbing');
};

function cursorOver(div){
	$("#" + div.id + "").css('cursor', 'move');
};

function showColorbox(id){
	  $.fn.colorbox({
		  width:"50%", 
		  href : "addBag?clientCif=" + id
	  });
};
