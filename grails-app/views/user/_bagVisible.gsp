<div>
	<g:form name="editBagVisibleForm" action="editBagVisible">
		<h3 class="redTitle" align="center"> <g:message code="userManagment.bags"/></h3>
		<g:if test="${user.client}">
			<g:if test="${bags!= null && bags.size() > 0}">
				<table>
					<tr>
						<td>
							<g:message code="userManagment.bags.select"/>					
						</td>
						<td>
							<g:select name="bagSelected" from="${bags}" multiple="multiple" optionKey="id" optionValue="name" noSelection="['0':'-Todas las bolsas-']"/>
						</td>			
					</tr>
					<tr>
						<td colspan="2" style="text-align:right;">
							<g:hiddenField name="userId" value="${user.id}"/>
							<g:submitButton name="bagButton" class="button" value="${message(code: 'userManagment.bags.save', default: 'Guardar bolsas que puede ver el usuario') }"/>
						</td>
					</tr>
				</table>
			</g:if>
			<g:else>
				<div id="warningBox" class="roundedBox">
					<g:message code="userManagment.bags.noBags"/>
				</div>
			</g:else>
			</g:if>
		<g:else>
			<div id="warningBox" class="roundedBox">
					<g:message code="userManagment.bags.noClient"/>
				</div>
		</g:else>
	</g:form>
</div>
<r:external uri="/js/buttons.js"/>
