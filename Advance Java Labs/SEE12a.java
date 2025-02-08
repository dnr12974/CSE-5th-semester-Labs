package MyDNR;
import java.sql.*;
import java.util.Scanner;

public class SEE12a {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB_NAME = "SEE12aDB1";

    public static void main(String args[]) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            // Create Database
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            statement.executeUpdate(createDatabaseSQL);
            String useDatabaseSQL = "USE " + DB_NAME;
            statement.executeUpdate(useDatabaseSQL);
            System.out.println("Database created and selected successfully!!!");

            // Create Table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Employee ("
                    + "EmpID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "EmpName VARCHAR(50), "
                    + "Designation VARCHAR(50), "
                    + "Department VARCHAR(50), "
                    + "Project VARCHAR(50), "
                    + "Salary DOUBLE)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created successfully!!!");

            // Insert Data
            String insertDataSQL = "INSERT INTO Employee (EmpName, Designation, Department, Project, Salary) VALUES "
                    + "('Rajesh', 'Manager', 'HR', 'ProjectA', 60000),"
                    + "('Rahul', 'Developer', 'IT', 'ProjectB', 75000),"
                    + "('Sohail', 'Technician', 'Support', 'ProjectA', 40000),"
                    + "('Alok', 'Analyst', 'Finance', 'ProjectC', 55000),"
                    + "('Rishikesh', 'Intern', 'Marketing', 'ProjectB', 30000)";
            statement.executeUpdate(insertDataSQL);
            System.out.println("Data inserted successfully!!!");

            // Delete Employees by Project
            System.out.print("Enter the project name to delete employees from: ");
            String projectName = scanner.nextLine();
            String deleteSQL = "DELETE FROM Employee WHERE Project = ?";
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, projectName);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println(rowsDeleted + " record(s) deleted successfully.");

            // Retrieve and display employees sorted by salary in descending order
            String querySortedEmployees = "SELECT * FROM Employee ORDER BY Salary DESC";
            ResultSet rsSortedEmployees = statement.executeQuery(querySortedEmployees);

            System.out.println("\nEmployees sorted by salary in descending order:");
            while (rsSortedEmployees.next()) {
                System.out.println("ID: " + rsSortedEmployees.getInt("EmpID") + ", Name: " + rsSortedEmployees.getString("EmpName") + ", Designation: " + rsSortedEmployees.getString("Designation") + ", Department: " + rsSortedEmployees.getString("Department") +",Project: "+rsSortedEmployees.getString("Project")+", Salary: " + rsSortedEmployees.getDouble("Salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                if (preparedStatement != null) preparedStatement.close();
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
