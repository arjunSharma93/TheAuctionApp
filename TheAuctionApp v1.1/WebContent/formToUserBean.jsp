<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<jsp:useBean id="user" class="model.User" scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>

<jsp:forward page="/reg"></jsp:forward>
</body>
</html>