<%@page import="dbService.executor.Executor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbService.DBService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Регистрация</title>
</head>
<body>
<% 
DBService dbservice = new DBService();
Executor executor = new Executor(dbservice.getMysqlConnection());
ArrayList<String> bossesNames = executor.execQuery("select * from boss",result-> {
	ArrayList<String> bosses=new ArrayList<String>();
	while(result.next()){
		bosses.add(result.getString(4));}
	   return bosses;
});%>

<div align="center" style="margin-top: 50px;">
 
        <form action="registration" method="POST">
            Имя: <input type="text" name="name" size="20px"> 
            Фамилия: <input type="text" name="surname" size="20px"> <br>
            Username:  <input type="text" name="username" size="20px"> <br>
            Password:  <input type="text" name="password" size="20px"> <br><br>
           Руководитель 
           <select name="bossid" >
           <option value="00">Я здесь Босс!</option>
           <%
        for (int i = 0; i < bossesNames.size(); i++) {
        	int x = i+1;
   out.println("<option  value=\""+x+"\">"+bossesNames.get(i)+"</option>"
		   );} %>
        </select>
        <input type="submit" value="Зарегистрироваться">

        </form>
 
    </div>
</body>
</html>