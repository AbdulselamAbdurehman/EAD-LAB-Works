package jdbc_connection;

import java.sql.*;

public class JDBCDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/Studentdb";
			String username = "abdu";
			String password = "abdu1234";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Established Connection");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
