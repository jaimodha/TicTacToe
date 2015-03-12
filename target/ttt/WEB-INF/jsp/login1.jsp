<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
</head>
<body>
	<h2>TicTacToe Login</h2>
	<c:if test="${not empty error}">
	${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	</c:if>
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
		<table border =1>
			<tr>
				<td>Username:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
			</table>
	</form>
	<ul>
	<li><a href = "ungame.html">Play As Guest</a></li>
	<li><a href = "register.html">Register</a></li>
	</ul>
</body>
</html>