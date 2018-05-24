package online.lms.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.ExtendedGrantedSerial;

public class ListGrantedBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Config/list_granted.jsp");
		Admin admin = (Admin) request.getSession().getAttribute("session");
		List<ExtendedGrantedSerial> grantedList = admin.listGrantedSerials();
		request.setAttribute("grantedList", grantedList);
		dispatcher.include(request, response);
	}

}
