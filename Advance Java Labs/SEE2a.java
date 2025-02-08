package MyDNR;
import java.sql.*;
public class SEE2a 
{
  private static final String DB_URL="jdbc:mysql://localhost:3307/";
  private static final String USER="root";
  private static final String PASS="";
  private static final String DB_NAME="MoviesSEE2a";
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
		  System.out.println("Database created successfully!!!");
		  String createTableSQL="CREATE TABLE IF NOT EXISTS Movies("+
		  "ID INT PRIMARY KEY AUTO_INCREMENT,"+
		  "Movie_Name VARCHAR(50),"+
		  "Genre VARCHAR(50),"+
		  "IMDB_Rating DECIMAL(3,1),"+
		  "Year INT)";
		  statement.executeUpdate(createTableSQL);
		  System.out.println("Table created successfully!!!");
		  String insertDataSQL="INSERT INTO Movies(Movie_Name,Genre,IMDB_Rating,Year) VALUES"+
		  "('Movie 1','Action',3.4,2011),"+
		  "('Movie 2','Sci-fi',7.8,2012),"+
		  "('Movie 3','Romance',8.2,2022),"+
		  "('Movie 4','Action',8.9,2021),"+
		  "('Movie 5','Sci-fi',9.1,2025)";
		  statement.executeUpdate(insertDataSQL);
		  System.out.println("Data inserted successfully!!!");
		  String queryAllMovies="SELECT * FROM Movies";
		  ResultSet rsAll=statement.executeQuery(queryAllMovies);
		  System.out.println("All movIes:");
		  while(rsAll.next())
		  {
			  System.out.println("ID:"+rsAll.getInt("ID")+
					             ",Movie name:"+rsAll.getString("Movie_Name")+
					             ",Genre:"+rsAll.getString("Genre")+
					             ",IMDB_Rating:"+rsAll.getDouble("IMDB_Rating")+
					             ",Year:"+rsAll.getInt("Year"));
		  }  
		  rsAll.absolute(5);
		  System.out.println("5th Movie:");
		  System.out.println("ID:"+rsAll.getInt("ID")+
		             ",Movie name:"+rsAll.getString("Movie_Name")+
		             ",Genre:"+rsAll.getString("Genre")+
		             ",IMDB_Rating:"+rsAll.getDouble("IMDB_Rating")+
		             ",Year:"+rsAll.getInt("Year"));
		  String insertMovieSQL="INSERT INTO Movies(Movie_Name,Genre,IMDB_Rating,Year) VALUES(?,?,?,?)";
		  preparedStatement=connection.prepareStatement(insertMovieSQL);
		  preparedStatement.setString(1,"Movie 6");
		  preparedStatement.setString(2,"Sci-fi");
		  preparedStatement.setDouble(3,8.9);
		  preparedStatement.setInt(4,2011);
		  preparedStatement.executeUpdate();
		  System.out.println("New Movie inserted successfully!");
		  rsAll=statement.executeQuery(queryAllMovies);
		  System.out.println("Movies after insertion:");
		  while(rsAll.next())
		  {
			  System.out.println("ID:"+rsAll.getInt("ID")+
					             ",Movie name:"+rsAll.getString("Movie_Name")+
					             ",Genre:"+rsAll.getString("Genre")+
					             ",IMDB_Rating:"+rsAll.getDouble("IMDB_Rating")+
					             ",Year:"+rsAll.getInt("Year"));
		  } 
		  String deleteMovieSQL="DELETE FROM Movies WHERE IMDB_Rating<5";
		  int rowsDeleted=statement.executeUpdate(deleteMovieSQL);
		  System.out.println("Rows deleted with IMDB_Rating<5.Number of rows deleted="+rowsDeleted);
		  String updateSQL="UPDATE Movies SET Genre=? WHERE ID=?";
		  preparedStatement=connection.prepareStatement(updateSQL);
		  preparedStatement.setString(1,"Sci-fi");
		  preparedStatement.setInt(2, 10);
		  int rowsUpdated=preparedStatement.executeUpdate();
		  System.out.println(rowsUpdated>0?"Rows updated successfully!":"No movie found with ID=10");
		  rsAll=statement.executeQuery(queryAllMovies);
		  System.out.println("Movies after deletion and updation:");
		  while(rsAll.next())
		  {
			  System.out.println("ID:"+rsAll.getInt("ID")+
					             ",Movie name:"+rsAll.getString("Movie_Name")+
					             ",Genre:"+rsAll.getString("Genre")+
					             ",IMDB_Rating:"+rsAll.getDouble("IMDB_Rating")+
					             ",Year:"+rsAll.getInt("Year"));
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
		  }
		  catch(SQLException e)
		  {
			  e.printStackTrace();
		  }
	  }
	  
  }
}
