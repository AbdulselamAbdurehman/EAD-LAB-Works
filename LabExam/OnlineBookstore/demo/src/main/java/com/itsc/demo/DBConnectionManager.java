package com.itsc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DBConnectionManager {

    private String url = "jdbc:mysql://localhost:3306/BookstoreDB";
    private String username = "abdu";
    private String password = "root";

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
