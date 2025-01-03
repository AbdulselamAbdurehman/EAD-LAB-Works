package com.itsc.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    private final DBConnectionManager dbManager;

    public DeleteBookServlet() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.dbManager = (DBConnectionManager) context.getBean("dbConnectionManager");
        ((ClassPathXmlApplicationContext) context).close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookId = req.getParameter("id");

        String query = "DELETE FROM Books WHERE id = ?";

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try (Connection connection = dbManager.openConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, Integer.parseInt(bookId));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                out.println("<h1>Book deleted successfully</h1>");
            } else {
                out.println("<h1>Book not found</h1>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error");
        }
    }
}
