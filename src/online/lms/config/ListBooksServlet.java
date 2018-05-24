package online.lms.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.models.Admin;
import online.lms.models.Book;
import online.lms.models.User;

public class ListBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Config/list_books.jsp");
		List<Book> bookList = new ArrayList<Book>();
		if (request.getSession().getAttribute("session") instanceof Admin) {
			Admin admin = (Admin) request.getSession().getAttribute("session");
			bookList = admin.listBooks();
			request.setAttribute("bookList", bookList);
		} else if (request.getSession().getAttribute("session") instanceof User) {
			Admin admin = new Admin();
			bookList = admin.listBooks();
			request.setAttribute("bookList", bookList);			
		}
		dispatcher.include(request, response);
	}
}
