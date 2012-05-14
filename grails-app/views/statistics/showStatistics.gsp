<%! import grails.converters.JSON %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title> <g:message code="clients"/> </title>
		<r:require module="roundedBox"/>
	</head>
	<body>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	    <script type="text/javascript">
	    
	      // Load the Visualization API and the barchart package.
	      google.load('visualization', '1.0', {'packages':['corechart']});
	      
	      // Set a callback to run when the Google Visualization API is loaded.
	      google.setOnLoadCallback(drawVisualization);
	      
	      function drawVisualization() {
	        var data1 = new  google.visualization.DataTable();
	        data1.addColumn('string', "${columnName}");
	        data1.addColumn('number', "${columnData}");
	        var array = ${statistics};
	        var i;
			for(i=0;i<array.length;i++){
				var aux = array[i].split(':');
				data1.addRow([aux[0], parseInt(aux[1])]);
		    }
	        
			var data2 = new  google.visualization.DataTable();
			data2.addColumn('string', "${columnName}");
			data2.addColumn('number', "${columnData}");
			array = ${statisticsColumn};
			for(i=0;i<array.length;i++){
				var aux = array[i].split(':');
				data2.addRow([aux[0], parseInt(aux[1])]);
		    }


	    // Create and draw the visualization.
	        new google.visualization.PieChart($('#issuesByType')[0]).draw(data1,
	                 {title:"${graphicsName}", width:800, height:400,});

            
               // Create and draw the visualization.
           new google.visualization.ColumnChart($('#hoursByIssue')[0]).draw(data2,
                    {title:"Tiempo de resolución de incidencias",
        	   width:800, height:400, colors:['#e0440e']}
               );
	      }
	    </script>
		<div id="results" class="divChart" align="center">
		<h1><g:message code="statistics"/></h1>
		    <!--Div that will hold the pie chart-->
		    <g:if test="${statistics.size()>0}">
   			    <div class="chart" id="issuesByType"></div>
   			    <div class="chart" id="hoursByIssue"></div>
		    </g:if>
		    <g:else>
		    	<div id="warningBox" class="roundedBox">
					<g:message code="movement.noMovements"/>
				</div>
		    </g:else>
		</div>

	</body>
</html>