<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Login Page</title>
		<link rel="stylesheet" type="text/css" href="resources/styles/login.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="resources/styles/style.css" media="screen" />
	</head>
	
	<body onload='document.loginForm.username.focus();'>

		<div id="container">
			<div class="centered">
			    <div class="centered-bg">Customer and Orders Application</div>
			</div>
		
			<div id="login-box">
				<h3>Login with Username and Password</h3>
		
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
		
				<form name='loginForm' action="<c:url value='/login' />" method='POST'>
					<table>
						<tr>
							<td align="right">User:</td>
							<td><input type='text' name='username' value=''></td>
						</tr>
						<tr>
							<td align="right">Password:</td>
							<td><input type='password' name='password' /></td>
						</tr>
						<tr>
							<td colspan='2' align="center"><input name="submit" type="submit" value="submit" /></td>
						</tr>
					</table>
		
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		
		</div>
	</body>
</html>