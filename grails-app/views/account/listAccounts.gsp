<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<r:require module="bolsa"/>		
		<title>List Accounts</title>
		</head>
	<body>
	<table>
	<thead>
		<tr>
			<th><g:message code="userManagment.name"/></th>
			<th><g:message code="userManagment.surname"/></th>
			<th><g:message code="userManagment.mail"/></th>	
			<th><g:message code="userManagment.delete"/></th>	
		</tr>
	</thead>
	<tbody>
		<g:each in="${users}" var="user" status="statusUser">
			<tr>
				<td>
					<div id="name_${user.id}" class="editValue">${user.name}</div>
				</td>
				<td>
					<div id="surname_${user.id}" class="editValue">${user.surname}</div>
				</td>
				<td>
					<div id="username_${user.id}" class="editValue">${user.username}</div>
				</td>
				<td>
					<g:link action=""> <img alt="${message(code:'userManagment.delete.alt', default:'Borrar usuario')}" src="${resource(file:'/images/icons/user_delete.png')}"/> </g:link>
				</td>
			</tr>
		</g:each>
	</tbody>
	</table>
		
	</body>
</html>
		