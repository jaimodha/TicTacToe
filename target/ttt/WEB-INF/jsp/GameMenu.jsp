<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>DashBoard</title>



</head>
<body>
<h2>Hello ${username}</h2>
<sec:authorize access="hasRole('ROLE_USER')">
<ul>
	<li><a href="TicTacToe.html">Play with AI</a></li>
	<li>Multiplayer
		<ul>
					<li><a href = "hostlist.html">HostGame</a></li>
					<li><a href = "joinlist.html">JoinGame</a></li>	
		</ul>
	</li>
	<li><a href="stats.html">Player Stats</a></li>
	<li><a href="menulogout.html">Logout</a></li>
</ul>
<h4>List of Saved Games.</h4>
<table border = 1>
<tr>
	<th>Serial Number</th>
	<th>StartTime</th>
	<th>EndTime</th>
	<th>Operation</th>
</tr>
<c:forEach items="${savedlist}" var="entry" varStatus="status">
<tr>
	<td>${status.index+1}</td>
	<td>${entry.starttime}</td>
	<td>${entry.savetime}</td>
	<td><a href = "resumegame.html?id1=${entry.id}">ResumeGame</a></td>
</tr>
</c:forEach>
</table>
</sec:authorize>

</body>
</html>