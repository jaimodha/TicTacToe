<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login</title>
</head>
<body>
<form:form modelAttribute="player">

<table border = 1>
<tr>
	<td>Username</td>
	<td><form:input path="username" /></td>
</tr>
<tr>
	<td>Password</td>
	<td><form:password path="password" /></td>
</tr>
<tr>

<td><input type="submit" name="LogIn" value="login" /></td>
</tr>
</table>
</form:form>
<a href="register.html">SignUp</a>
</body>
</html>