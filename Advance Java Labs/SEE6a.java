package MyDNR;
import java.sql.*;
public class SEE6a
{
private static final String DB_URL="jdbc:mysql://localhost:3307/";
private static final String USER="root";
private static final String PASS="";
private static final String DB_NAME="EmployeesDB";
public static void main(String args[])
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
		String createTableSQL="CREATE TABLE IF NOT EXISTS Employees("+
		                      "ID INT PRIMARY KEY AUTO_INCREMENT,"+
				              "Employee_Name VARCHAR(50),"+
		                      "Designation VARCHAR(50),"+
				              "Department VARCHAR(50),"+
		                      "Salary DECIMAL(10,2))";
		statement.executeUpdate(createTableSQL);
		System.out.println("Table created successfully!");
		String insertDataSQL="INSERT INTO Employees(Employee_Name,Designation,Department,Salary) VALUES"+
		                     "('Rajesh','Assistant Professor','CSE',45000),"+
		                     "('Suresh','Assosiate Professor','ECE',25000),"+
		                     "('Rahul','Assistant Professor','CSE',75000)";
		statement.executeUpdate(insertDataSQL);
		System.out.println("Data inserted successfully!");
		String updateSQL="UPDATE Employees SET Designation=? WHERE Designation=?";
		preparedStatement=connection.prepareStatement(updateSQL);
		preparedStatement.setString(1, "Assosiate Professor");
		preparedStatement.setString(2, "Assistant Professor");
		int rowsUpdated=preparedStatement.executeUpdate();
		System.out.println(rowsUpdated>0?"Designation updated sucessfully!":"No row found with designation 'Assistant Professor'");
		
		
		String queryAllEmployees="SELECT * FROM Employees";
		ResultSet rsAll=statement.executeQuery(queryAllEmployees);
		System.out.println("All Employees after update:");
		while(rsAll.next())
		{
			System.out.println("ID:"+rsAll.getInt("ID")+
					          ",Name:"+rsAll.getString("Employee_Name")+
					          ",Designation:"+rsAll.getString("Designation")+
					          ",Department:"+rsAll.getString("Department")+
					          ",Salary:"+rsAll.getDouble("Salary"));
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
