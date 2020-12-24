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
<br>
<%
if(session.getAttribute("name")==null || session.getAttribute("password")==null) {
	response.sendRedirect("login.html");
}
else if(session.getAttribute("name")!=null || session.getAttribute("password")!=null) {
	if(session.getAttribute("role").equals("user"))
	response.sendRedirect("login.html");
}

ArrayList<Product> productList;
productList = (ArrayList<Product>)request.getAttribute("productList");
for(/* Product p: productList */ int i=0;i<productList.size();i++){
%>
<table border="1">
<tr>
<td>Product Nam</td>
<td>Product Description</td>
<td>Product Minimum Bid</td>
</tr>
<tr> 
<td><%=productList.get(i).getProductName()%></td>
<td><%=productList.get(i).getProductDescription()%></td>
<td><%=productList.get(i).getMinimumBid()%></td>
</tr>
<tr>
<td>
<form action="./removeitem" method="post">
<input type="submit" value="remove" />
<input type="hidden" name="productid" value="<%=productList.get(i).getProductid()%>" />
</form>
</td>
<td>
<form action="./productlistforroom" method="post">
<input type="hidden" name="productId" value="<%=productList.get(i).getProductid()%>" />
<input type="submit" value="move to Room">
</form>
</td>
</tr>
</table>
<br>
<br>
<%}%>

<form action="addObject.jsp">
    <input type="submit" value="Add Object" />
</form>

<!-- <h3><a href="addObject.jsp">add object</a></h3> -->
</body>
</html>