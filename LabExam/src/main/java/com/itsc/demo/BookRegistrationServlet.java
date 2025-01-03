package com.itsc.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class BookRegistrationServlet extends HttpServlet {
    private static final String query = "INSERT INTO books(bookname, bookedition, bookprice) VALUES(?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister",
                    "root", "root");
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);

            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h2>Book registered successfully.</h2>");
            } else {
                pw.println("<h2>Book not registered successfully.</h2>");
            }
            pw.println("<a href='Home.html'>Home</a>");
            pw.println("<a href='booklist'>Book List</a>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }
    }
}