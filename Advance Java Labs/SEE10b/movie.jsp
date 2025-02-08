<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Ticket Price</title>
</head>
<body>
<h1>Movie Ticket Price</h1>
<%
String user=request.getParameter("user");
int age=Integer.parseInt(request.getParameter("age"));
int price;
if(age>62)
{
	price=50;
}
else if(age<10)
{
	price=30;
}
else
{
	price=80;
}
out.println("<p>Name:"+user+"</p>");
out.println("<p>Age:"+age+"</p>");
out.println("<p>Price:"+price+"</p>");

%>
</body>
</html>