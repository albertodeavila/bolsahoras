<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title> <g:message code="userManagment"/> </title>
		<r:require module="tooltip"/>
		<r:require module="roundedBox"/>
	</head>
	<body>
		<g:hiddenField name="roles" value="${roles}"/>
		<g:hiddenField name="clients" value="${clients}"/>
		<div class="roundedBox">
			<table class="jeditable">
				<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code:'userManagment.name', default:'Nombre')}" params="[offset:offset]" action="show"/>
						<g:sortableColumn property="surname" title="${message(code:'userManagment.surname', default:'Apellidos')}" params="[offset:offset]" action="show"/>
						<g:sortableColumn property="username" title="${message(code:'userManagment.mail', default:'Email')}" params="[offset:offset]" action="show"/>
						<th><g:message code="userManagment.password"/></th>	
						<th><g:message code="userManagment.clients"/></th>	
						<th><g:message code="userManagment.role"/></th>	
						<th></th>	
					</tr>
				</thead>
				<tbody>
					<g:each in="${users}" var="user" status="statusUser">
						<tr>
							<td width="6%">
								<div id="name_${user.id}" class="editUserValue">${user.name}</div>
							</td>
							<td width="10%">
								<div id="surname_${user.id}" class="editUserValue">${user.surname}</div>
							</td>
							<td width="14%">
								<div id="username_${user.id}" class="editUserValue">${user.username}</div>
							</td>
							<td width="6%">
								<div id="password_${user.id}" class="editValuePassword">******</div>
							</td>
							<td width="45%">
								<div id="client_${user.id}" class="editClientSelect">
									<g:if test="${user.client !=null }">
										${user.client.name}	
									</g:if>
								</div>
							</td>
							<td width="15%">
								<div id="roles_${user.id}" class="editUserSelect">
									${user.authorities*.name.join(', ')}	
								</div>
							</td>
							<td width="10%">
								<g:link class="button deleteButton" action="delete" params="[userId: user.id]" title="${message(code:'userManagment.delete.alt', default:'Borrar usuario')}"> 
									<img alt="${message(code:'userManagment.delete.alt', default:'Borrar usuario')}" src="${resource(file:'/images/icons/user_delete.png')}"  /> 
								</g:link>
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
			<g:paginate action="show" offset="${offset}" next="${message(code:'default.paginate.next', default:'Siguiente')}" prev="${message(code:'default.paginate.prev', default:'Anterior')}" total="${userTotal}" params="[offset:offset]"/>
			<div class="marginLeft"  style="text-align:right">
				<span id="addUserButton" class="button">
					<img alt="${message(code:'userManagment.add.alt', default:'Agregar usuario')}" src="${resource(file:'/images/icons/user_add.png')}"/>
					<g:message code="userManagment.add"/> 
				</span>
			</div>
		</div>
	</body>
</html>
		