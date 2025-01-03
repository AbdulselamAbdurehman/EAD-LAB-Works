package com.itsc.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/displayBooks")
public class DisplayBooksServlet extends HttpServlet {

    private final DBConnectionManager dbManager;

    public DisplayBooksServlet() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.dbManager = (DBConnectionManager) context.getBean("dbConnectionManager");
        ((ClassPathXmlApplicationContext) context).close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String query = "SELECT * FROM Books";

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try (Connection connection = dbManager.openConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            out.println("<html><body>");
            out.println("<h1>Books in the Store</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");

            while (rs.next()) {
                out.printf("<tr><td>%d</td><td>%s</td><td>%s</td><td>%.2f</td></tr>",
                        rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getDouble("price"));
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error");
        }
    }
}
