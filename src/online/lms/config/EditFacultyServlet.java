package online.lms.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Faculty;

public class EditFacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Serverpages/index.jsp?page=300");
		Faculty faculty = Faculty.facultyBuilder(id, getServletContext());
		request.setAttribute("faculty", faculty);
		dispatcher.include(request, response);
	}

}
