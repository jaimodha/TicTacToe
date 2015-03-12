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
var flag = false;
$(function(){
	player();
});
$(function(){
    $.ajax({
        url: "hostlist.json",
        success: function( data ) {
        	$("#list").empty();
        		for(var i=0;i<data.hostnames.length;++i)
        			$("#list").append("<li>"+data.hostnames[i]+"</li>");
        	
        },
    });
    update();
});
function update()
{
    $.ajax({
        url: "hostlist.deferred.json",
        success: function( data ) {
            $("#list").empty();
            for(var i=0;i<data.length;++i)
                $("#list").append("<li>"+data[i]+"</li>");
           
            update();
        },
    });
}




$(function(){
    $.ajax({
        url: "joinlist.json",
        success: function( data ) {
        	$("#list1").empty();
        		for(var i=0;i<data.joinnames.length;++i)
        			$("#list1").append("<li>"+data.joinnames[i]+"</li>");
        	
        },
    });
    update1();
});
function update1()
{
    $.ajax({
        url: "joinlist.deferred.json",
        success: function( data ) {
            $("#list1").empty();
            for(var i=0;i<data.length;++i)
                $("#list1").append("<li>"+data[i]+"</li>");
           
            update1();
        },
    });    
}


function player(){
    $.ajax({
        url: "multiplayer.json",
        success: function( data ) {
        	
        	if(flag!=true){
        		for(var i=0;i<data.multiplayer.length;++i){
        			
        			if(data.multiplayer[i].hostname === "${username}"){
        				$("#msgini").empty();
        				$("#msg").append(data.multiplayer[i].joinname+" has joined please make your move");
        				for(var j=1;j<=3;j++){
        					$("#bord").append("<tr id ="+j+"></tr>");
        					for(var k=1;k<=3;k++)
        						{
        						$("#"+j).append("<td><a href='#'>_</a></td>");
        						}
        				}
        				//window.location.replace("MultiGame.html");
        				flag = true;
        				return;
        			}else if(data.multiplayer[i].joinname === "${username}"){
        				$("#msgini").empty();
        				$("#msg").append("Waiting for "+data.multiplayer[i].hostname+" move");
        				for(var j=1;j<=3;j++){
        					$("#bord").append("<tr id ="+j+"></tr>");
        					for(var k=1;k<=3;k++)
        						{
        						$("#"+j).append("<td>_</td>");
        						}
        				}
        				//window.location.replace("MultiGame.html");
        				flag = true;
        				return;
        			}
        		}
        			
        	player();
        	}
        },
    });
};



</script>
</head>
<body>
<h2>Hello ${username}</h2>
<ul>
		<li><a href="remultimenu.html">MainMenu</a></li>
		<li><a href="mplogout.html">Logout</a></li>
</ul>
<h3>This List show player that are currently in the join queue or host queue.</h3>
<table border = 1>
	<tr>
		<th>HostList</th>
		<th>JoinList</th>
	</tr>
	<tr>
		<td>
		<ul id="list">
		</ul>
		</td>
		
		<td>
		<ul id="list1">
		</ul>
		</td>
	</tr>
</table>
<h3 id="msgini">${message}</h3>
<h3 id="msg"></h3>

<table id = "bord" border ="1">
</table>
 
</body>
</html>