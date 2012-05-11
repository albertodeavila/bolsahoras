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
					<div class="marginLeft">
						<g:if test="${bagSelected}">
							<g:render template="../includes/listMovements" model="[movements:movements, movementCount:movementCount]"/>
						</g:if>
						<g:else>
							<div id="warningBox" class="roundedBox">
								<g:message code="noBagSelected"/>
							</div>
						</g:else>
					</div>
				</div>
			</div>
			<div class="clients">
				<g:if test="${bagSelected}">
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
				<div class="roundedBox">
					<h3 class="client title">
						<g:message code="bags"/>
					</h3>
					<div class="margin10">
						<g:if test="${clientSelected.bags.size() > 0}">
							<g:each in="${clientSelected.bags.sort{it.id}}" var="bag">
								<div class="nav noBackground bag">
									<g:link action="showMovements" params="[bag: bag.id, clientCif: clientSelected.cif]">
										<img alt="${message(code:'movement.show', default:'Mostrar movimientos')}" src="${resource(file:'/images/icons/bullet_green.png')}"/>${bag.name}
									</g:link>
								</div>
							</g:each>
						</g:if>
						<g:else>
							<div id="warningBox" class="roundedBox">
								<g:message code="noBag"/>
							</div>
						</g:else>
					</div>
				</div>
				
			</div>
		</div>
	</body>
</html>