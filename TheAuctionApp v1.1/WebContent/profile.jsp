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
Name: <%out.println(request.getAttribute("name")); %>

Mobile: <%out.println(request.getAttribute("mobile")); %>
</pre>
</body>
</html>