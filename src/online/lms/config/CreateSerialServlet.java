package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Book;
import online.lms.models.Serial;

public class CreateSerialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		int bookId = Integer.valueOf(request.getParameter("book_id"));
		Serial serial = new Serial(id, bookId);
		boolean status = Book.addSerial(serial);
		if (status) {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=6&serial_added="+id+"&book_serialed="+bookId);
		} else {
			
		}
	}

}
