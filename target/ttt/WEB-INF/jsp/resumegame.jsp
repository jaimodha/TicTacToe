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
<title>TicTacToe</title>
</head>
<body>
<h2>TicTacToe</h2>
<c:if test="${winner.equals('') and count == 0 }">
<p>Please make your move:</p>
</c:if>

<c:if test="${not empty winner }">
<c:if test="${winner.equals('x')}">
<font color="blue">You Won!</font>
</c:if> 
<c:if test="${winner.equals('o')}">
<font color="red">I Won!</font>
</c:if> 

<br />
<br/>
</c:if>
<c:if test="${not empty count}">
<c:if test="${count == 9}">
<c:if test="${empty winner}">
<font color="green">Game tied!</font>
</c:if>
</c:if>
</c:if>
<table border = 1>
<tr>
<c:forEach var="row" begin="0" end="2">
<c:choose>
<c:when test="${pboard[row] == 'x'}">
<td><strong><font color="blue" size="18">${pboard[row]}</font></strong></td>
</c:when>
<c:when test="${pboard[row] == 'o'}">
<td><strong><font color="red" size="18">${pboard[row]}</font></strong></td>
</c:when>
<c:otherwise>
<td><strong><c:if test="${flag}"><a href = "resumegame.html?id=${row}"></c:if><font size="18">${pboard[row]}</font><c:if test="${flag}"></a></c:if></strong></td>
</c:otherwise>
</c:choose>
</c:forEach>
</tr>
<tr>
<c:forEach var="row" begin="3" end="5">
<c:choose>
<c:when test="${pboard[row] == 'x'}">
<td><strong><font color="blue" size="18">${pboard[row]}</font></strong></td>
</c:when>
<c:when test="${pboard[row] == 'o'}">
<td><strong><font color="red" size="18">${pboard[row]}</font></strong></td>
</c:when>



<c:otherwise>
<td><strong><c:if test="${flag}"><a href = "resumegame.html?id=${row}"></c:if><font size="18">${pboard[row]}</font><c:if test="${flag}"></a></c:if></strong></td>
</c:otherwise>
</c:choose>
</c:forEach>
</tr>
<tr>
<c:forEach var="row" begin="6" end="8">
<c:choose>
<c:when test="${pboard[row] == 'x'}">
<td><strong><font color="blue" size="18">${pboard[row]}</font></strong></td>
</c:when>
<c:when test="${pboard[row] == 'o'}">
<td><strong><font color="red" size="18">${pboard[row]}</font></strong></td>
</c:when>




<c:otherwise>
<td><strong><c:if test="${flag}"><a href = "resumegame.html?id=${row}"></c:if><font size="18">${pboard[row]}</font><c:if test="${flag}"></a></c:if></strong></td>
</c:otherwise>
</c:choose>
</c:forEach>
</tr>
</table>
<br />
<br />
<sec:authorize access="hasRole('ROLE_USER')">
<ul>
<li><a href = "renewgame.html">New Game</a></li>
<li><a href = "relogout.html">Logout</a></li>

<c:choose>
<c:when test="${flag}">
<c:if test="${empty count}">
<li><a href = "resavegame.html">Save Game</a></li>
</c:if>
</c:when>
<c:otherwise>
<li><a href = "GameMenu.html">GameMenu</a></li>
</c:otherwise>
</c:choose>
<c:if test="${count == 9 }">
<li><a href = "GameMenu.html">GameMenu</a></li>
</c:if>



</ul>
</sec:authorize>
</body>
</html>