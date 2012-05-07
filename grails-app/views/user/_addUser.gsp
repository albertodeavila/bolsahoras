<div>
<g:form name="addUserForm" action="create">
	<h3 class="redTitle" align="center"> <g:message code="userManagment.create"/></h3>
	<table style="text-align: center">
			<tr>
				<td>
					<g:message code="userManagment.name"/>
				</td>
				<td>
					<g:textField name="name" class="width80p"/>
				</td>
			</tr>
			<tr>
				<td>
					<g:message code="userManagment.surname"/>
				</td>
				<td>
					<g:textField name="surname" class="width120p"/>
				</td>
			</tr>
			<tr>
				<td>
					<g:message code="userManagment.mail"/>
				</td>
				<td>
					<g:textField name="email" class="width120p"/>
				</td>
			</tr>
			<tr>
				<td>
					<g:message code="userManagment.password"/>
				</td>
				
				<td>
					<g:passwordField name="password" class="width80p"/>
				</td>
			</tr>
			<tr>
				<td>
					<g:message code="userManagment.role"/>
				</td>
				<td>
					<g:select name="userRoles" from="${userRoles}" multiple="true" optionKey="id" optionValue="name" class="width120p"/>
				</td>
			</tr>
			<tr>
				<td>
					<g:message code="userManagment.clients"/>
				</td>
				<td>
					<g:select name="client" from="${clients}" optionKey="cif" optionValue="fullInfo" noSelection="['0':'-Sin cliente-']"/>
				</td>
			</tr>
			<tr>
				<td colspan ="6" style="text-align:right">
					<g:submitButton id="addUserButton" class="button" name="addUserButton" value="${message(code:'userManagment.add.alt', default:'Agregar usuario')}"/>
				</td>
			</tr>
	</table>
</g:form>
</div>
<r:external uri="/js/buttons.js"/>
