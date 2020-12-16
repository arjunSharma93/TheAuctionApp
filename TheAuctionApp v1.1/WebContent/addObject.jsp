<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>addObject_page</title>
</head>
<body>
<%
if(session.getAttribute("adminName")==null && session.getAttribute("adminPassword")==null){
	response.sendRedirect("adminLogin.html");
}
%>
<form action="formToBean.jsp" method="post">
<pre>
							${acknowledge}
Product Name= 				<input type="text" name="productName" placeholder="Enter Product Name" required>
Product Description= 		<input type="text" name="productDescription" placeholder="Enter Product Description" required>
Minimum Bid= 				<input type="text" name="minimumBid" placeholder="Enter Product Minimum Bid" required>
							<input type="submit" value="Add">
</pre>
</form>


</body>
</html>