package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;

public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String facebook = request.getParameter("facebook");
		String twitter = request.getParameter("twitter");
		String linkedIn = request.getParameter("linkedIn");
		int fine = Integer.valueOf(request.getParameter("fine"));
		
		Admin admin = (Admin) request.getSession().getAttribute("session");
		boolean status = admin.updateSocialSites(facebook, twitter, linkedIn, fine);
		if (status)
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=1");
		else
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=1");
	}

}
