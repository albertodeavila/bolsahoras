<div>
	<h3 class="redTitle" align="center"><g:message code="bag.add"/></h3>
	<g:form name="createBagForm" action="createBag">
		<table>
			<tr>
				<td>
					<g:message code="bag.title"/>
				</td>
				<td>
					<g:textField name="name" value="${timeCanSpent}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:right">
					<g:hiddenField name="clientCif" value="${clientCif}"/>
					<g:submitButton id="createBagButton" class="button" name="createBagButton" value="Crear bolsa"/>
				</td>
			</tr>
		</table>
	</g:form>
</div>
<r:external uri="/js/buttons.js"/>
