package online.lms.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.User;

public class ListRegistrantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Config/list_registrants.jsp");
		Admin admin = (Admin) request.getSession().getAttribute("session");
		List<User> registrantList = admin.listRegistrants();
		request.setAttribute("registrantList", registrantList);
		dispatcher.include(request, response);
	}
}
