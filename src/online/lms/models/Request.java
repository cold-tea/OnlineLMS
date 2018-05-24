package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;

import online.lms.database.DBHandler;

public class Request extends ExtendedRequest {
	private int sn;
	private String username;
	private int bookId;
	private Date requestDate;
	private static ServletContext context;
	
	public Request(int sn, String username, int bookId, Date requestDate) {
		this.sn = sn;
		this.username = username;
		this.bookId = bookId;
		this.requestDate = requestDate;
	}
	
	public Request(int sn, String username, int bookId, Date requestDate, String userName, String bookName) {
		super(userName, bookName);
		this.sn = sn;
		this.username = username;
		this.bookId = bookId;
		this.requestDate = requestDate;
	}
	
	

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	public static ExtendedRequest requestBuilder(int sns, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		Request.context = context;
		ResultSet rs = handler.executePreparedQuery("SELECT * FROM request_table WHERE sn=?", sns);
		try {
			rs.next();
			int sn = rs.getInt("sn");
			String username = rs.getString("username");
			int bookId = rs.getInt("book_id");
			Date date = rs.getDate("request_date");
			Request request = new Request(sn, username, bookId, date);
			ExtendedRequest extendedRequest = requestToExtended(request);
			return extendedRequest;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ExtendedRequest requestToExtended(Request request) {
		String username = request.getUsername();
		int bookId = request.getBookId();
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet rs = handler.executePreparedQuery("SELECT name FROM book_table WHERE id=?", bookId);
		try {
			rs.next();
			String bookName = rs.getString("name");
			rs = handler.executePreparedQuery("SELECT fullname FROM user_table WHERE username=?", username);
			rs.next();
			String userName = rs.getString("fullname");
			ExtendedRequest extRequest = new Request (request.getSn(), username, bookId, request.getRequestDate(), 
					userName, bookName);
			return extRequest;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
