package online.lms.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.lms.helper.ForgetHelper;
import online.lms.models.Admin;
import online.lms.models.Book;

public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String edition = request.getParameter("edition");
		int faculty = Integer.valueOf(request.getParameter("faculty"));
		int quantity = 0;
		double price = Double.valueOf(request.getParameter("price"));
		Book book = new Book (id, name, author, publisher, edition, faculty, quantity, price);
		Admin admin = (Admin) request.getSession().getAttribute("session");
		boolean status = admin.createBook(book);
		RequestDispatcher dispatcherCreate = request.getRequestDispatcher("/Serverpages/index.jsp?page=5&status=fail");
		if (status) {
			String emailStatus = new ForgetHelper().getInfos(faculty, id, name, author, getServletContext());
			response.sendRedirect("/Online_LMS/Serverpages/index.jsp?page=6&book_added="+name+"&email_status="+emailStatus);			
		} else {
			dispatcherCreate.include(request,response);
		}
	}

}
