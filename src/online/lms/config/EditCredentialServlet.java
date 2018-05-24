package online.lms.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.User;

public class EditCredentialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object object = request.getSession().getAttribute("session");
		if (object instanceof Admin) {
			String username= ((Admin) object).getUsername();
			
		} else if (object instanceof User) {
			String username= ((User) object).getUsername();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Serverpages/index.jsp?page=100");
			User user = User.userBuilder(username, getServletContext());
			request.setAttribute("user", user);
			dispatcher.include(request, response);
		}
	}

}
