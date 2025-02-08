<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Store Cookies</title>
</head>
<body>
<%
Cookie c1=new Cookie("userName","johnDoe");
Cookie c2=new Cookie("userEmail","johndoe@example.com");
Cookie c3=new Cookie("userLocation","NewYork");
c1.setMaxAge(60*60*24);
c2.setMaxAge(60*60);
c3.setMaxAge(30);
response.addCookie(c1);
response.addCookie(c2);
response.addCookie(c3);
out.println("Cookies Stored!");

%>
</body>
</html>