package online.lms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.User;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcherLogin = request.getRequestDispatcher("/Serverpages/login.jsp");
		RequestDispatcher dispatcherRegister = request.getRequestDispatcher("/Serverpages/register.jsp");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		int faculty = Integer.valueOf(request.getParameter("faculty"));
		String gender = request.getParameter("gender");
		Date date = new Date();
		User user = new User (username, password, fullName, address, email, faculty
				, gender, date);
		boolean status = user.registerUser(getServletContext());
		if (status) {
			out.write("<div class=\"alert alert-success\" role=\"alert\">Your request is pending..</div>");
			dispatcherLogin.include(request, response);
		} else {
			out.write("<div class=\"alert alert-danger\" role=\"alert\">Provide valid credentials..</div>");
			dispatcherRegister.include(request,response);
		}
	}

}
