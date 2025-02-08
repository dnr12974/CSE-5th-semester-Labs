package MyDNR;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class SEE1a 
{
 private static final String DB_URL="jdbc:mysql://localhost:3307/";
 private static final String USER="root";
 private static final String PASS="";
 private static final String DB_NAME="DepartmentSEE1aFinal";
 public static void main(String args[])
 {
	 Connection connection=null;
	 Statement statement=null;
	 PreparedStatement preparedStatement=null;
	 Scanner scanner=new Scanner(System.in);
	 try
	 {
		 connection=DriverManager.getConnection(DB_URL,USER,PASS);
		 statement=connection.createStatement();
		 String createDatabaseSQL="CREATE DATABASE IF NOT EXISTS "+DB_NAME;
		 statement.executeUpdate(createDatabaseSQL);
		 String useDatabaseSQL="USE "+DB_NAME;
		 statement.executeUpdate(useDatabaseSQL);
		 System.out.println("Database created successfully!!!");
		 String createTableSQL="CREATE TABLE IF NOT EXISTS Departments("+
		 "Dept_ID INT PRIMARY KEY,"+
		 "Name VARCHAR(50),"+
		 "Year_Established INT,"+
		 "Head_Name VARCHAR(50),"+
		 "No_of_Employees INT)";
		 statement.executeUpdate(createTableSQL);
		 System.out.println("Table created successfully!!!");
		 String insertDataSQL="INSERT INTO Departments(Dept_ID,Name,Year_Established,Head_Name,No_of_Employees) VALUES"+
		                      "(1,'Physics',2001,'Rajesh Kumar',35),"+
		                      "(2,'Biology',2011,'Suresh Rai',30),"+
		                      "(3,'Chemistry',2018,'Rahul K',21),"+
		                      "(4,'Maths',2004,'Mohan Reddy',19),"+
		                      "(5,'History',2011,'Rohan Gandhi',30)";
		 statement.executeUpdate(insertDataSQL);
		 System.out.println("Data inserted successfully!!!");
		 String queryAllDepartments="SELECT * FROM Departments";
		 ResultSet rsAll=statement.executeQuery(queryAllDepartments);
		System.out.println("All departments:");
		while(rsAll.next())
		{
			System.out.println("Dept_ID:"+rsAll.getInt("Dept_ID")+
					           ",Name:"+rsAll.getString("Name")+
					           ",Year Established:"+rsAll.getInt("Year_Established")+
					           ",Head Name:"+rsAll.getString("Head_Name")+
					           ",Number of employees:"+rsAll.getInt("No_of_Employees"));
		}
		System.out.println("Enter the year to filter departments:");
		int year=scanner.nextInt();
		String queryYearDepartments="SELECT * FROM Departments WHERE Year_Established=? ";
		PreparedStatement pstmtYear=connection.prepareStatement(queryYearDepartments);
		pstmtYear.setInt(1, year);
		ResultSet rsYear=pstmtYear.executeQuery();
		if(!rsYear.isBeforeFirst())
		{
			System.out.println("NO department found with year "+year);
		}
		else
		{
			System.out.println("Department found with year "+year);
			while(rsYear.next())
			{
				System.out.println("Dept_ID:"+rsYear.getInt("Dept_ID")+
						           ",Name:"+rsYear.getString("Name")+
						           ",Year Established:"+rsYear.getInt("Year_Established")+
						           ",Head Name:"+rsYear.getString("Head_Name")+
						           ",Number of employees:"+rsYear.getInt("No_of_Employees"));
			}
		}
		System.out.println("Enter department id to filter:");
		int deptid=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter name of department to filter:");
		String deptName=scanner.nextLine();
		String queryDeptDetails="SELECT * FROM Departments WHERE Dept_ID=? AND Name=?";
		PreparedStatement pstmtDeptDetails=connection.prepareStatement(queryDeptDetails);
		pstmtDeptDetails.setInt(1, deptid);
		pstmtDeptDetails.setString(2, deptName);
		ResultSet rsDeptDetails=pstmtDeptDetails.executeQuery();
		if(!rsDeptDetails.isBeforeFirst())
		{
			System.out.println("NO department found with Department id:"+deptid+" and "+"Department name:"+deptName);
		}
		else
		{
			System.out.println("Department found with  Department id:"+deptid+" and "+"Department name:"+deptName);
			while(rsDeptDetails.next())
			{
				System.out.println("Dept_ID:"+rsDeptDetails.getInt("Dept_ID")+
						           ",Name:"+rsDeptDetails.getString("Name")+
						           ",Year Established:"+rsDeptDetails.getInt("Year_Established")+
						           ",Head Name:"+rsDeptDetails.getString("Head_Name")+
						           ",Number of employees:"+rsDeptDetails.getInt("No_of_Employees"));
			}
		}
		System.out.println("Insert new department:");
		System.out.println("Enter new Dept_ID:");
		int newDeptid=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter new Name:");
		String newDeptName=scanner.nextLine();
		System.out.println("Enter new year established:");
		int newYearEstablished=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter new Head name:");
		String newHeadName=scanner.nextLine();
		System.out.println("Enter new no of employees:");
		int newNoOfEmployees=scanner.nextInt();
		String insertDeptSQL="INSERT INTO Departments(Dept_ID,Name,Year_Established,Head_Name,No_of_Employees) VALUES (?,?,?,?,?)";
		PreparedStatement pstmtInsert=connection.prepareStatement(insertDeptSQL);
		pstmtInsert.setInt(1, newDeptid);
		pstmtInsert.setString(2, newDeptName);
		pstmtInsert.setInt(3,newYearEstablished);
		pstmtInsert.setString(4, newHeadName);
		pstmtInsert.setInt(5, newNoOfEmployees);
		pstmtInsert.executeUpdate();
		System.out.println("New department inserted successfully!!!");
		String queryNewDepartment="SELECT * FROM Departments WHERE Dept_ID=?";
		PreparedStatement pstmtNewDepartment=connection.prepareStatement(queryNewDepartment);
		pstmtNewDepartment.setInt(1, newDeptid);
		ResultSet rsNewDept=pstmtNewDepartment.executeQuery();
		System.out.println("Inserted Department Details");
		while(rsNewDept.next())
		{
			System.out.println("Dept_ID:"+rsNewDept.getInt("Dept_ID")+
					           ",Name:"+rsNewDept.getString("Name")+
					           ",Year Established:"+rsNewDept.getInt("Year_Established")+
					           ",Head Name:"+rsNewDept.getString("Head_Name")+
					           ",Number of employees:"+rsNewDept.getInt("No_of_Employees"));
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
			 if(preparedStatement!=null) preparedStatement.close();
			 scanner.close();
			 
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
	 }
 }
}
