package online.lms.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.User;

public class EditUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("id");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Serverpages/index.jsp?page=100");
		User user = User.userBuilder(username, getServletContext());
		request.setAttribute("user", user);
		dispatcher.include(request, response);
	}
}
