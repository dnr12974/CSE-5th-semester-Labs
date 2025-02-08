package MyDNR;
import java.sql.*;
import java.util.Scanner;

public class SEE10a {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB_NAME = "StudentsDBSEE";

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
            String createTableSQL = "CREATE TABLE IF NOT EXISTS student ("
                    + "USN VARCHAR(15) PRIMARY KEY, "
                    + "Name VARCHAR(50), "
                    + "Branch VARCHAR(10), "
                    + "Admission_Type VARCHAR(10))";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created successfully!!!");

            // Insert Multiple Students
         
            
            
            String insertDataSQL = "INSERT INTO student (USN, Name, Branch, Admission_Type) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertDataSQL);

            while (true) {
                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter USN: ");
                String usn = scanner.nextLine();
                System.out.print("Enter Branch: ");
                String branch = scanner.nextLine();
                System.out.print("Enter Admission Type (CET/COMED_K): ");
                String admissionType = scanner.nextLine();

                preparedStatement.setString(1, usn);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, branch);
                preparedStatement.setString(4, admissionType);
                preparedStatement.executeUpdate();
                System.out.println("Data inserted successfully!!!");

                // Ask if user wants to add more students
                System.out.print("Do you want to add another student? (yes/no): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("no")) {
                    break; // Exit loop if user says "no"
                }
            }

            // Query to get students admitted through CET and branch is CSE
            String queryCSECETStudents = "SELECT USN, Name FROM student WHERE Admission_Type='CET' AND Branch='CSE'";
            ResultSet rsCSECET = statement.executeQuery(queryCSECETStudents);

            System.out.println("\nStudents Admitted through CET in CSE Branch:");
            boolean found = false;
            while (rsCSECET.next()) {
                found = true;
                System.out.println("USN: " + rsCSECET.getString("USN") + ", Name: " + rsCSECET.getString("Name"));
            }
            if (!found) {
                System.out.println("No students found.");
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
