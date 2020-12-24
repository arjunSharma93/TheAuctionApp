<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>details_page</title>
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
${noDetails}
<%
ArrayList<String> emailList = new ArrayList<String>();
ArrayList<String> amountList = new ArrayList<String>();

emailList = (ArrayList<String>) request.getAttribute("emailList");
amountList = (ArrayList<String>) request.getAttribute("amountList");

for(int i=0;i<emailList.size();i++){
%>
<table border="1">
<tr>
<td><%= emailList.get(i) %></td>
<td><%= amountList.get(i) %></td>
</tr>
</table>
<%} %>
</body>
</html>