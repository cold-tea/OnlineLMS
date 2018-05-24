package online.lms.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Book;

public class EditBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Serverpages/index.jsp?page=200");
		Book book = Book.bookBuilder(id, getServletContext());
		request.setAttribute("book", book);
		dispatcher.include(request, response);
	}


}
