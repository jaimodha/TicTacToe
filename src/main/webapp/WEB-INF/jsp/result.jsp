<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Result</title>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$(function(){
    $.ajax({
        url: "TwoPlayer.json",
        success: function( data ) {
        	
        		for(var i=0;i<data.twopgame.length;++i){
        			if(data.twopgame[i].hostname ==="${username}" || data.twopgame[i].joinname ==="${username}"){
        				$("#msg").append("Winner Name Or Game result "+data.twopgame[i].winner);
        				for(var j=0;j<=2;j++){
            				$("#r1").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
            			}
            			for(var j=3;j<=5;j++){
            				$("#r2").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
            			}
            			for(var j=6;j<=8;j++){
            				$("#r3").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
            			}
            			break;
        			}
        			
        		}
        			
        	},
    });
});

</script>
</head>
<body>
<h2>hello ${username}</h2>
<sec:authorize access="hasRole('ROLE_USER')">
<ul>
	<li><a href ="remultimenu.html">MainMenu</a></li>
	<li><a href ="mplogout.html">Logout</a></li>
</ul>
</sec:authorize>
<h3 id="msg"></h3>
<table id = "bord" border ="1">
<tr id = "r1"></tr>
<tr id = "r2"></tr>
<tr id = "r3"></tr>
</table>
</body>
</html>