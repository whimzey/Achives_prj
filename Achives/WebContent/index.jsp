<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
body {
    background-image:
        url('http://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Пожалуйста, авторизуйтесь</title>
</head>
<body>
 
    <div align="center" style="margin-top: 50px;">
 
        <form action="logon" method="POST">
            Username:  <input type="text" name="username" size="20px"> <br>
            Password:  <input type="text" name="password" size="20px"> <br><br>
            Я здесь босс <input type="checkbox" name="isaboss" value="logon" /><br>
        <input type="submit" value="Войти"></form>
       <form>
        <input type="button" name="register" value="Зарегистрироваться" onclick='location.href="registration.jsp"'></form>
        
 
    </div>
 
</body>
</html>