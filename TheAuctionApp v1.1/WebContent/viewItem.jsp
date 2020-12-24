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
<table border="1">
<tr>
<td>Product Name</td>
<td>Product Description</td>
<td>Product Minimum Bid</td>
</tr>
<tr> 
<td><%=p.getProductName()%></td>
<td><%=p.getProductDescription()%></td>
<td><%=p.getMinimumBid()%></td>
</tr>
</table>
<br>
<%}%>
</body>
</html>