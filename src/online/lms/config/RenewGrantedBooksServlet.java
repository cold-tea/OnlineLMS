package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;

public class RenewGrantedBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int serial = Integer.valueOf(request.getParameter("serial"));
		String returnDate = request.getParameter("return_date");
		Admin admin = (Admin) request.getSession().getAttribute("session");
		boolean status = admin.renewGrantedBooks(serial, returnDate);
		if (status) {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=10&serial_renewed=renewed&serial="+serial+"&returnDate="+returnDate);
		} else {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=10&serial_renewed=unrenewed&serial="+serial+"&returnDate="+returnDate);
		}
	}

}
