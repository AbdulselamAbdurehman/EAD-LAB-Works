package com.itsc.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

@WebServlet("/registerBook")
public class BookRegistrationServlet extends HttpServlet {

    private final DBConnectionManager dbManager;

    public BookRegistrationServlet() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.dbManager = (DBConnectionManager) context.getBean("dbConnectionManager");
        ((ClassPathXmlApplicationContext) context).close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        double price = Double.parseDouble(req.getParameter("price"));

        String query = "INSERT INTO Books (title, author, price) VALUES (?, ?, ?)";

        try (Connection connection = dbManager.openConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setDouble(3, price);

            int rows = ps.executeUpdate();

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            if (rows > 0) {
                out.println("<h1>Book Registered Successfully</h1>");
            } else {
                out.println("<h1>Book Registration Failed</h1>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error");
        }
    }
}
