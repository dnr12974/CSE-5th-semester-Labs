package MyDNR;
import java.sql.*;

public class SEE9a {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB_NAME = "Registration";

    public static void main(String args[]) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            
            // Create Database
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            statement.executeUpdate(createDatabaseSQL);
            
            // Use the Database
            String useDatabaseSQL = "USE " + DB_NAME;
            statement.executeUpdate(useDatabaseSQL);
            System.out.println("Database created successfully!!!");
            
            // Create Table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS student(" +
                    "Roll_No INT PRIMARY KEY AUTO_INCREMENT," +
                    "Name VARCHAR(50)," +
                    "Program VARCHAR(50)," +
                    "Year_of_Admission INT)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created successfully!!!");
            
            // Insert Sample Data
            String insertDataSQL = "INSERT INTO student (Name, Program, Year_of_Admission) VALUES" +
                    "('Ram', 'BE', 2023)," +
                    "('Shyam', 'BSc', 2022)," +
                    "('Ravi', 'BE', 2021)," +
                    "('Amit', 'BE', 2023)," +
                    "('Raj', 'MCA', 2020)";
            statement.executeUpdate(insertDataSQL);
            System.out.println("Data inserted successfully!!!");
            
            // Query 1: List name and roll number of students enrolled in 2023
            String queryStudents2023 = "SELECT Roll_No, Name FROM student WHERE Year_of_Admission = 2023";
            resultSet = statement.executeQuery(queryStudents2023);
            System.out.println("Students enrolled in 2023:");
            while (resultSet.next()) {
                System.out.println("Roll_No: " + resultSet.getInt("Roll_No") + ", Name: " + resultSet.getString("Name"));
            }
            
            // Query 2: List Roll-No and Name of all students in the BE Program
            String queryBEStudents = "SELECT Roll_No, Name FROM student WHERE Program = 'BE'";
            resultSet = statement.executeQuery(queryBEStudents);
            System.out.println("\nStudents in BE Program:");
            while (resultSet.next()) {
                System.out.println("Roll_No: " + resultSet.getInt("Roll_No") + ", Name: " + resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                if (preparedStatement != null) preparedStatement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
