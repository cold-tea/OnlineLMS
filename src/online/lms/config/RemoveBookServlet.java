package online.lms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.Book;

public class RemoveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Book book = Book.bookBuilder(id, getServletContext());
		Admin admin = (Admin) request.getSession().getAttribute("session");
		admin.removeBook(book);
		response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=6&book="+book.getName());
	}
}