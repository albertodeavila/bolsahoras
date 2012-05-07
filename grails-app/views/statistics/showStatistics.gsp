
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title> <g:message code="clients"/> </title>
	</head>
	<body>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		    <script type="text/javascript">
		    
		      // Load the Visualization API and the barchart package.
		      google.load('visualization', '1.0', {'packages':['corechart']});
		      
		      // Set a callback to run when the Google Visualization API is loaded.
		      google.setOnLoadCallback(drawVisualization);
		      
		      function drawVisualization() {
		        var data = new  google.visualization.DataTable();
		        data.addColumn('string', "${columnName}");
		        data.addColumn('number', "${columnData}");
		        var array = ${statistics};
		        var i;
				for(i=0;i<array.length;i++){
					var aux = array[i].split(':');
					data.addRow([aux[0], parseInt(aux[1])]);
			    }
		        
		      // Create and draw the visualization.
		        new google.visualization.PieChart($('#issuesByType')[0]).draw(data,
		                 {title:"${graphicsName}"});
		      }
		      
		    </script>
		<div id="results" class="divChart" align="center">
		<h1><g:message code="statistics"/></h1>
		    <!--Div that will hold the pie chart-->
		    <g:if test="${statistics.size()>0}">
   			    <div class="chart" id="issuesByType"></div>
		    </g:if>
		    <g:else>
		    	<div id="warningBox" class="roundedBox">
					<g:message code="movement.noMovements"/>
				</div>
		    </g:else>
		</div>

	</body>
</html>