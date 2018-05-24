package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;

import online.lms.database.DBHandler;

public class Fine extends ExtendedFine{
	private int bookSerial;
	private String username;
	private Date returnDate;
	private Date todayDate;
	private int daysExceeded;
	private double fine;
	private static ServletContext context;
	
	public Fine(int bookSerial, String username, Date returnDate, Date todayDate, int daysExceeded, double fine) {
		super();
		this.bookSerial = bookSerial;
		this.username = username;
		this.returnDate = returnDate;
		this.todayDate = todayDate;
		this.daysExceeded = daysExceeded;
		this.fine = fine;
	}
	public Fine(int bookSerial, String username, Date returnDate, Date todayDate, int daysExceeded, double fine, String userName, String bookName) {
		super(userName, bookName);
		this.bookSerial = bookSerial;
		this.username = username;
		this.returnDate = returnDate;
		this.todayDate = todayDate;
		this.daysExceeded = daysExceeded;
		this.fine = fine;
	}
	public int getBookSerial() {
		return bookSerial;
	}
	public void setBookSerial(int bookSerial) {
		this.bookSerial = bookSerial;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getTodayDate() {
		return todayDate;
	}
	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}
	public int getDaysExceeded() {
		return daysExceeded;
	}
	public void setDaysExceeded(int daysExceeded) {
		this.daysExceeded = daysExceeded;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	
	private static int getFineAmount(ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet rs = handler.executeQuery("select fine_per_day from social_table");
		int fine = 0;
		try {
			rs.next();
			fine = rs.getInt("fine_per_day");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fine;
	}
	
	public static ExtendedFine fineBuilder(int serial, ServletContext context) {
		Fine.context = context;
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int finePerDay = getFineAmount(context);
		ResultSet rs= handler.executePreparedQuery("select book_serial, username, return_date, sysdate, trunc(sysdate)-trunc(return_date) as dates, (trunc(sysdate)-trunc(return_date)) * "+ finePerDay + " as fines " +
				"from grant_table where book_serial=?",serial);
		try {
			rs.next();
			
			Fine fine = new Fine(rs.getInt("book_serial"), rs.getString("username"), 
					rs.getDate("return_date"), rs.getDate("sysdate"), 
					rs.getInt("dates"), rs.getDouble("fines"));
			ExtendedFine extFine = fineToExtended(fine);
			return extFine;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private static ExtendedFine fineToExtended(Fine fine) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int bookSerial = fine.getBookSerial();
		String username = fine.getUsername();
		ResultSet rs = handler.executePreparedQuery("SELECT book_id FROM book_serials WHERE id=?", bookSerial);
		try {
			rs.next();
			int bookId = Integer.valueOf(rs.getInt("book_id"));
			rs = handler.executePreparedQuery("SELECT name FROM book_table WHERE id=?", bookId);
			rs.next();
			String bookName = rs.getString("name");
			rs = handler.executePreparedQuery("SELECT fullname FROM user_table WHERE username=?", username);
			rs.next();
			String userName = rs.getString("fullname");
			ExtendedFine extFine = new Fine(bookSerial, username, 
					fine.getReturnDate(), fine.getTodayDate(), fine.getDaysExceeded(),
					fine.getFine(), userName, bookName);
			return extFine;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
