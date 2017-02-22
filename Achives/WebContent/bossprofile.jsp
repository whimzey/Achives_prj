<%@page import="dbService.dao.UsersDAO"%>
<%@page import="messages.MessageDAO"%>
<%@page import="dbService.dataSets.BossDataSet"%>
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
   #body{
   position:relative;
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

<% Map<String,String> messageMap = (Map<String,String>)session.getAttribute("message");%>
<% BossDataSet bossProfile = (BossDataSet)session.getAttribute("profile");%>
<% ArrayList<String> achives = (ArrayList<String>) session.getAttribute("achives");%>
<%String name = bossProfile.getName();
long id = bossProfile.getId();
//session.setAttribute("name", name);
%>

<div id="header"><h1 >Достижения моих сотрудников</h1> 

<div id="userinfo" align="right">

Имя: <%=name %><br>
 <form action="Logout" method="GET"><input type="submit" value="Выход" ></form>
</div></div>

<div id="body"> 
<div  id="reachedachieves"><h3>Задачи моих проектов</h3> <br>
<% ArrayList<String> list = achives; %>
<%  for (int i = 0; i < list.size(); i++) {
	out.println(list.get(i));
	out.println("<br>");
	}
	 %>
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

var items=1;
function AddItem() {
  div=document.getElementById("items");
  button=document.getElementById("add");
  items++;
  
  newitem+="<input type=\"text\" name=\"achivename" + items+"\" "size=\"45\"><input type=\"text\" name=\"score"+ items+"\"  /><br>";
  newnode=document.createElement("span");
  newnode.innerHTML=newitem;
  div.insertBefore(newnode,button);
}
</script>
<div class="b-popup" id="popup1">
    <div class="b-popup-content">
      
  <form action="NewAchives" method="POST"> 
  Добавить задачу<br>
 <div ID="items"> 
  <input type="text" name="achivename"  /><input type="text" name="score"  />
 <input type="button" onclick="javascript:AddItem()" value="Еще одну, пожалуй" ID="add">
 </div>
    <input type="submit" onclick="javascript:PopUpHide()" value="ОК">
    </form>
    
</div></div>
<input type="button" value="Добавить задачи" onclick="javascript:PopUpShow()" >
</div>
	

<div id="achives">
<h3>Достижения на согласовании</h3> <br>



<% if(messageMap!=null){ 
	for (Map.Entry<String, String> entry : messageMap.entrySet())

{
    String[]x = entry.getValue().split(",");
	
	out.println("<form action=\"AchiveApproval\" method=\"POST\">"+ x[0]+" просит подтвердить выполнение достижения " + "\""+ x[1]+
			"\"" + "<input type=\"submit\" name=\"approve\" value=\"Подтвердить\"\">"+
					"<input type=\"hidden\" name=\"employeeName\" value=\""+x[0]+"\" />"+
							"<input type=\"hidden\" name=\"achive\" value=\""+x[1]+"\" />"+
									"<input type=\"hidden\" name=\"id\" value=\""+String.valueOf(id)+"\" />"+
											"<input type=\"hidden\" name=\"idmessage\" value=\""+String.valueOf(entry.getKey())+"\" />"+
													"<input type=\"hidden\" name=\"name\" value=\""+bossProfile.getUsername()+"\" />"+
			"<input type=\"submit\" name=\"dontapprove\" value=\"Отклонить\">"+
	        "</form>");
	out.println("<br>");
	}}
else {
	out.println("Сейчас ничего нет, но скоро обязательно что-нибудь появится!");
}%>
	</div>
	

<div id="footer">Здесь могла быть полезная информация, но ее украл енот!</div>
</div>

</body>
</html>
