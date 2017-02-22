<%@page import="dbService.dao.UsersDAO"%>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbService.dataSets.UsersDataSet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
  

 body {
    background-image:
        url('http://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');

   width: 100%;
   margin:0px;
}
.b-popup{
    width:100%;
    min-height:100%;
    background-color: rgba(0,0,0,0.5);
    overflow:hidden;
   position:absolute;
    top:50px;
}
.b-popup .b-popup-content{
    margin:40px auto 0px auto;
    width:300px;
    text-align:center;
    padding:10px;
    background-color: #c5c5c5;
    border-radius:5px;
    box-shadow: 0px 0px 10px #000;
    
}
   #body{
  
   margin:10px;

}
    #header{
background:white;
width:100%;
float:right;
padding: 0 10px 0 10px;
margin-bottom:10px;
text-align:center;
box-shadow: rgba(0,0,0,1.2) 0px 1px 3px;
display:block;
border-radius: 4px;
}
     #userinfo{
padding:0 10px 10px 0;


}

#reachedachieves, #achives{
background:oldlace;
width:45%;
height:300px;
box-shadow: rgba(0,0,0,1.2) 0px 1px 3px;
padding: 10px;
display:block;
border-radius: 4px;
}
#reachedachieves{
float: left;}
#achives{
float: right;}

#footer{
background:white;
width:100%;
height:30px;
float:left;
padding:10px;
margin-top:10px;
text-align:right;
font-size:11px;
}

  </style>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Мой профиль</title>
</head>
  <body>

 <%UsersDataSet profile = (UsersDataSet)session.getAttribute("profile");%>
<% ArrayList<String> answerfromboss = (ArrayList<String>)session.getAttribute("answerfromboss");%>

<div id="header"><h1 >Мои достижения</h1> 

<div id="userinfo" align="right">

Имя: <%=profile.getName() %><br>
Руководитель: <%=profile.getBossName() %><br>

<form action="getPrize" method="POST">
	    Мои баллы: <%=profile.getScore() %>    <input type="submit" value="Потратить"></form>
	    <form action="Logout" method="GET"><input type="submit" value="Выход" ></form>
  
<script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
<script>
    $(document).ready(function(){
        //Скрыть PopUp при загрузке страницы    
        PopUpHide();
    });
    //Функция отображения PopUp
    function PopUpShow(){
        $("#popup1").show();
    }
    //Функция скрытия PopUp
    function PopUpHide(){
        $("#popup1").hide();
        //location.reload()

    }
</script>
<script >

setTimeout('$("#dim").hide();',800);
    </script>
    <% String success = (String)session.getAttribute("success"); %>
   
    <%if (success!=null){
    	
	out.println("<div class=\"b-popup\" id=\"dim\">"+
						"<div class=\"b-popup-content\">"+success+"</div></div>");
	session.removeAttribute("success");
}%>

<%if(answerfromboss.size()!=0){
	out.println("<div > Сообщения <input type=\"button\" onclick=\"javascript:PopUpShow()\" value=\"Показать\"></div>");
}%>

<div class="b-popup" id="popup1">
    <div class="b-popup-content">
        <% if(answerfromboss.size()!=0){
        for (int i = 0; i < answerfromboss.size(); i++) {
        	   	
        	out.println(answerfromboss.get(i));
        	out.println("<br>");
        }
   out.println("<form action=\"deleteanswerfromboss\" method=\"POST\">"+ 
    "<input type=\"submit\" onclick=\"javascript:PopUpHide()\" value=\"ОК\">"+
    "<input type=\"hidden\" name=\"id\" value=\""+profile.getId()+"\" /></form>");}%>
    </div>
</div>
</div></div>

<div id="body"> <div id="achives">
<h3>Доступные достижения</h3> <br>
<% ArrayList<String> list = profile.getAvaliableachevesArray(); %>


<%  for (int i = 0; i < list.size(); i++) {
	
	out.println("<form action=\"lettertoboss\" method=\"POST\">"+ list.get(i)+" "+
	        "<input type=\"submit\"  value=\"Подтвердить выполнение\">"+
	        		"<input type=\"hidden\" name=\"name\" value=\""+profile.getName()+"\" />"+
	        				"<input type=\"hidden\" name=\"achive\" value=\""+list.get(i)+"\" />"+
	        "</form>");
	out.println("<br>");
	} %>
	</div>
	
<div id="reachedachieves">
<h3>Выполненные задачи</h3> <br>
<% ArrayList<String> donelist = profile.getReachedachivesArray(); %>
<%  for (int i = 0; i < donelist.size(); i++) {
	out.println(donelist.get(i));
	out.println("<br>");
	}
	 %>
	</div>
<div id="footer">Здесь могла быть полезная информация, но ее украл енот!</div>
</div>

</body>
</html>
