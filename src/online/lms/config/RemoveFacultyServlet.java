package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.Faculty;

public class RemoveFacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Faculty faculty = Faculty.facultyBuilder(id, getServletContext());
		Admin admin = (Admin) request.getSession().getAttribute("session");
		admin.removeFaculty(faculty);
		response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=8&faculty="+faculty.getName());
	}

}
