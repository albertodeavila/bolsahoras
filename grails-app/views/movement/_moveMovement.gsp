<div>
	<g:form name="moveMovementForm" action="move">
		<h3 class="redTitle" align="center"> <g:message code="movement.move.alt"/></h3>
		<table>
			<tr>
				<td>
					<g:message code="movement.selectBag"/>
				</td>
				<td>
					<g:select name="bagToMove" from="${bags}" optionValue="name" optionKey="id"/>
				</td>
			</tr>
			<tr>
				<td colspan ="5" style="text-align:right">
					<g:hiddenField name="clientCif" value="${clientCif}"/> 
					<g:hiddenField name="bagId" value="${bagId}"/>
					<g:hiddenField name="movementId" value="${movementId}"/>
					<g:submitButton id="moveMovementButton" class="button" name="moveMovementButton" value="${message(code:'movement.move', default:'Mover')}"/>
				</td>
			</tr>
		</table>
	</g:form>
</div>
<r:external uri="/js/jquery.ui.datepicker-es.js"/>
<r:external uri="/js/buttons.js"/>