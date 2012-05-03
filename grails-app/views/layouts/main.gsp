<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'bolsa.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">		
		<r:require module="bolsa"/>
		<r:require module="buttons"/>
		<r:require module="tipsy" />
        <r:layoutResources />
	</head>
	<body>
		<div id="salendaLogo" class="banner width100" role="banner">
			<div class="floatLeft">
				<a href="${createLink(controller: 'login')}"><img src="${resource(dir: 'images', file: 'salenda.png')}" alt="${message(code:'salenda', default:'Salenda')}"/></a>
			</div>
			<div class="titleApp floatLeft"> 
					<h1><g:message code="app"/></h1>			
				</div>
			<div class="floatRight">
				<sec:ifLoggedIn>
					<div class="nameRightUp">
						<g:message code="hello"/> <g:nameUserLogged/>
						<span id="logOut" class="button">
							 <a href="${createLink(controller: 'logout')}">
							 	<img src="${resource(dir: 'images', file: '/icons/door_out.png')}" alt="${message(code:'salenda', default:'Log out')}"/>
							 	<g:message code="logout"/>
							 </a>
						</span> 
					</div>
				</sec:ifLoggedIn>
			</div>
		</div>
		<div id="menu" class="nav">
			<ul>
				<sec:access expression="hasRole('ROLE_USER')">
					<li>
						<a href="${createLink(controller: 'userBag')}">
							<img alt="${message(code:'userBag', default:'Bolsa')}" src="${ resource(dir: 'images', file: '/icons/shoppingbag.png')}"/>
							<g:message code="userBag"/>
						</a>
					</li>
					<li>
						<a href="${createLink(controller: 'statistics')}">
							<img src="${ resource(dir: 'images', file: '/icons/chart_pie.png')}" alt="${message(code:'statistics', default:'Estadísticas')}"/>
							<g:message code="statistics"/>
						</a>
					</li>
					<li>
						<a href="${createLink(controller: 'price')}">
							<img src="${ resource(dir: 'images', file: '/icons/money.png')}" alt="${message(code:'prices', default:'Precios')}"/>
							<g:message code="prices"/>
						</a>
					</li>
				</sec:access>				
				<sec:access expression="hasRole('ROLE_ADMIN')">
					<li>
						<a href="${createLink(controller: 'integration')}">
							<img src="${ resource(dir: 'images', file: '/icons/cog.png')}" alt="${message(code:'integration', default:'Integración')}"/>
							<g:message code="integration"/>
						</a>
					</li>
					<li>
						<a href="${createLink(controller: 'user')}">
							<img src="${ resource(dir: 'images', file: '/icons/user.png')}" alt="${message(code:'users', default:'Gestión de usuarios')}"/>
							<g:message code="users"/>
						</a>
					</li>
					<li>
						<a href="${createLink(controller: 'project')}">
							<img src="${ resource(dir: 'images', file: '/icons/application_view_tile.png')}" alt="${message(code:'projects', default:'Proyectos')}"/>
							<g:message code="projects"/>
						</a>
					</li>
					<li>
						<a href="${createLink(controller: 'movement')}">
							<img src="${ resource(dir: 'images', file: '/icons/clock.png')}" alt="${message(code:'movements', default:'Movimientos')}"/>
							<g:message code="movements"/>
						</a>
					</li>
				</sec:access>
			</ul>		
		</div>
		<div>
			<g:if test="${flash.message != null }">
				<g:if test="${flash.messageType == 'error'}">
					<div id="errorBox" class="roundedBox">
						<g:message code="${flash.message}"/>
					</div>
				</g:if>
				<g:else>
					<div id="messageBox" class="roundedBox">
						<g:message code="${flash.message}"/>					
					</div>
				</g:else>
			</g:if>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
        <r:layoutResources />
	</body>
</html>