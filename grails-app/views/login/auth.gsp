<html>
<head>
	<meta name='layout' content='main'/>
	<title><g:message code="app"/></title>
	<r:require module="buttons"/>
</head>

<body>
<div id='login'>
	<div class='inner'>
		<g:if test='${flash.message}'>
			<div class="login_message">${flash.message}</div>
		</g:if>

		<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
			<div align="center">
				<table class="tableLogin">
					<tr>
						<td>
							<label for='username'><g:message code="userLogin.username"/>:</label>
						</td>
						<td>
							<input type='text' class='text_' name='j_username' id='username'/>
						</td>
					</tr>
					<tr>
						<td>
							<label for='password'><g:message code="userLogin.password"/>:</label>
						</td>
						<td>	
							<input type='password' class='text_' name='j_password' id='password'/>
						</td>
					</tr>
<%--				<tr>
						
						<td colspan="2" style="text-align: right">
							<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
							<label for='remember_me'><g:message code="userLogin.remember"/></label>
						</td>	
					</tr> --%>
					<tr>
						<td colspan="2" style="text-align: center">
								<g:submitButton id="loginButton" class="button" name="loginSubmit" value="${message(code:'userLogin.login', default:'Login')}"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
</body>
</html>
