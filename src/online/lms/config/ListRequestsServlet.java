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
import online.lms.models.ExtendedRequest;
import online.lms.models.User;

public class ListRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Config/list_requests.jsp");
		List<ExtendedRequest> requestList = new ArrayList<ExtendedRequest>();
		if (request.getSession().getAttribute("session") instanceof Admin) {
			Admin admin = (Admin) request.getSession().getAttribute("session");
			requestList = admin.listRequests();
			request.setAttribute("requestList", requestList);
		} else if (request.getSession().getAttribute("session") instanceof User) {
			User user = (User) request.getSession().getAttribute("session");
			requestList = user.listRequests(user.getUsername());
			request.setAttribute("requestList", requestList);
		}
		dispatcher.include(request, response);
	}

}
