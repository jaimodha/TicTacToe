<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>TwoPlayer</title>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$(function(){
    $.ajax({
        url: "TwoPlayer.json",
        success: function( data ) {
        		for(var i=0;i<data.twopgame.length;++i){
        			
        				 if(data.twopgame[i].hostname ==="${username}"){
        					 if(data.twopgame[i].winner!=null ){
        						 window.location = "result.html";
        					 }
        					$("#msgini").empty();
        					$("#r1").empty();
        					$("#r2").empty();
        					$("#r3").empty();
        					$("#msg").empty();
             				$("#msg").append(data.twopgame[i].joinname+" has joined.");
             				if(data.twopgame[i].hturn===true){
             					$("#msg1").empty();
             					$("#msg1").append("Please make your Move");
             					for(var j=0;j<=2;j++){
                    				$("#r1").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.twopgame[i].pboard[j]+"</a></td>");
                    			}
                    			for(var j=3;j<=5;j++){
                    				$("#r2").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.twopgame[i].pboard[j]+"</a></td>");
                    			}
                    			for(var j=6;j<=8;j++){
                    				$("#r3").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.twopgame[i].pboard[j]+"</a></td>");
                    			}
             				}else{
             					$("#msg1").empty();
             					$("#msg1").append("Wait for other players move");
             					for(var j=0;j<=2;j++){
                    				$("#r1").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
                    			}
                    			for(var j=3;j<=5;j++){
                    				$("#r2").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
                    			}
                    			for(var j=6;j<=8;j++){
                    				$("#r3").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
                    			}
             				}
             				
             				break;
        				 }else if(data.twopgame[i].joinname==="${username}"){
        					 if(data.twopgame[i].winner!=null){
        						 window.location = "result.html";
        					 }
        					$("#msgini").empty();
        					$("#r1").empty();
        					$("#r2").empty();
        					$("#r3").empty();
        					$("#msg").empty();
             				$("#msg").append("Joined game with host "+data.twopgame[i].hostname);
             				if(data.twopgame[i].hturn===false){
             					$("#msg1").empty();
             					$("#msg1").append("Please make your Move");
             					for(var j=0;j<=2;j++){
                    				$("#r1").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.twopgame[i].pboard[j]+"</a></td>");
                    			}
                    			for(var j=3;j<=5;j++){
                    				$("#r2").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.twopgame[i].pboard[j]+"</a></td>");
                    			}
                    			for(var j=6;j<=8;j++){
                    				$("#r3").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data.twopgame[i].pboard[j]+"</a></td>");
                    			}
             				}else{
             					$("#msg1").empty();
             					$("#msg1").append("Wait for other players move");
             					for(var j=0;j<=2;j++){
                    				$("#r1").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
                    			}
                    			for(var j=3;j<=5;j++){
                    				$("#r2").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
                    			}
                    			for(var j=6;j<=8;j++){
                    				$("#r3").append("<td>"+data.twopgame[i].pboard[j]+"</td>");
                    			}
             				}
             				
             				break;
        				 }
        			 }
        	
        },
    });
    update();
});
function update()
{
    $.ajax({
        url: "TwoPlayer.deferred.json",
        success: function( data ) {
        	for(var i=0;i<data.length;++i){
        		
				 if(data[i].hostname ==="${username}"){
					 if(data[i].winner!=null){
						 window.location = "result.html";
					 }
					$("#msgini").empty();
					$("#r1").empty();
					$("#r2").empty();
					$("#r3").empty();
					$("#msg").empty();
    				$("#msg").append(data[i].joinname+" has joined");
    				if(data[i].hturn===true){
    					$("#msg1").empty();
     					$("#msg1").append("Please make your Move");
    					for(var j=0;j<=2;j++){
            				$("#r1").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data[i].pboard[j]+"</a></td>");
            			}
            			for(var j=3;j<=5;j++){
            				$("#r2").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data[i].pboard[j]+"</a></td>");
            			}
            			for(var j=6;j<=8;j++){
            				$("#r3").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data[i].pboard[j]+"</a></td>");
            			}
    				}else{
    					$("#msg1").empty();
     					$("#msg1").append("Wait for other players move");
    					for(var j=0;j<=2;j++){
            				$("#r1").append("<td>"+data[i].pboard[j]+"</td>");
            			}
            			for(var j=3;j<=5;j++){
            				$("#r2").append("<td>"+data[i].pboard[j]+"</td>");
            			}
            			for(var j=6;j<=8;j++){
            				$("#r3").append("<td>"+data[i].pboard[j]+"</td>");
            			}
    				}
    				
    				break;
				 }else if(data[i].joinname==="${username}"){
					 if(data[i].winner!=null){
						 window.location = "result.html";
					 }
					
					$("#msgini").empty();
					$("#r1").empty();
					$("#r2").empty();
					$("#r3").empty();
					$("#msg").empty();
    				$("#msg").append("Joined game hosted by "+data[i].hostname);
    				if(data[i].hturn===false){
    					$("#msg1").empty();
      					$("#msg1").append("Please make your Move");
    					for(var j=0;j<=2;j++){
            				$("#r1").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data[i].pboard[j]+"</a></td>");
            			}
            			for(var j=3;j<=5;j++){
            				$("#r2").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data[i].pboard[j]+"</a></td>");
            			}
            			for(var j=6;j<=8;j++){
            				$("#r3").append("<td><a href = 'multicontrol.html?id="+j+"'>"+data[i].pboard[j]+"</a></td>");
            			}
    				}else{
    					$("#msg1").empty();
     					$("#msg1").append("Wait for other players move");
    					for(var j=0;j<=2;j++){
            				$("#r1").append("<td>"+data[i].pboard[j]+"</td>");
            			}
            			for(var j=3;j<=5;j++){
            				$("#r2").append("<td>"+data[i].pboard[j]+"</td>");
            			}
            			for(var j=6;j<=8;j++){
            				$("#r3").append("<td>"+data[i].pboard[j]+"</td>");
            			}	
    				}
    				
    				break;
				 }
			 }

            update();
        },
    });
}
</script>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
<ul>
	<li><a href ="remultimenu.html">MainMenu</a></li>
	<li><a href ="mplogout.html">Logout</a></li>
</ul>
</sec:authorize>
<h3 id="msgini">${message}</h3>
<h3 id="msg"></h3>
<h4 id="msg1"></h4>

<table id = "bord" border ="1">
<tr id = "r1"></tr>
<tr id = "r2"></tr>
<tr id = "r3"></tr>
</table>
</body>
</html>
