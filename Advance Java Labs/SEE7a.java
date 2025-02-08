package MyDNR;
import java.sql.*;
public class SEE7a 
{
 private static final String DB_URL="jdbc:mysql://localhost:3307/";
 private static final String USER="root";
 private static final String PASS="";
 private static final String DB_NAME="StudentsSEE7a";
 public static void main(String args[])
 {
	 Connection connection=null;
	 Statement statement=null;
	 try
	 {
		 connection=DriverManager.getConnection(DB_URL,USER,PASS);
		 statement=connection.createStatement();
		 String createDatabaseSQL="CREATE DATABASE IF NOT EXISTS "+DB_NAME;
		 statement.executeUpdate(createDatabaseSQL);
		 System.out.println("Database created successfully!");
		 String useDatabaseSQL="USE "+DB_NAME;
		 statement.executeUpdate(useDatabaseSQL);
		 System.out.println("Database created successfully!");
		 String createTableSQL="CREATE TABLE IF NOT EXISTS Students("+
		                       "ID INT PRIMARY KEY AUTO_INCREMENT,"+
				               "Name VARCHAR(50),"+
		                       "Department VARCHAR(50),"+
				               "CGPA DECIMAL(3,1))";
		 statement.executeUpdate(createTableSQL);
		 System.out.println("Table created successfully!");
		 String insertDataSQL="INSERT INTO Students(Name,Department,CGPA) VALUES"+
		                      "('Rajesh','CSE',8.5),"+
		                      "('Brijesh','CSE',9.3),"+
		                      "('John','CSE',8.5)";
		 statement.executeUpdate(insertDataSQL);
		 System.out.println("Data inserted successfully!");
		 String queryBelow9Students="SELECT * FROM Students WHERE CGPA<9";
		 ResultSet rsbelow9=statement.executeQuery(queryBelow9Students);
		 System.out.println("Students below 9 CGPA are:");
		 while(rsbelow9.next())
		 {
			 System.out.println("ID:"+rsbelow9.getInt("ID")+
					           ",Name:"+rsbelow9.getString("Name")+
					           ",Department:"+rsbelow9.getString("Department")+
					           ",CGPA:"+rsbelow9.getDouble("CGPA"));
		 }

		 String updateSQL="UPDATE Students SET CGPA=9.4 WHERE Name='John'";
		 int rowsUpdated=statement.executeUpdate(updateSQL);
		 System.out.println(rowsUpdated>0?"CGPA updated successfully!":"No row found with Name 'John'!");
		 String queryAllStudents="SELECT * FROM Students";
		 ResultSet rsAll=statement.executeQuery(queryAllStudents);
		 System.out.println("All Students after update: ");
		 while(rsAll.next())
		 {
			 System.out.println("ID:"+rsAll.getString("ID")+
					           ",Name:"+rsAll.getString("Name")+
					           ",Department:"+rsAll.getString("Department")+
					           ",CGPA:"+rsAll.getDouble("CGPA"));
		 } 
	 }
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	 finally
	 {
		 try
		 {
			 if(statement!=null) statement.close();
			 if(connection!=null) connection.close();
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
	 }
 }
}
