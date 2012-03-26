<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title> <g:message code="userManagment.title"/> </title>
	</head>
	<body>
	<g:hiddenField name="roles" value="${roles}"/>
	<table class="jeditable">
	<thead>
		<tr>
			<th><g:message code="userManagment.name"/></th>
			<th><g:message code="userManagment.surname"/></th>
			<th><g:message code="userManagment.mail"/></th>	
			<th><g:message code="userManagment.role"/></th>	
			<th><g:message code="userManagment.delete"/></th>	
		</tr>
	</thead>
	<tbody>
		<g:each in="${users}" var="user" status="statusUser">
			<tr>
				<td width="15%">
					<div id="name_${user.id}" class="editUserValue">${user.name}</div>
				</td>
				<td width="15%">
					<div id="surname_${user.id}" class="editUserValue">${user.surname}</div>
				</td>
				<td width="15%">
					<div id="username_${user.id}" class="editUserValue">${user.username}</div>
				</td>
				<td width="30%">
					<div id="roles_${user.id}" class="editUserSelect">
						${user.authorities*.name.join(', ')}	
					</div>
				</td>
				<td width="15%">
					<span class="button">
						<g:link action="delete" params="[userId: user.id]"> 
							<img alt="${message(code:'userManagment.delete.alt', default:'Borrar usuario')}" src="${resource(file:'/images/icons/user_delete.png')}"  /> 
							<g:message code="userManagment.delete"/> 
						</g:link>
					</span>
				</td>
			</tr>
		</g:each>
		
	</tbody>
	</table>
	<div class="marginLeft">
		<span id="addUser" class="button">
			<img alt="${message(code:'userManagment.add.alt', default:'Agregar usuario')}" src="${resource(file:'/images/icons/user_add.png')}"/>
			<g:message code="userManagment.add"/> 
		</span>
		</div>
	</body>
</html>
		