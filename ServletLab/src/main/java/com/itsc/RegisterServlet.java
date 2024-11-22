package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// @WebServlet("/register")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String query = "INSERT INTO books(bookname, bookedition, bookprice) VALUES(?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        // Load the MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            pw.println("<h1>Error: Unable to load MySQL Driver.</h1>");
            return;
        }

        // Retrieve form data
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

        // Database connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookregister", "root", "root");
                PreparedStatement ps = conn.prepareStatement(query)) {

            // Set parameters
            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);

            // Execute update
            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h2 class='text-success'>Book registered successfully.</h2>");
            } else {
                pw.println("<h2 class='text-danger'>Book registration failed.</h2>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        }

        // Close the writer
        pw.close();
    }
}
