<r:require module="tooltip"/>
<g:if test="${movements}">
	<table>
		<thead>
			<tr>
				<th width="3%"><g:message code="movement.date"/></th>
				<th width="3%"><g:message code="movement.ZDTicket"/></th>
				<th width="3%"><g:message code="movement.JIRAKey"/></th>	
				<th width="20%"><g:message code="movement.title"/></th>	
				<th width="3%"><g:message code="movement.hours"/></th>	
				<th width="3%"><g:message code="movement.balance"/></th>
				<sec:access expression="hasRole('ROLE_ADMIN')">
					<th width="21%"></th>	
				</sec:access>
			</tr>
		</thead>
		<tbody>
			<g:each in="${movements}" var="movement" status="count">
				<tr>
					<td>
						<g:formatDate format="dd-MM-yyyy" date="${movement.updateDate}" />
					</td>
					<td>
						${movement.zendeskTicket}
					</td>
					<td>
						${movement.jiraId}
					</td>
					<td>
						${movement.title}
					</td>
					<td style="text-align: center;">
						<g:formatNumber number="${(movement.timeSpent/3600)}" type="number" maxFractionDigits="2" format="#" groupingUsed="false"/>
					</td>
					<td>
						<g:formatNumber number="${accumulatedBalance[count + offset]}" type="number" maxFractionDigits="2" format="#" groupingUsed="false"/>
					</td>
					<sec:access expression="hasRole('ROLE_ADMIN')">
						<td>
							<span id="move_${movement.id}" class="button move" title="${message(code:'movement.move', default:'Mover')}" onclick="showColorboxMove(${movement.id})">
								<img alt="${message(code:'movement.move', default:'Mover')}" src="${resource(file:'/images/icons/report_go.png')}"  /> 
							</span>
							<g:if test="${movement.timeSpent < 0}">
								<span id="payback_${movement.id}" class="button payBack" title="${message(code:'movement.payBack', default:'Reembolsar')}" onclick="showColorboxPayback(${movement.id})">
									<img alt="${message(code:'movement.payBack.alt', default:'Reembolsar movimiento')}" src="${resource(file:'/images/icons/report_delete.png')}"  /> 
								</span>
							</g:if>
						</td>
					</sec:access>
				</tr>	
			</g:each>
		</tbody>
	</table>
		<g:paginate action="showMovements" offset="${offset}" next="${message(code:'default.paginate.next', default:'Siguiente')}" prev="${message(code:'default.paginate.prev', default:'Anterior')}" total="${movementCount}" params="[clientCif:clientSelected.cif, offset:offset, bagId:bagSelected.id]"/>
</g:if>
<g:else>
	<div id="warningBox" class="roundedBox">
		<g:message code="movement.noMovements"/>
	</div>
</g:else>
<sec:access expression="hasRole('ROLE_ADMIN')">
	<div class="marginRight5" style="text-align: right" >
		<g:hiddenField name="clientCif" value="${clientSelected.cif}"/>
		<g:hiddenField name="bagId" value="${bagSelected.id}"/>
		<span id="addMovement" class="button ">
			<img alt="${message(code:'movement.add.alt', default:'Agregar movimiento')}" src="${resource(file:'/images/icons/report_add.png')}"/>
			<g:message code="movement.add"/> 
		</span>
	</div>
</sec:access>
<r:external uri="/js/buttons.js"/>