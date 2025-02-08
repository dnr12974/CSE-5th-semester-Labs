<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Details</title>
</head>
<body>
 <h1>Student Details:</h1>
 <%
 String URL="jdbc:mysql://localhost:3307/USNdbSEE";
 String User="root";
 String Pass="";
 String usn=request.getParameter("usn");
 Connection connection=null;
 PreparedStatement preparedStatement=null;
 try
 {
	 Class.forName("com.mysql.jdbc.Driver");
	 connection=DriverManager.getConnection(URL,User,Pass);
	 String sql="SELECT * FROM Students WHERE USN=?";
	 preparedStatement=connection.prepareStatement(sql);
	 preparedStatement.setString(1,usn);
	 ResultSet rs=preparedStatement.executeQuery();
	 if(rs.next())
	 {
		 String name=rs.getString("Name");
		 out.println("<p>Name:"+name+"</p>");
		 out.println("<p>USN:"+usn+"</p>");
	 }
	 else
	 {
		 out.println("<p>Invalid USN!!!</p>");
	 }
 }
 catch(ClassNotFoundException e)
 {
	 e.printStackTrace();
 }
 catch(SQLException e)
 {
	 e.printStackTrace();
 }
 finally
 {
	 try
	 {
		 if(preparedStatement!=null) preparedStatement.close();
		 if(connection!=null) connection.close();
	 }
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
 }
 
 
 %>
</body>
</html>