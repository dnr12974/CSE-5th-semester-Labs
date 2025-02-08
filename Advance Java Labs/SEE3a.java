package MyDNR;
import java.sql.*;
public class SEE3a
{
 private static final String DB_URL="jdbc:mysql://localhost:3307/";
 private static final String USER="root";
 private static final String PASS="";
 private static final String DB_NAME="BankDBk";
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
		 String createTableSQL="CREATE TABLE IF NOT EXISTS Bank_Account("+
		                       "Account_no INT PRIMARY KEY,"+
				               "Account_Name VARCHAR(50),"+
		                       "Type_of_Account VARCHAR(50),"+
				               "Balance INT)";
		 statement.executeUpdate(createTableSQL);
		 String insertDataSQL="INSERT INTO Bank_Account(Account_no,Account_Name,Type_of_Account,Balance) VALUES(?,?,?,?)";
		 preparedStatement=connection.prepareStatement(insertDataSQL);
		 preparedStatement.setInt(1,1);
		 preparedStatement.setString(2, "Rajesh");
		 preparedStatement.setString(3, "Savings");
         preparedStatement.setInt(4, 30000);
         preparedStatement.executeUpdate();
         
         preparedStatement.setInt(1,2);
		 preparedStatement.setString(2, "Suresh");
		 preparedStatement.setString(3, "Current");
         preparedStatement.setInt(4, 10000);
         preparedStatement.executeUpdate();
         
         String queryAllAccounts="SELECT * FROM Bank_Account";
         ResultSet rsAll=statement.executeQuery(queryAllAccounts);
         System.out.println("All accounts:");
         while(rsAll.next())
         {
        	 System.out.println("Account number:"+rsAll.getInt("Account_no")+
        			 ",Account Name:"+rsAll.getString("Account_Name")+
        			 ",Type of account:"+rsAll.getString("Type_of_Account")+
        			 ",Balance:"+rsAll.getInt("Balance"));
         }
         connection.setAutoCommit(false);
         try
         {
        	 preparedStatement.setInt(1,3);
    		 preparedStatement.setString(2, "Aditya");
    		 preparedStatement.setString(3, "Current");
             preparedStatement.setInt(4, 92000);
             preparedStatement.executeUpdate(); 
             
             preparedStatement.setInt(1,4);
    		 preparedStatement.setString(2, "Ajay");
    		 preparedStatement.setString(3, "Savings");
             preparedStatement.setInt(4, 99000);
             preparedStatement.executeUpdate();
             
             System.out.println("Rows inserted,commiting transactions!");
             connection.commit();
         }
         catch(SQLException e)
         {
        	 System.out.println("Exception occured,Rolling back transactions!");
        	 connection.rollback();
        	 e.printStackTrace();
         }
         Savepoint savepoint1=connection.setSavepoint("Savepoint1");
         try
         {
        	 preparedStatement.setInt(1,5);
    		 preparedStatement.setString(2, "Dheeraj");
    		 preparedStatement.setString(3, "Savings");
             preparedStatement.setInt(4, 112000);
             preparedStatement.executeUpdate(); 
             
             preparedStatement.setInt(1,6);
    		 preparedStatement.setString(2, "Freddy");
    		 preparedStatement.setString(3, "Current");
             preparedStatement.setInt(4, 47000);
             preparedStatement.executeUpdate();
             
             System.out.println("Rows inserted,Rollingback transaction to savepoint1!");
             connection.rollback(savepoint1);
             System.out.println("Rows after rollback,commiting transactions!");
             connection.commit();
         }
         catch(SQLException e)
         {
        	 System.out.println("Exception occured,rolling back transactions");
        	 connection.rollback();
        	 e.printStackTrace();
         }
         rsAll=statement.executeQuery(queryAllAccounts);
         System.out.println("All accounts after rollback and commit:");
         while(rsAll.next())
         {
        	 System.out.println("Account number:"+rsAll.getInt("Account_no")+
        			 ",Account Name:"+rsAll.getString("Account_Name")+
        			 ",Type of account:"+rsAll.getString("Type_of_Account")+
        			 ",Balance:"+rsAll.getInt("Balance"));
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
