package online.lms.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.AvailableSerial;
import online.lms.models.User;

public class ListAvailableBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Config/list_available.jsp");
		List<AvailableSerial> availableList = new ArrayList<>();
		if (request.getSession().getAttribute("session") instanceof Admin) {
			Admin admin = (Admin) request.getSession().getAttribute("session");
			availableList = admin.listAvailableSerials();
			request.setAttribute("availableList", availableList);
		} else if (request.getSession().getAttribute("session") instanceof User) {
			Admin admin = new Admin();
			availableList = admin.listAvailableSerials();
			request.setAttribute("availableList", availableList);
		}
		dispatcher.include(request, response);
	}
}
