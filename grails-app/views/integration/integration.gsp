<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title> <g:message code="integration"/> </title>
		<r:require module="integration"/>
		<r:require module="roundedBox"/>
	</head>
	<body>
		<div class="roundedBox">
			<h3 class="jira title">
				<img width="50px" alt="${message(code:'integration.jira', default:'JIRA')}" src="${resource(file:'/images/jira_logo.png')}"  /> 
				<g:message code="integration.jira"/>
			</h3>
			<div class="margin40">
				<table> 
					<tr >
						<td width="15%">
							<g:message code="integration.url"/>:
						</td>
						<td colspan="3">
							<div id="jira" class="editIntegrationURL floatRigth maxWith90">${jira}</div>
						</td>
					</tr>
					<tr >
						<td width="15%">
							<g:message code="integration.jira.query"/>:
						</td>
						<td colspan="3">
							<div id="jiraQuery" class="editIntegrationURL floatRigth maxWith90">${jiraQuery}</div>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<span class="floatLeft marginRigth5">
								<g:message code="integration.jira.username"/>:
							</span>
						</td>	
						<td width="25%">						
							<div id="jiraUsername" class="editIntegrationUsername floatRigth maxWith300px">${jiraUsername}</div>
						</td>
						<td width="15%">
							<span class="floatLeft marginRigth5">
								<g:message code="integration.jira.password"/>:
							</span>
						</td>	
						<td>			
							<div id="jiraPass" class="editIntegrationValuePassword floatRigth maxWith300px ">${jiraPass}</div>
						</td>
					</tr>
					<tr >
						<td width="50%" colspan="3">
							<g:message code="integration.jira.zendesk"/>:
						</td>
						<td >
							<div id="jiraZendesk" class="editIntegrationURL floatRigth maxWith300px">${jiraZendesk}</div>
						</td>
					</tr>	
				</table>
				<div align="right">
					<g:link action="refreshJIRA" class="button"> 
						<img alt="${message(code:'integration.jira.refresh', default:'Refrescar datos JIRA')}" src="${resource(file:'/images/icons/arrow_refresh.png')}"  /> 
						<g:message code="integration.jira.refresh"/> 
					</g:link>
				</div>
			</div>
		</div>
		<div class="roundedBox">
			<h3 class="facturaDirecta title">
			 	<img width="50px" alt="${message(code:'integration.facturaDirecta', default:'Factura Directa')}" src="${resource(file:'/images/logo2.png')}"  /> 
			 	<g:message code="integration.facturaDirecta"/>
			 </h3>
			<div class="margin40">
				<table>
					<tr >
						<td width="15%">
							<g:message code="integration.url"/>:
						</td>
						<td colspan="3">
							<div id="facturaDirecta" class="editIntegrationURL floatRigth maxWith90" >${facturaDirecta}</div>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<span class="floatLeft marginRigth5">
								<g:message code="integration.jira.username"/>:
							</span>
						</td>	
						<td width="25%">						
							<div id="facturaDirectaUsername" class="editIntegrationUsername floatRigth maxWith300px">${facturaDirectaUsername}</div>	
						</td>
						<td width="15%">
							<span class="floatLeft marginRigth5">
								<g:message code="integration.jira.password"/>:
							</span>
						</td>	
						<td>			
							<div id="facturaDirectaPass" class="editIntegrationValuePassword floatRigth maxWith300px">${facturaDirectaPass}</div>
						</td>
					</tr>	
				</table>
				<div align="right">
					<g:link action="refreshFacturaDirecta" class="button"> 
						<img alt="${message(code:'integration.facturaDirecta.refresh', default:'Refrescar datos de Factura Directa')}" src="${resource(file:'/images/icons/arrow_refresh.png')}"  /> 
						<g:message code="integration.facturaDirecta.refresh"/> 
					</g:link>
				</div>
			</div>	
		</div>
		<div class="roundedBox">
			<h3 class="jira title"><g:message code="integration.bagHours"/></h3>
			<div class="margin40">
				<table>
					<tr>
						<td width="50%">
							<g:message code="integration.bagLimit"/>:
						</td>
						<td >
							<div id="bagLimit" class="editIntegrationURL floatRigth maxWith300px">${bagLimit}</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>