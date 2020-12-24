<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>profile_page</title>
</head>
<body>
<%
if(session.getAttribute("email") == null || session.getAttribute("password")==null){
	response.sendRedirect("login.html");
}
%>

<jsp:include page="/profile"></jsp:include>
<pre>
<table border="1">
<tr>
<td>Name</td><td> <%out.println(request.getAttribute("name")); %></td>
</tr>
<tr>
<td>Mobile</td><td> <%out.println(request.getAttribute("mobile")); %></td>
</tr>
</table>
</pre>
</body>
</html>