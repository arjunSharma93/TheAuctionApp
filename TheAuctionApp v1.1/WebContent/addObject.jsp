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
if(session.getAttribute("name")==null && session.getAttribute("password")==null){
	response.sendRedirect("login.html");
}
else if(session.getAttribute("name")!=null && session.getAttribute("password")!=null){
	if(session.getAttribute("role").equals("user"))
	response.sendRedirect("login.html");
}
%>
<form action="formToBean.jsp" method="post">
<pre>
<table>
<tr></tr>
<tr></tr>
${acknowledge}
<td>
Product Name:			<input type="text" name="productName" placeholder="Enter Product Name" required>
</td>
<tr></tr>
<tr></tr>
<td>
Product Description:		<input type="text" name="productDescription" placeholder="Enter Product Description" required>
</td>
<tr></tr>
<tr></tr>
<td>
Minimum Bid:			<input type="text" name="minimumBid" placeholder="Enter Product Minimum Bid" required>
</td>
<tr></tr>
<tr></tr>
<td></td>							<td><input type="submit" value="Add"></td>
</table>
</pre>
</form>


</body>
</html>