<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>adminHome_page</title>
</head>
<body>
<%

if(session.getAttribute("adminName")==null||session.getAttribute("adminPassword")==null){
	response.sendRedirect("adminLogin.html");
}
%>

<h1>Welcome,  <% out.println(session.getAttribute("adminName")); %> to Admin Panel.</h1>


<br><br>
<h3><a href="addObject.jsp">add object</a></h3>

<br><br><br>
<h6><a href="./logout">Logout</a></h6>
</body>
</html>