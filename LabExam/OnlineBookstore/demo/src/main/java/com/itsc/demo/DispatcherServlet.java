package com.itsc.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/app") // Catch-all pattern
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        handleRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        handleRequest(req, res);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String path = req.getPathInfo(); // Get the URL path after the root context
        System.out.println("Path: " + path + " Method: " + req.getMethod());
        // Bypass static resources (e.g., .html, .css, .js files)
        if (path != null && (path.endsWith(".html") || path.startsWith("/static/"))) {
            req.getRequestDispatcher(path).forward(req, res);
            return;
        }

        switch (path) {
            case "/":
                res.sendRedirect("/index.html"); // Redirect to the static index.html
                break;
            case "/registerBook":
                req.getRequestDispatcher("/registerBook").forward(req, res);
                break;

            case "/displayBooks":
                req.getRequestDispatcher("/displayBooks").forward(req, res);
                break;

            case "/deleteBook":
                req.getRequestDispatcher("/deleteBook").forward(req, res);
                break;

            case "/searchBooks":
                req.getRequestDispatcher("/searchBooks").forward(req, res);
                break;

            default:
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
                break;
        }
    }

}
