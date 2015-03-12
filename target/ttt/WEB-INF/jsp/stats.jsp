<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>History</title>
</head>
<body>
<h2>Game History</h2>
<table border = 1>
<tr>
	<td>The number of games completed</td><td>${ans1}</td>
</tr>
<tr>
	<td>The number of 1-player games completed.</td><td>${ans2}</td>
</tr>
<tr>
	<td>The number of 2-player games completed.</td><td>${ans3}</td>
</tr>
<tr>
	<td>The win rate against AI.</td><td>${ans4}</td>
</tr>
<tr>
	<td>The win rate against human players.</td><td>${ans5}</td>
</tr>
</table>
<table border = 1>
<tr>
	<th>Serial Number</th>
	<th>Opponent</th>
	<th>Start Time</th>
	<th>End Time</th>
	<th>Outcome</th>
	<th>Duration</th>
</tr>

<c:forEach items="${ans6}" var="entry" varStatus="status">
<tr>
	<td>${status.index+1}</td>
	<td>
	<c:choose>
	<c:when test="${entry.getPlayer2().getUsername() eq null}">
	AI
	</c:when>
	<c:otherwise>
	<c:if test="${entry.getPlayer2().getUsername() eq username}">
	${ entry.getPlayer1().getUsername()}
	</c:if>
	<c:if test="${entry.getPlayer1().getUsername() eq username}">
	${entry.getPlayer2().getUsername()}
	</c:if>
	</c:otherwise>
	</c:choose>
	</td>
	<td>${entry.starttime}</td>
	<td>${entry.endtime}</td>
	<td>
	<c:choose>
	<c:when test="${entry.status eq 'AI_WINS' }">
	${entry.status}
	</c:when>
	<c:when test="${entry.status eq 'PLAYER1_WINS'}">
	Won
	</c:when>
	<c:when test="${entry.status eq username}">
	Won
	</c:when>
	<c:when test="${entry.status eq 'Tie' }">
	${entry.status}
	</c:when>
	<c:otherwise>
	Lost
	</c:otherwise>
	</c:choose>
	
	
	
	
	
	</td>
	<td>${duration.get(status.index)}</td>
</tr>
</c:forEach>
</table>

<a href = "GameMenu.html">MainMenu</a>
</body>
</html>