<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import="java.util.*" %>
 <%@page import="model.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>itemlist_page</title>
</head>
<body>
<%
if(session.getAttribute("email")==null || session.getAttribute("password")==null) {
	response.sendRedirect("login.html");
}

ArrayList<Product> productList;
productList = (ArrayList<Product>)request.getAttribute("productList");
for(Product p: productList){
%>
<tr> 
<td><%=p.getProductName()%></td>  >
<td><%=p.getProductDescription()%></td>  >
<td><%=p.getMinimumBid()%></td>
</tr>
<br>
<br>
<%}%>
</body>
</html>