package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.User;

public class RequestBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.valueOf(request.getParameter("id"));
		User user = (User) request.getSession().getAttribute("session");
		String username = user.getUsername();
		boolean status = user.requestBook(bookId,  username);
		if (status) {
			 response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=9&requester_username="+
					 username+"&requester_book="+bookId);
		} else {
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=9&requester_failed=failed");
		}
	}

}
