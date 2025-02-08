package MyDNR;
import java.sql.*;
import java.util.Scanner;

public class SEE11a {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB_NAME = "SEE11aDB";

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
                    + "Salary DOUBLE)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created successfully!!!");

            // Insert Data
            String insertDataSQL = "INSERT INTO Employee (EmpName, Designation, Department, Salary) VALUES "
                    + "('Rajesh', 'Manager', 'HR', 60000),"
                    + "('Rahul', 'Developer', 'IT', 75000),"
                    + "('Sohail', 'Technician', 'Support', 40000),"
                    + "('Alok', 'Analyst', 'Finance', 55000),"
                    + "('Rishikesh', 'Intern', 'Marketing', 30000)";
            statement.executeUpdate(insertDataSQL);
            System.out.println("Data inserted successfully!!!");

            // Query to get employees with salary above 50,000
            String queryHighSalaryEmployees = "SELECT EmpID, EmpName, Designation, Department, Salary FROM Employee WHERE Salary > 50000";
            ResultSet rsHighSalary = statement.executeQuery(queryHighSalaryEmployees);

            System.out.println("\nEmployees with Salary above Rs. 50,000:");
            System.out.printf("%-15s %-15s %-15s %-15s %-15s\n", "ID", "Name", "Designation", "Department", "Salary");
            System.out.println("---------------------------------------------------------------------------");
            while (rsHighSalary.next()) {
                System.out.printf("%-15d %-15s %-15s %-15s %-15.2f\n", rsHighSalary.getInt("EmpID"), rsHighSalary.getString("EmpName"), rsHighSalary.getString("Designation"), rsHighSalary.getString("Department"), rsHighSalary.getDouble("Salary"));
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
