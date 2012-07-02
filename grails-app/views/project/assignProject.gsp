<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title> <g:message code="projects"/> </title>
		<r:require module="projects"/>
	</head>
	<body>
		<g:if test="${clients}">
			<g:form action="assign">
				<div class="width100">
					<div class="middleRight">
						<div id="projectsWithoutClient" class="accordion" >
							<h3>
								<a href="#"><g:message code="projects"/></a>
							</h3>
							<div class="autoHeight placeholder">
								<g:each in="${projects}" var="project">
									<div id="${project.key}" class="roundedBox paddingLeft" onmouseover="cursorOver(this)" ondrag="cursorClick(this)">
										${project.name}
									</div>
								</g:each>
							</div>
						</div>
					</div>
					<div class="middleLeft">
						<g:each in="${clients}" var="client">
							<div class="projectsByClient" >
								<h3>
									<a href="#">${client.name}</a>
								</h3>
								<div id="${client.cif}" class="autoHeight" >
									<g:each in="${client.bags}" var="bag">
										<div id="${bag.id}" class="placeholder noBackground box" onChange="this.style.color='red'">
											<h4 class="marginLeft marginTop">${bag.name}
											<g:link class="button deleteButton floatRight" action="deleteBag" params="[bagId: bag.id]" title="${message(code:'deleteBag', default:'Borrar bolsa')}"> 
												<img alt="${message(code:'bag.deleteBag', default:'Borrar bolsa')}" src="${resource(file:'/images/icons/basket_delete.png')}"  /> 
											</g:link>
											</h4>
											<g:each in="${bag.projects}" var="project">
												<div id="${project.key}" class="project paddingLeft roundedBox">
													${project.name}
												</div>
												<input type="hidden" name="${bag.id}" value="${project.key}"/>
											</g:each>
										</div>
									</g:each>
									<div class="paddingBotton">
										<span id="${client.cif}" class="button" title="${message(code:'bag.add', default:'Agregar bolsa')}" onclick="showColorbox('${client.cif}')">
											<img alt="${message(code:'bag.add', default:'Agregar bolsa')}" src="${resource(file:'/images/icons/basket_add.png')}"  /> <g:message code="bag.add"/>
										</span>
									</div>
								</div>
							</div>
						</g:each>
					</div>
				</div>
				<div class="floatRight clearBoth">
					<g:submitButton id="assignButton" class="button" name="assignButton" value="${message(code:'projects.assign', default:'Asignar')}"/>
				</div>
			</g:form>
		</g:if>
		<g:else>
			<g:if test="${!projects}">
				<div id="warningBox" class="roundedBox">
					<g:message code="projects.noClientsNeitherProjects"/>
				</div>
			</g:if>
			<g:else>
				<div id="warningBox" class="roundedBox">
					<g:message code="projects.noClients"/>
				</div>
			</g:else>
		</g:else>
	</body>	
</html>