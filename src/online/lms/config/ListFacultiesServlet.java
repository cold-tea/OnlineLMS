package online.lms.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.Faculty;

public class ListFacultiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Config/list_faculties.jsp");
		Admin admin = (Admin) request.getSession().getAttribute("session");
		List<Faculty> facultyList = admin.listFaculties();
		request.setAttribute("facultyList", facultyList);
		dispatcher.include(request, response);
	}


}
