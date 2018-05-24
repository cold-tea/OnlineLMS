package online.lms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import online.lms.models.Admin;
import online.lms.models.Login;
import online.lms.models.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Login login = new Login(username, password);
		String result = login.requestLogin(getServletContext());
		
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Serverpages/login.jsp");
		RequestDispatcher dispatcher1 = request.getRequestDispatcher("/Serverpages/index.jsp?page=1");
		if(result.equals("field_nulls")) {
			out.write("Please enter Username and Password.");
			dispatcher.include(request, response);
		} else if (result.equals("admin_successful")) {
			HttpSession session = request.getSession();
			Admin admin = Admin.adminBuilder(username, getServletContext());
			session.setAttribute("session", admin);
			dispatcher1.forward(request, response);
		} else if (result.equals("admin_unsuccessful") ||
				result.equals("user_unsuccessful")) {
			response.sendRedirect("/Online_LMS/Serverpages/login.jsp?login=unsuccessful");
			
		} else if (result.equals("user_successful")) {
			HttpSession session = request.getSession();
			User user = User.userBuilder(username, getServletContext());
			session.setAttribute("session", user);
			dispatcher1.forward(request, response);
		}
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/Online_LMS/LoginForm");
	}
	
	

}
