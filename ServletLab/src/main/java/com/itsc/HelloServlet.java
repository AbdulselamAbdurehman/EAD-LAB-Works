package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class HelloServlet
// */
//@WebServlet("/hello")
//public class HelloServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public HelloServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//				response.getWriter().append("Hello Word").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}

//@WebServlet("/servlet")
//public class HelloServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        PrintWriter pw = res.getWriter();
//        String userName = req.getParameter("username");
//        pw.println("Hello, " + userName);
//    }
//}



//
//@WebServlet("/servlet")
//public class HelloServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        PrintWriter pw = res.getWriter();
//        String userName = req.getParameter("username");
//        int age = Integer.parseInt(req.getParameter("age"));
//        pw.printf("%s is %d years old", userName, age);
//    }
//}



//@WebServlet("/servlet")
//public class HelloServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType("text/html");
//        PrintWriter pw = resp.getWriter();
//        String fname = req.getParameter("first-name");
//        if (fname == null) {
//            fname = "Guest";
//        }
//
//        pw.println("<html><body>");
//        pw.println("<h1>Hello, " + fname + "</h1>");
//        pw.println("</body></html>");
//    }
//}



//@WebServlet("/servlet")
//public class HelloServlet extends HttpServlet {
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
//	resp.setContentType("text/html");
//	PrintWriter pw = resp.getWriter();
//	//retrieve form parameters
//	String username = req.getParameter("username");
//	String email = req.getParameter("email");
//	//send a personalized response
//	pw.println("<html><body>");
//	pw.println("<h1> Form Submitted</h1>");
//	pw.println("<p>Username: " + username + "</p>");
//	pw.println("<p>Email: " + email + "</p>");
//	pw.println("<p>Email: <a href='mailto:" + email + "'>" + email +
//	"</a></p>");
//	pw.println("</body></html>");
//}
//
//}



@WebServlet("/servlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            req.getRequestDispatcher("/index.html").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}



