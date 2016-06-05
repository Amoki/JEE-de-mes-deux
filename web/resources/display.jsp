<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Step2: Display</title>
    <jsp:useBean id="myUser" scope="session" class="beans.UserModelBean" />
    <jsp:setProperty name="myUser" property="firstname" />
    <jsp:setProperty name="myUser" property="lastname"/>
    <jsp:setProperty name="myUser" property="age"/>
    <jsp:setProperty name="myUser" property="login"/>
    <jsp:setProperty name="myUser" property="pwd"/>
</head>
<body>
<p>
    Current firstname: <jsp:getProperty name="myUser" property="firstname"/> <br>
    Current name: <jsp:getProperty name="myUser" property="lastname"/> <br>
    Current age: <jsp:getProperty name="myUser" property="age"/> <br>
    Current login: <jsp:getProperty name="myUser" property="login"/> <br>
    Current password: <jsp:getProperty name="myUser" property="pwd"/> <br>
</p>
</body>
</html>
