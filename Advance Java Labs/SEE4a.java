package MyDNR;
import java.sql.*;
public class SEE4a 
{
  private static final String DB_URL="jdbc:mysql://localhost:3307/";
  private static final String USER="root";
  private static final String PASS="";
  private static final String DB_NAME="CountriesSEE";
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
		  String useDatabaseSQL="USE "+DB_NAME;
		  statement.executeUpdate(useDatabaseSQL);
		  System.out.println("Database created successfully!");
		  String createTableSQL="CREATE TABLE IF NOT EXISTS Countries("+
		                        "Country_Code VARCHAR(50) PRIMARY KEY,"+
				                "Capital VARCHAR(50),"+
		                        "Continent VARCHAR(50),"+
				                "Population INT)";
		  statement.executeUpdate(createTableSQL);
		  System.out.println("Table created successfully!");
		  String insertDataSQL="INSERT INTO Countries(Country_Code,Capital,Continent,Population) VALUES"+
		                       "('C1','Capital 1','Continent 1',50000000),"+
		                       "('C2','Capital 2','Continent 2',20000000),"+
		                       "('C3','Capital 3','Continent 3',90000000),"+
		                       "('C4','Capital 4','Continent 4',10000000),"+
		                       "('C5','Capital 5','Continent 5',30000000)";
		  statement.executeUpdate(insertDataSQL);
		  System.out.println("Data inserted successfully!");
		  String queryAllCountries="SELECT * FROM Countries ORDER BY Population ASC";
		  ResultSet rsAll=statement.executeQuery(queryAllCountries);
		  System.out.println("The countries ordered in ascending order of population are:");
		  System.out.printf("%-15s%-15s%-15s%-15s%n","Country Code","Capital","Continent","Population");
		  System.out.println("----------------------------------------------------------------------------");
		  while(rsAll.next())
		  {
			  System.out.printf("%-15s%-15s%-15s%-15d%n",
					            rsAll.getString("Country_Code"),
					            rsAll.getString("Capital"),
					            rsAll.getString("Continent"),
					            rsAll.getInt("Population"));
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
