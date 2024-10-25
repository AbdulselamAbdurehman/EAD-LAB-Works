package jdbc_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Studentdb";
        String username = "abdu"; 
        String password = "abdu1234";
        
        try {
            // Connect to MySQL Server
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Task 1: Create Database
            statement.execute("CREATE DATABASE IF NOT EXISTS Studentdb");
            connection.setCatalog("Studentdb");  // Set the database context
            // Create table
            statement.execute("CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY, " +
                    "firstname VARCHAR(255), " +
                    "lastname VARCHAR(255), " +
                    "grade INT)");

            // Task 2: Insert Data
            insertSampleData(connection, statement);

            // Task 3: Retrieve Data
            retrieveData(connection);

            // Task 4: Update Data
            updateStudentName(connection, 1, "UpdatedFirstName");

            // Task 5: Delete Data
            deleteStudent(connection, 2);

            // Task 6: Calculate Average Grade
            calculateAverageGrade(connection);

            // Close the resources
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertSampleData(Connection connection, Statement statement) {
        try {
            // Insert a single row
            PreparedStatement insertSingle = connection.prepareStatement("INSERT INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)");
            insertSingle.setInt(1, 1);
            insertSingle.setString(2, "John");
            insertSingle.setString(3, "Doe");
            insertSingle.setInt(4, 90);
            insertSingle.executeUpdate();

            // Insert ten more rows with different data
            String[] insertStatements = {
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (2, 'Jane', 'Smith', 85)",
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (3, 'Jim', 'Brown', 78)",
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (4, 'Jake', 'White', 92)",
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (5, 'Jill', 'Black', 88)",
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (6, 'Jack', 'Green', 76)",
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (7, 'Jerry', 'Yellow', 95)",
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (8, 'Janet', 'Purple', 82)",
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (9, 'Jordan', 'Cyan', 84)",
                "INSERT INTO students (id, firstname, lastname, grade) VALUES (10, 'Jessica', 'Magenta', 89)"
            };

            for (String stmt : insertStatements) {
                statement.executeUpdate(stmt);
            }
            System.out.println("Data inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void retrieveData(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students LIMIT 5");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int grade = resultSet.getInt("grade");
                System.out.println("ID: " + id + ", Name: " + firstname + " " + lastname + ", Grade: " + grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateStudentName(Connection connection, int id, String newFirstName) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE students SET firstname = ? WHERE id = ?");
            updateStatement.setString(1, newFirstName);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
            System.out.println("Updated student with ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Connection connection, int id) {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
            System.out.println("Deleted student with ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void calculateAverageGrade(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT AVG(grade) AS average_grade FROM students");

            if (resultSet.next()) {
                double averageGrade = resultSet.getDouble("average_grade");
                System.out.println("Average Grade: " + averageGrade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
