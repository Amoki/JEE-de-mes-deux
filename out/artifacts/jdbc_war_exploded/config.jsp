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
    <jsp:useBean id="myUser" scope="session" class="bean.UserBean">
        <!-- INITIALISATION  -->
        <jsp:setProperty name="myUser" property="name" value="none" />
        <jsp:setProperty name="myUser" property="surname" value="none" />
        <jsp:setProperty name="myUser" property="age" value="0" />
        <jsp:setProperty name="myUser" property="preference" value="EVERY THING IS GOOD" />
    </jsp:useBean>

    <jsp:setProperty name="myUser" property="name" />
    <jsp:setProperty name="myUser" property="surname"/>
    <jsp:setProperty name="myUser" property="age"/>

</head>
<body>
Done
<a href="display.jsp">affichage</a>
</body>
</html>
