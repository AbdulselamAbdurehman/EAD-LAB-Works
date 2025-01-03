package com.itsc.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeleteBookServlet extends HttpServlet {
    private static final String query = "DELETE FROM books WHERE id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister",
                    "root", "root");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h2>Record deleted successfully.</h2>");
            } else {
                pw.println("<h2>Failed to delete record.</h2>");
            }
            pw.println("<a href='Home.html'>Home</a>");
            pw.println("<a href='booklist'>Book List</a>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }
    }
}
