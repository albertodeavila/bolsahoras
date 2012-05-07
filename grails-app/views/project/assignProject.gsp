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
								<div id="${client.cif}" class="autoHeight placeholder" onChange="this.style.color='red'">
									<g:each in="${client.projects}" var="project">
										<div id="${project.key}" class="project paddingLeft roundedBox">
											${project.name}
										</div>
										<input type="hidden" name="${client.cif}" value="${project.key}"/>
									</g:each>
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
			<g:if test="${!clients && !projects}">
				<div id="warningBox" class="roundedBox">
					<g:message code="projects.noClientsNeitherProjects"/>
				</div>
			</g:if>
			<g:else>
				<g:if test="${!clients}">
					<div id="warningBox" class="roundedBox">
						<g:message code="projects.noClients"/>
					</div>
				</g:if>
			</g:else>
		</g:else>
	</body>	
</html>