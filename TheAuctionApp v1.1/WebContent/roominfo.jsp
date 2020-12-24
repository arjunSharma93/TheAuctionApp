<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="model.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>roominfo_page</title>
</head>
<body>

<%
if(session.getAttribute("email")==null || session.getAttribute("password")==null) {
	response.sendRedirect("login.html");
}
else if(session.getAttribute("name")!=null || session.getAttribute("password")!=null) {
	if(session.getAttribute("role").equals("user"))
	response.sendRedirect("login.html");
}
%>
<jsp:include page="/productlistforroom"></jsp:include>

choose item from the list below:
<br>
<%
ArrayList<Product> productList;
productList = (ArrayList<Product>) request.getAttribute("productList");
for(Product p: productList){%>
<%=p%>
<br><br>
<%}%>
<br>
<form action="./productlistforroom" method="post">
Enter Product Id: <input type="text" name="productId" placeholder="enter the product id for which the room will be created" required>
							<input type="submit" value="Create Room">
</form>

</body>
</html>