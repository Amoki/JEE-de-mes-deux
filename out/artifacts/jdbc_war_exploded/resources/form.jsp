<%--
  Created by IntelliJ IDEA.
  User: Hugo
  Date: 13/05/2016
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


</head>
<body>
<form  action="CreateUserServlet" method="post">
    Put your name <input type="text" name="name" />
    Put your surname <input type="text" name="surname" />
    Put your age <input type="number" name="age" />
    <input type="submit" value="GO!">
</form>
</body>
</html>
