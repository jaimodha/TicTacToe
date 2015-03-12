<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Register</title>
</head>
<body>
<h1>TicTacToe(X0)</h1>
<h3>Please fill in the details to register.</h3>
<form:form modelAttribute="player">
<table border = 1>
<tr>
	<td>UserName*</td>
	<td><form:input path="username" /></td>
</tr> 
<tr>
	<td>Password*</td>
	<td><form:input path="password" /></td>
</tr> 
<tr>
	<td>e-Mail*</td>
	<td><form:input path="email" /></td>
</tr> 
<tr><td><input type = "submit" name = "register" value = "SignUp"/></td></tr>
</table>
</form:form>
</body>
</html>