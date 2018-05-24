package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.User;

public class RemoveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("id");
		User user = User.userBuilder(username, getServletContext());
		Admin admin = (Admin) request.getSession().getAttribute("session");
		admin.removeUser(user);
		response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=3&user="+username);
		
	}
}
