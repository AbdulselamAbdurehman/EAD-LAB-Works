package jdbc_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementWithResultSet {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/teachersdb";
        String username = "abdu";
        String pwd = "abdu1234";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, pwd);
             Statement statement = connection.createStatement()) {

            // SQL query to create a new table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS teacher1 (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VARCHAR(255)," +
                    "last_name VARCHAR(255)," +
                    "school VARCHAR(255)," +
                    "hire_date DATE," +
                    "salary DECIMAL(10, 2))";

            // Execute the SQL statement
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'teacher1' created successfully.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
