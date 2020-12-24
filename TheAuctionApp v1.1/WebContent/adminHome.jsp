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

if(session.getAttribute("email")==null||session.getAttribute("password")==null){
	response.sendRedirect("login.html");
}
else if(session.getAttribute("name")!=null || session.getAttribute("password")!=null) {
	if(session.getAttribute("role").equals("user"))
	response.sendRedirect("login.html");
}
%>

<h1>Welcome,  <% out.println(session.getAttribute("name")); %> to Admin Panel.</h1>

<br><br>
<form action="roominfo.jsp">
    <input type="submit" value="create room" />
</form>

<br><br>
<form action="./roomservlet">
    <input type="submit" value="View Room" />
</form>

<br><br>
<!-- <h3><a href="addObject.jsp">add item</a></h3> -->

<form action="addObject.jsp">
    <input type="submit" value="add item" />
</form>

<br><br>
<!-- <h3><a href="./viewitem">view added item</a></h3> -->

<form action="./viewitem">
    <input type="submit" value="view item" />
</form>

<br><br>
<!-- <h6><a href="./logout">Logout</a></h6> -->

<form action="./logout">
    <input type="submit" value="Logout" />
</form>

</body>
</html>