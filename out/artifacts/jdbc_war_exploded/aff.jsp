<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display User</title>
</head>
<body>
	<%
		String surname=request.getParameter("surname");
		String lastname=request.getParameter("lastname");
	%>
	
	<h1>User Info</h1>
	
	<h3>
		Surname : 
		<%= surname%>
	</h3>	
	
	<h3>
		Lastname : 
		<%= lastname%>
	</h3>
	
</body>
</html>