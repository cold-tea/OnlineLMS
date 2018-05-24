package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;

public class RemoveFineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int serial = Integer.valueOf(request.getParameter("id"));
		Admin admin = (Admin) request.getSession().getAttribute("session");
		admin.removeGrantedSerial(serial);
		response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=12&removed_fine="+serial);
	}
}
