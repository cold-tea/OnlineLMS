package online.lms.config;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.database.DBHandler;
import online.lms.helper.ForgetHelper;

public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user");
		try {
		boolean status = new ForgetHelper().getInfo(username, getServletContext());
		if ( status == true )
			response.sendRedirect("/Online_LMS/LoginForm?info=sent");
		else
			response.sendRedirect("/Online_LMS/LoginForm?info=nosent");
		} catch (Exception e) {
			
		} finally {
			
		}

	}
	
	

}
