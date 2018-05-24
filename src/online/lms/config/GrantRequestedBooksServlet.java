package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.GrantedSerial;

public class GrantRequestedBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sn = Integer.valueOf(request.getParameter("sn"));
		String returnDate = request.getParameter("return_date");
		Admin admin = (Admin) request.getSession().getAttribute("session");
		GrantedSerial serialGranted = admin.grantRequestHelper(sn);
		if(serialGranted != null) {
			boolean status = admin.grantSerial(serialGranted, returnDate);
			if (status) {
				admin.removeRequest(sn);
				response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=11&granted_username="+serialGranted.getUserName()+"&granted_book="+serialGranted.getBookName());
			} else {
				
			}
		} else {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=11&sn_unproceed="+sn);
		}
	}
}
