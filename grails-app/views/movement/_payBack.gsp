<div>
	<h3 class="redTitle" align="center"> <g:message code="movement.payBack.alt"/></h3>
	<g:if test="${timeCanSpent > 0}">
		<g:form name="payBackForm" action="payBack">
			<table>
				<tr>
					<td>
						<g:message code="movement.payBack.info"/>
					</td>
					<td>
						<g:textField name="payBackHours" value="${timeCanSpent}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:right">
						<g:hiddenField name="clientCif" value="${clientCif}"/>
						<g:hiddenField name="movementId" value="${movementId}"/>
						<g:hiddenField name="bag" value="${bagId}"/>
						<g:submitButton id="payBackButton" class="button" name="payBackButton" value="${message(code:'movement.payBack', default:'Reembolsar')}"/>
					</td>
				</tr>
			</table>
		</g:form>
	</g:if>
	<g:else>
		<div id="warningBox" class="roundedBox">
			<g:message code="movement.payBack.warning"/>					
		</div>
	</g:else>
</div>
<r:external uri="/js/buttons.js"/>
