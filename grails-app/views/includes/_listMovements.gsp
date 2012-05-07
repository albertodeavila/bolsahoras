<r:require module="tooltip"/>
<g:if test="${movements}">
	<table>
		<thead>
			<tr>
				<g:sortableColumn property="updateDate" title="${message(code:'movement.date', default:'Fecha')}" style="width:3%" params="[clientCif:clientSelected.cif, offset:offset]" action="showMovements"/>
				<g:sortableColumn property="zendeskTicket" title="${message(code:'movement.zendeskTicket', default:'Ticket')}" style="width:3%" params="[clientCif:clientSelected.cif, offset:offset]" action="showMovements"/>
				<g:sortableColumn property="jiraId" title="${message(code:'movement.JIRAKey', default:'JIRA')}" style="width:3%" params="[clientCif:clientSelected.cif, offset:offset]" action="showMovements"/>
				<g:sortableColumn property="title" title="${message(code:'movement.title', default:'Título')}" style="width:20%" params="[clientCif:clientSelected.cif, offset:offset]" action="showMovements"/>
				<g:sortableColumn property="timeSpent" title="${message(code:'movement.hours', default:'Horas')}" style="width:3%" params="[clientCif:clientSelected.cif, offset:offset]" action="showMovements"/>
				<th width="3%"><g:message code="movement.balance"/></th>	
				<sec:access expression="hasRole('ROLE_ADMIN')">
					<th width="3%"></th>	
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
							<g:if test="${movement.timeSpent < 0}">
								<span id="${movement.id}" class="button payBack" title="${message(code:'movement.payBack', default:'Reembolsar')}" onclick="showColorbox(${movement.id})">
									<img alt="${message(code:'movement.payBack.alt', default:'Reembolsar movimiento')}" src="${resource(file:'/images/icons/report_delete.png')}"  /> 
								</span>
							</g:if>
						</td>
					</sec:access>
				</tr>	
			</g:each>
		</tbody>
	</table>
	<sec:access expression="hasRole('ROLE_ADMIN')">
		<g:paginate action="showMovements" offset="${offset}" next="${message(code:'default.paginate.next', default:'Siguiente')}" prev="${message(code:'default.paginate.prev', default:'Anterior')}" total="${movementCount}" params="[clientCif:clientSelected.cif, offset:offset]"/>
	</sec:access>
	<sec:access expression="hasRole('ROLE_USER')">
		<g:paginate action="showMovements" offset="${offset}" next="${message(code:'default.paginate.next', default:'Siguiente')}" prev="${message(code:'default.paginate.prev', default:'Anterior')}" total="${movementCount}" params="[clientCif:clientSelected.cif, offset:offset]"/>
	</sec:access>
</g:if>
<g:else>
	<div id="warningBox" class="roundedBox">
		<g:message code="movement.noMovements"/>
	</div>
</g:else>
<sec:access expression="hasRole('ROLE_ADMIN')">
	<div class="marginRight5" style="text-align: right" >
		<g:hiddenField name="clientCif" value="${clientSelected.cif}"/>
		<span id="addMovement" class="button ">
			<img alt="${message(code:'movement.add.alt', default:'Agregar movimiento')}" src="${resource(file:'/images/icons/report_add.png')}"/>
			<g:message code="movement.add"/> 
		</span>
	</div>
</sec:access>