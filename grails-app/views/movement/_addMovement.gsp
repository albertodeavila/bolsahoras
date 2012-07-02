<div>
	<g:form name="addMovementForm" action="create">
		<h3 class="redTitle" align="center"> <g:message code="movement.create"/></h3>
		<table>
			<tr>
				<td>
					<g:message code="movement.date"/>
				</td>
				<td>
					<input type="text" id="datepicker" name="date" size="10"/>
				</td>
			</tr>
			<tr>
				<td>
					<g:message code="movement.ZDTicket"/>
				</td>
				<td>
					<g:textField name="ZDTicket" class="widtd80p"/>
				</td>
			</tr>
			<tr>	
				<td>
					<g:message code="movement.JIRAKey"/>
				</td>
			<td>
					<g:textField name="JIRAKey" class="widtd80p"/>
				</td>
			</tr>
			<tr>
				<td>
					<g:message code="movement.title"/>
				</td>
				<td>
					<g:textField name="title" class="widtd80p"/>
				</td>
			</tr>
			<tr>
				<td>
					<g:message code="movement.hours"/>
				</td>
				<td>
					<g:textField name="hours" class="widtd80p"/>
				</td>
			</tr>
			<tr>
				<td colspan ="5" style="text-align:right">
					<g:hiddenField name="clientCif" value="${clientCif}"/> 
					<g:hiddenField name="bagId" value="${bagId}"/>
					<g:submitButton id="addMovementButton" class="button" name="addMovementButton" value="${message(code:'movement.create', default:'Agregar movimiento')}"/>
				</td>
			</tr>
		</table>
	</g:form>
</div>
<r:external uri="/js/addMovement.js"/>
<r:external uri="/js/jquery.ui.datepicker-es.js"/>
<r:external uri="/js/buttons.js"/>