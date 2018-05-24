package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;

import online.lms.database.DBHandler;

public class GrantedSerial extends ExtendedGrantedSerial{
	private int bookSerial;
	private String username;
	private Date receivedDate;
	private Date returnDate;
	private static ServletContext context;
	
	public GrantedSerial (int bookSerial, String username, Date joinedDate, Date returnDate) {
		this.bookSerial = bookSerial;
		this.username = username;
		this.receivedDate = joinedDate;
		this.returnDate = returnDate;
	}
	
	public GrantedSerial (int bookSerial, String username, Date joinedDate, Date returnDate, String userName, String bookName) {
		super(userName, bookName);
		this.bookSerial = bookSerial;
		this.username = username;
		this.receivedDate = joinedDate;
		this.returnDate = returnDate;
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

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date joinedDate) {
		this.receivedDate = joinedDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public static ExtendedGrantedSerial grantedSerialBuilder(int serials, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		GrantedSerial.context = context;
		ResultSet resultSet = handler.executePreparedQuery("SELECT * FROM grant_table WHERE "
				+ "book_serial = ?", serials);
		try {
			resultSet.next();
			int serial = Integer.valueOf(resultSet.getInt("book_serial"));
			String username = resultSet.getString("username");
			Date receivedDate = resultSet.getDate("received_date");
			Date returnDate = resultSet.getDate("return_date");
			GrantedSerial grantedSerial = new GrantedSerial(serial, username, receivedDate, returnDate);
			ExtendedGrantedSerial extendedGrantedSerial = grantedToExtended(grantedSerial);
			return extendedGrantedSerial;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private static ExtendedGrantedSerial grantedToExtended(GrantedSerial serial) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int bookSerial = serial.getBookSerial();
		String username = serial.getUsername();
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
			ExtendedGrantedSerial extGrantedSerial = new GrantedSerial(bookSerial, username, 
					serial.getReceivedDate(), serial.getReturnDate(), userName, bookName);
			return extGrantedSerial;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
