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
<!-- <h3><a href="profile.jsp">Profile</a></h3> -->
<form action="profile.jsp">
    <input type="submit" value="Profile" />
</form>
<br><br>
<!-- <h3><a href="./viewitem">view available items</a></h3> -->
<form action="./viewitem">
    <input type="submit" value="View Available Items" />
</form>
<br><br>
<!-- <h6><a href="./logout">Logout</a></h6> -->
<form action="./logout">
    <input type="submit" value="Logout" />
</form>
<br><br>
<form action="./roomservlet">
    <input type="submit" value="Auction Room" />
</form>
</body>
</html>