package jdbc_connection;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabaseExample { 
    public static void main(String[] args) { 
        // Connection parameters
        String url = "jdbc:mysql://localhost:3306/"; 
        String username = "abdu";  // Replace with your MySQL username
        String password = "abdu1234";  // Replace with your MySQL password
        String databaseName = "new_database"; 
        
        try { 
            // Establish a connection to the MySQL server 
            Connection connection = DriverManager.getConnection(url, username, password); 
            
            // Create a statement
            Statement statement = connection.createStatement(); 
            
            // Execute the SQL query to create the new database
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName; 
            statement.executeUpdate(createDatabaseSQL);        
            System.out.println("Database '" + databaseName + "' created successfully.");
            
            // Close resources
            statement.close(); 
            connection.close(); 
            
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
}
