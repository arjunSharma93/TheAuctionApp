<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>

<%
/*Product product = new Product();
product.setProductName(request.getParameter("productName"));
product.setProductDescription(request.getParameter("productDescription"));
product.setMinimunBid(request.getParameter("minimumBid"));

request.setAttribute("product", product);*/
%>


<jsp:useBean id="product" class="model.Product" scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="product"/>

<jsp:forward page="/addobject"></jsp:forward>





</body>
</html>