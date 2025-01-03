// Answers

// 1. CREATE DATABASE BookstoreDB;
// 2.

// package com.itsc;

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.Statement;

// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// @WebServlet("/init")
// public class DBSetup extends HttpServlet {

// private static final String createDatabaseQuery = "CREATE Database
// BookstoreDB;";
// private static final String createTableQuery = "CREATE TABLE Books( id INT
// AUTOINCREMENT PRIMARY KEY, title VARCHAR, author VARCHAR, price DOUBLE)";

// protected void doGet(HttpServletRequest req, HttpServletResponse res) throws
// IOException {

// res.setContentType("text/html");
// PrintWriter pw = res.getWriter();

// pw.println("<h1>hi</h1>");
// this.initDB(pw);
// this.createTable(pw);

// }

// public void initDB(PrintWriter pw) {

// String url = "jdbc:mysql://localhost:3306";
// String user = "abdu";
// String pwd = "root";

// try {
// Class.forName("com.mysql.cj.jdbc.Driver");
// Connection conn = DriverManager.getConnection(url, user, pwd);
// if (conn != null) {

// Statement ps = conn.prepareStatement(createDatabaseQuery);
// ps.executeQuery();
// System.out.println("Database successfully created");
// pw.println("Database successfully created");

// } else {
// System.out.println("Failed to connect");
// pw.println("Failed to connect");

// }
// } catch (Exception e) {
// System.out.println(e.getMessage());
// }
// }

// public void createTable(PrintWriter pw) {

// String url = "jdbc:mysql://localhost:3306///BookstoreDB";
// String user = "abdu";
// String pwd = "root";

// try {
// Class.forName("com.mysql.cj.jdbc.Driver");
// Connection conn = DriverManager.getConnection(url, user, pwd);

// if (conn != null) {

// Statement ps = conn.prepareStatement(createTableQuery);
// ps.executeQuery();
// System.out.println("Books Table Created Successfully!");
// pw.println("Books Table Created Successfully!");

// } else {
// System.out.println("Failed to connect");
// pw.println("Failed to connect");
// }
// } catch (Exception e) {
// System.out.println(e.getMessage());
// }
// }

// }