<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "model.Product"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>auction_room</title>
</head>
<body>
<%
if(session.getAttribute("email")==null || session.getAttribute("password")==null) {
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
<table border="1">
<tr>
<td><%= productList.get(i).getProductName() %></td>
<td><%= productList.get(i).getProductDescription() %></td>
<td><%= productList.get(i).getMinimumBid() %></td>
</tr>
</table>
leader:
<tr>
<td><%= productAndEmailMap.get(productList.get(i)) %></td> with amount 
<td><%= productAndMaxAmountMap.get(productList.get(i)) %></td>
</tr>
<form action="./updatebidtab" method="post">
<input type="hidden" name="userEmail" value="<%= session.getAttribute("email") %>" />
<input type="hidden" name="productId" value="<%= productList.get(i).getProductid() %>" />
<input type="number" name="amount" placeholder="Enter amount more than minimumbid" min="<%= productList.get(i).getMinimumBid() %>" required/>
<input type="submit" value="bid" />
</form>
<br><br>
<%}%>
<%-- <%
ArrayList<Product> plist;
plist = (ArrayList<Product>)request.getAttribute("pList");
List<List> slist;
slist = (ArrayList<List>)request.getAttribute("superList");
for(int i=0; i < 1;i++){
%>
<tr>
<td><%=plist.get(i).getProductName()%></td>	>
<td><%=plist.get(i).getProductDescription()%></td>	>
<td><%=plist.get(i).getMinimumBid()%></td>
<br>
Leader name: <% for(int j=0; j<slist.size(); j++){
	if(slist.get(j).get(0) == plist.get(i).getProductid()){
		String n =(String) slist.get(j).get(1);
		String a = (String) slist.get(j).get(2);
		out.println(n +" with amount "+ a);
		break;
		}
	}
%>
<form action="./updatebidtab" method="post">
<input type="text" name="upass" placeholder="Enter your password" required />
<input type="text" name="amnt" placeholder="Enter amount more than min bid value" required />
<input type="hidden" name="productid" value="<%=plist.get(i).getProductid()%>" />
<input type="submit" value="bid" />
</form>
</tr>
<%}%> --%>
</body>
</html>