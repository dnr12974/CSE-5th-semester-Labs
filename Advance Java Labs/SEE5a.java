package MyDNR;
import java.sql.*;
public class SEE5a 
{
 private static final String DB_URL="jdbc:mysql://localhost:3307/";
 private static final String USER="root";
 private static final String PASS="";
 private static final String DB_NAME="SubjectsSEE";
 public static void main(String agrs[])
 {
	 Connection connection=null;
	 Statement statement=null;
	 PreparedStatement preparedStatement=null;
	 try
	 {
		 connection=DriverManager.getConnection(DB_URL,USER,PASS);
		 statement=connection.createStatement();
		 String createDatabaseSQL="CREATE DATABASE IF NOT EXISTS "+DB_NAME;
		 statement.executeUpdate(createDatabaseSQL);
		 String useDatabaseSQL="USE "+DB_NAME;
		 statement.executeUpdate(useDatabaseSQL);
		 System.out.println("Database created successfully!");
		 String createTableSQL="CREATE TABLE IF NOT EXISTS Subjects("+
		                      "Code VARCHAR(50) PRIMARY KEY,"+
				              "Name VARCHAR(50),"+
		                      "Department VARCHAR(50),"+
				              "Credits INT)";
		 statement.executeUpdate(createTableSQL);
		 System.out.println("Table created sucessfully!");
		 String insertDataSQL="INSERT INTO Subjects(Code,Name,Department,Credits) VALUES"+
		                      "('CSL56','Java Programming Laboratory','CSE',1),"+
		                      "('CSL57','System Programming','CSE',2),"+
		                      "('CSL58','Data Structures','CSE',3)";
		 statement.executeUpdate(insertDataSQL);
		 System.out.println("Data inserted successfully!");
		 String updateSQL="UPDATE Subjects SET Name=? WHERE Code=?";
		 preparedStatement=connection.prepareStatement(updateSQL);
		 preparedStatement.setString(1, "Advanced Java Programming Lab");
		 preparedStatement.setString(2, "CSL56");
		 int rowsUpdated=preparedStatement.executeUpdate();
		 System.out.println(rowsUpdated>0?"Subject updated successfuly!":"No subject found with code 'CSL56'");
		 String deleteSQL="DELETE FROM Subjects WHERE Name=?";
		 preparedStatement=connection.prepareStatement(deleteSQL);
		 preparedStatement.setString(1, "System Programming");
		 int rowsDeleted=preparedStatement.executeUpdate();
		 System.out.println(rowsDeleted>0?"Row deleted successfully!":"no row found with subject name 'System Programming'");
		 String queryAllSubjects="SELECT * FROM Subjects";
		 ResultSet rsAll=statement.executeQuery(queryAllSubjects);
		 System.out.println("All subjects:");
		 while(rsAll.next())
		 {
			 System.out.println("Code:"+rsAll.getString("Code")+
					           ",Name:"+rsAll.getString("Name")+
					           ",Department:"+rsAll.getString("Department")+
					           ",Credits:"+rsAll.getInt("Credits"));
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
			 if(preparedStatement!=null) preparedStatement.close();
			 if(connection!=null) connection.close();
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
	
	 }
 }
}
