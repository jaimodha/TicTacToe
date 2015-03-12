<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>MultiPlayer</title>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
//var flag = false;
$(function(){
	player();
});

$(function(){
    $.ajax({
        url: "multiplayer.json",
        success: function( data ) {
        	
        		for(var i=0;i<data.multiplayer.length;++i){
        			
        			if(data.multiplayer[i].hostname === "${username}"){
        				//$("#bord").empty();
        			for(var j=0;j<=2;j++){
        				$("#r1").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
        			}
        			for(var j=3;j<=5;j++){
        				$("#r2").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
        			}
        			for(var j=6;j<=8;j++){
        				$("#r3").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
        			}
        			player();
        			return;
        			
        			
        			}else if(data.multiplayer[i].joinname === "${username}"){
        				//$("#bord").empty();
        				for(var j=0;j<=2;j++){
            				$("#r1").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
            			}
            			for(var j=3;j<=5;j++){
            				$("#r2").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
            			}
            			for(var j=6;j<=8;j++){
            				$("#r3").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
            			}
            			player();
            			return;
            			
        			}
        		}
        },
    });
});


function player()
{
    $.ajax({
        url: "hostlist.deferred.json",
        success: function( data ) {
    		for(var i=0;i<data.multiplayer.length;++i){
    			
    			if(data.multiplayer[i].hostname === "${username}"){
    				//$("#bord").empty();
    			for(var j=0;j<=2;j++){
    				$("#r1").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
    			}
    			for(var j=3;j<=5;j++){
    				$("#r2").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
    			}
    			for(var j=6;j<=8;j++){
    				$("#r3").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
    			}
    			player();
    			return;
    			
    			
    			}else if(data.multiplayer[i].joinname === "${username}"){
    				//$("#bord").empty();
    				for(var j=0;j<=2;j++){
        				$("#r1").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
        			}
        			for(var j=3;j<=5;j++){
        				$("#r2").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
        			}
        			for(var j=6;j<=8;j++){
        				$("#r3").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.multiplayer[i].pbord[j]+"</a></td>");
        			}
        			player();
        			return;
        			
    			}
    		}
              player();
        },
    });
}




</script>

</head>
<body>
<table id = "bord" border ="1">
<tr id = "r1"></tr>
<tr id = "r2"></tr>
<tr id = "r3"></tr>
</table>

</body>
</html>