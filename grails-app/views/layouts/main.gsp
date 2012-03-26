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
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">		
		<r:require module="bolsa"/>
		<r:require module="buttons"/>
        <r:layoutResources />
	</head>
	<body>
		<div id="salendaLogo" class="banner" role="banner">
			<div class="logo">
				<a href="http://www.salenda.es"><img src="${resource(dir: 'images', file: 'salenda.png')}" alt="${message(code:'salenda', default:'Salenda')}"/></a>
			</div>
			<div class="titleApp"> 
					<h1> <g:message code="app"/></h1>			
				</div>
			<div class="width100 inline">
				
				<sec:ifLoggedIn>
					<div class="nameRightUp">
							<h3> <sec:loggedInUserInfo field="username"/> <a href="${createLink(controller: 'logout')}">Log out</a> </h3>
					</div>
				</sec:ifLoggedIn>
			</div>
		</div>
		<div id="menu" class="nav">
			<ul>
			
				<li><a href="${createLink(controller: 'logout')}">Log out</a></li>
				<li><a href="${createLink(controller: 'login')}">Log in</a></li>
				<sec:access expression="hasRole('ROLE_ADMIN')">
					<li><a href="${createLink(controller: 'integration')}">Integración</a></li>
					<li><a href="${createLink(controller: 'user')}">Gestión de usuarios</a></li>
				</sec:access>
			</ul>		
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
        <r:layoutResources />
	</body>
</html>