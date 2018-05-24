package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.Faculty;

public class PostEditFacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String system = request.getParameter("system");
		int length = Integer.valueOf(request.getParameter("length"));
		String description = request.getParameter("description");
		Faculty faculty = new Faculty(id, name, system, length, description);
		Admin admin = (Admin) request.getSession().getAttribute("session");
		boolean status = admin.editFaculty(faculty);
		if (status) {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=8&faculty_edited=edited&id="+id);
		} else {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=8&faculty_edited=unedited&id="+id);
		}
	}

}
