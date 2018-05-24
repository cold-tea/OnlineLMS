package online.lms.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.User;

public class PostEditUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Admin admin = (Admin) request.getSession().getAttribute("session");
		boolean status = admin.editUser(user);
		if (status) {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=3&user_edited=edited&username="+username);
		} else {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=3&user_edited=unedited&username="+username);
		}
	}

}
