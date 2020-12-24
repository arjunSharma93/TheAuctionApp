<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "model.Product"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>adminAuction_room</title>
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
${msg}
<%
ArrayList<Product> productList = new ArrayList<Product>();
LinkedHashMap<Product, String> productAndMaxAmountMap = new LinkedHashMap<Product, String>();
LinkedHashMap<Product, String> productAndEmailMap = new LinkedHashMap<Product, String>();

productList = (ArrayList<Product>) request.getAttribute("productList");
productAndMaxAmountMap = ( LinkedHashMap<Product,String>) request.getAttribute("productAndMaxAmountMap");
productAndEmailMap = (LinkedHashMap<Product,String>) request.getAttribute("productAndEmailMap");

for(int i=0; i<productList.size(); i++){
%>
<tr>
<td><%= productList.get(i).getProductName() %>></td>
<td><%= productList.get(i).getProductDescription() %>></td>
<td><%= productList.get(i).getMinimumBid() %>></td>
</tr>
<br>leader:
<tr>
<td><%= productAndEmailMap.get(productList.get(i)) %></td> with amount 
<td><%= productAndMaxAmountMap.get(productList.get(i)) %></td>
</tr>
<form action="./deletebidtab" method="post">
<input type="hidden" name="userEmail" value="<%= productAndEmailMap.get(productList.get(i)) %>" />
<input type="hidden" name="productId" value="<%= productList.get(i).getProductid() %>" />
<input type="hidden" name="amount" value="<%= productAndMaxAmountMap.get(productList.get(i)) %>" />
<input type="submit" value="sold" />
</form>
<form action="./detailsbid">
<input type="hidden" name="productId" value="<%= productList.get(i).getProductid() %>"/>
<input type="submit" value="See details" />
</form>
<br>
<%}%>
</body>
</html>