package MyDNR;
import java.sql.*;
public class SEE8a 
{
  private static final String DB_URL="jdbc:mysql://localhost:3307/";
  private static final String USER="root";
  private static final String PASS="";
  private static final String DB_NAME="StudentDBSEE";
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
          
          String createTableSQL="CREATE TABLE IF NOT EXISTS Students("+
          "USN VARCHAR(20) PRIMARY KEY,"+
          "Student_Name VARCHAR(50),"+
          "Semester INT,"+
          "Course VARCHAR(50),"+
          "Grade CHAR(1))";
          statement.executeUpdate(createTableSQL);
          System.out.println("Table created successfully!!!");
          
          String insertDataSQL="INSERT INTO Students(USN,Student_Name,Semester,Course,Grade) VALUES"+
          "('1RN20CS001','Ram',5,'Computer Science','A'),"+
          "('1RN20CS002','Shyam',6,'Electronics','B'),"+
          "('1RN20CS003','Ravi',4,'Mechanical','C'),"+
          "('1RN20CS004','Amit',3,'Civil','A'),"+
          "('1RN20CS005','Raj',2,'Electrical','B')";
          statement.executeUpdate(insertDataSQL);
          System.out.println("Data inserted successfully!!!");
          
          String queryAllStudents="SELECT * FROM Students";
          ResultSet rsAll=statement.executeQuery(queryAllStudents);
          System.out.println("All Students:");
          while(rsAll.next())
          {
              System.out.println("USN:"+rsAll.getString("USN")+
                                 ", Name:"+rsAll.getString("Student_Name")+
                                 ", Semester:"+rsAll.getInt("Semester")+
                                 ", Course:"+rsAll.getString("Course")+
                                 ", Grade:"+rsAll.getString("Grade"));
          }
          
          String updateSQL="UPDATE Students SET Grade=? WHERE Student_Name=?";
          preparedStatement=connection.prepareStatement(updateSQL);
          preparedStatement.setString(1,"S");
          preparedStatement.setString(2,"Ram");
          int rowsUpdated=preparedStatement.executeUpdate();
          System.out.println(rowsUpdated>0?"Grade updated successfully!":"No student found with name Ram");
          
          rsAll=statement.executeQuery(queryAllStudents);
          System.out.println("Students after grade update:");
          while(rsAll.next())
          {
              System.out.println("USN:"+rsAll.getString("USN")+
                                 ", Name:"+rsAll.getString("Student_Name")+
                                 ", Semester:"+rsAll.getInt("Semester")+
                                 ", Course:"+rsAll.getString("Course")+
                                 ", Grade:"+rsAll.getString("Grade"));
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
