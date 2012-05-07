<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title> <g:message code="clients"/> </title>
		<r:require module="roundedBox"/>
	</head>
	<body>
		<div class="width100">
			<div class="movements marginTop10">
				<div class="roundedBox">
					<h3 class="jira title">
						<g:message code="movements"/>
					</h3>
					<div class="margin40">
						<g:if test="${clientSelected}">
							<g:render template="../includes/listMovements" model="[movements:movements, movementCount:movementCount]"/>
						</g:if>
						<g:else>
							<div id="warningBox" class="roundedBox">
								<g:message code="noClient"/>
							</div>
						</g:else>
					</div>
				</div>
			</div>
			<div class="clients">
				<g:if test="${clientSelected}">
					<div class="roundedBox">
						<g:if test="${balance > 0}">
							<span class="hours title green">
						</g:if>
						<g:else>
							<span class="hours title red">
						</g:else>	
							<g:formatNumber number="${balance}" type="number" maxFractionDigits="2" format="#" groupingUsed="false"/>
						</span> 

						<span class="spanHours"> 
							<g:message code="balance"/>
						</span>
					</div>
				</g:if>
			</div>
		</div>
	</body>
</html>