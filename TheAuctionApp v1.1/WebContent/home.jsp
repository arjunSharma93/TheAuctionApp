<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>home_page</title>
</head>
<body>
<%

if(session.getAttribute("email")==null||session.getAttribute("password")==null){
	response.sendRedirect("login.html");
}
%>

<h1>Welcome,  <% out.println(session.getAttribute("name")); %> to our Application.</h1>
<br>
<h3><a href="profile.jsp">Profile</a></h3>
<br><br>
<h3><a href="./viewitem">view available items</a></h3>
<br><br>
<h6><a href="./logout">Logout</a></h6>
</body>
</html>