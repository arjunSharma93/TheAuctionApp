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
${acknowledge}<br>
<%
if(session.getAttribute("adminName")==null || session.getAttribute("adminPassword")==null) {
	response.sendRedirect("login.html");
}

ArrayList<Product> productList;
productList = (ArrayList<Product>)request.getAttribute("productList");
for(/* Product p: productList */ int i=0;i<productList.size();i++){
%>
<tr> 
<td><%=productList.get(i).getProductName()%></td>	>
<td><%=productList.get(i).getProductDescription()%></td>	>
<td><%=productList.get(i).getMinimumBid()%></td>		<!-- <a href="./removeitem">remove this item</a> -->
<form action="./removeitem" method="post">
<input type="submit" value="remove" />
<input type="hidden" name="productid" value="<%=productList.get(i).getProductid()%>" />
</form>
</tr>
<br>
<br>
<%}%>
<h3><a href="addObject.jsp">add object</a></h3>
</body>
</html>