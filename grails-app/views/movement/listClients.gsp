<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title> <g:message code="clients"/> </title>
		<r:require module="movements"/>
		<r:require module="roundedBox"/>
	</head>
	<body>
		<div class="width100">
			<div class="movements">
				<g:if test="${clientSelected && !clientSelected.bags}">
					<div id="warningBox" class="roundedBox">
						<g:message code="movement.noBags"/>
					</div>
				</g:if>
				<g:else>
					<g:if test="${bagSelected}">
						<div class="roundedBox">
							<g:render template="../includes/listMovements" model="[movements:movements]"/>
						</div>
					</g:if>
				</g:else>
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
						<g:message code="clients"/>
					</h3>
					<div class="margin10">
						<g:each in="${clients}" var="client">
							<div class="nav noBackground">
								<g:link action="showBags" params="[clientCif: client.cif]">
									<img alt="${message(code:'movement.show', default:'Mostrar movimientos')}" src="${resource(file:'/images/icons/resultset_next.png')}"/>${client.name}
								</g:link>
							</div>
							<g:if test="${clientSelected && (client.cif == clientSelected.cif)}">
								<div class="margin20">
									<g:each in="${clientSelected.bags.sort{it.id}}" var="bag">
										<div class="nav noBackground">
											<g:link action="showMovements" params="[bagId: bag.id, clientCif: client.cif]">
												<img alt="${message(code:'movement.show', default:'Mostrar movimientos')}" src="${resource(file:'/images/icons/bullet_green.png')}"/>${bag.name}
											</g:link>
										</div>
									</g:each>
								</div>
							</g:if>
						</g:each>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>