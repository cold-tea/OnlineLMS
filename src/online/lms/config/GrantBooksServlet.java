package online.lms.config;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.GrantedSerial;

public class GrantBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookSerial = Integer.valueOf(request.getParameter("serial"));
		String username = request.getParameter("username");
		String dateString = request.getParameter("return_date");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date returnDate = null;
		try {
			returnDate = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GrantedSerial serial = new GrantedSerial(bookSerial, username, null, returnDate);
		Admin admin = (Admin) request.getSession().getAttribute("session");
		boolean status = admin.grantSerial(serial, dateString);
		if (status) {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=9&serial_granted=granted&serial="+bookSerial+"&username="+username);
		} else {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=9&serial_granted=ungranted&serial="+bookSerial+"&username="+username);
		}
	}

}
