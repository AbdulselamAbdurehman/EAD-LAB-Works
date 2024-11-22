package com.itsc;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AddServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Retrieve values of num1 and num2 from the request
        String num1Param = req.getParameter("num1");
        String num2Param = req.getParameter("num2");
        
        // Initialize response writer
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        try {
            // Convert parameters to integers
            int n1 = Integer.parseInt(num1Param);
            int n2 = Integer.parseInt(num2Param);
            
            // Perform the addition and send the result in the response
            int sum = n1 + n2;
            pw.println("<html><body>");
            pw.println("<h1>Sum: " + sum + "</h1>");
            pw.println("</body></html>");
        } catch (NumberFormatException e) {
            // Handle invalid number format
            pw.println("<html><body>");
            pw.println("<h1>Error: Please enter valid numbers for both fields.</h1>");
            pw.println("</body></html>");
        }
    }
}
