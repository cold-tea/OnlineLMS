package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.ExtendedRequest;
import online.lms.models.Request;
import online.lms.models.User;

public class RemoveRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sn = Integer.valueOf(request.getParameter("id"));
		Admin admin = null;
		if (request.getSession().getAttribute("session") instanceof Admin)
			admin = (Admin) request.getSession().getAttribute("session");
		else if (request.getSession().getAttribute("session") instanceof User)
			admin = new Admin();
		ExtendedRequest extRequest = Request.requestBuilder(sn, getServletContext());
		admin.removeRequest(sn);		
		response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=11&removed_username="+extRequest.getUserName()+"&removed_book="+extRequest.getBookName());
	}

}
