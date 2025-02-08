<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Retrieve Cookies</title>
</head>
<body>
<h2>Stored Cookies</h2>
<%
Cookie[] cookies=request.getCookies();
if(cookies!=null)
{
	for(Cookie c:cookies)
	{
		String name=c.getName();
		if(name.equals("userName") || name.equals("userEmail") || name.equals("userLocation"))
		{
			out.println("Name:"+name+"<br>");
			out.println("Value:"+c.getValue()+"<br>");
			out.println("<hr>");
		}
	}
}
else
{
	out.println("Cookies not found");
}
%>
</body>
</html>