package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import online.lms.database.DBHandler;
import online.lms.validators.UserValidator;

public class User extends Member {
	private int facultyId;
	private String gender;
	private Date joinedDate;
	private static ServletContext context;
	
	public User(String username, String password, String fullName, String address,
			 String email, int facultyId, String gender, Date joinedDate) {
		super(username, password, fullName, address, email);
		this.facultyId = facultyId;
		this.gender = gender;
		this.joinedDate = joinedDate;
		
	}
	public User() { }

	public int getFacultyId() {
		return facultyId;
	}

	public String getGender() {
		return gender;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}
	
	public static User userBuilder(String username, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		User.context = context;
		ResultSet resultSet = handler.executePreparedQuery("SELECT * FROM user_table WHERE "
				+ "username = ?", username);
		try {
			resultSet.next();
			String usernames = resultSet.getString("username");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("fullName");
			String address = resultSet.getString("address");
			String email = resultSet.getString("email");
			int facultyId = resultSet.getInt("faculty_id");
			String gender = resultSet.getString("gender");
			Date date = resultSet.getDate("joined_date");
			User user = new User(usernames, password, fullName, address, email, facultyId, gender, date);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public static User registrantBuilder(String username, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		User.context = context;
		ResultSet resultSet = handler.executePreparedQuery("SELECT * FROM register_table WHERE "
				+ "username = ?", username);
		try {
			resultSet.next();
			String usernames = resultSet.getString("username");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("fullName");
			String address = resultSet.getString("address");
			String email = resultSet.getString("email");
			int facultyId = resultSet.getInt("faculty_id");
			String gender = resultSet.getString("gender");
			Date date = resultSet.getDate("joined_date");
			User user = new User(usernames, password, fullName, address, email, facultyId, gender, date);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public void registerUser(User user, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if(!UserValidator.validate(user)) return;
		int status = handler.executeAction("INSERT INTO register_table VALUES ('"+user.getUsername()+"','" + user.getPassword() 
		+"','"+user.getFullName()+"','" +user.getAddress()+"','" +user.getEmail()+"'," +user.getFacultyId()+",'"+ user.getGender()
		+"',SYSDATE)");
		if (status > 0) System.out.println("User Waiting for admin's approval.");
		else System.out.println("Not Created !! Problem in query");
	}
	
	public boolean registerUser(ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if(!UserValidator.validate(this)) {System.out.println("Cannot store"); return false;}
		int status = handler.executeAction("INSERT INTO register_table VALUES ('"+getUsername()+"','" + getPassword() 
		+"','"+getFullName()+"','" +getAddress()+"','" +getEmail()+"'," +getFacultyId()+",'"+ getGender()
		+"',SYSDATE)");
		if (status > 0) { System.out.println("User Waiting for admin's approval."); return true; }
		else { System.out.println("Not Created !! Problem in query"); return false; }
	}
	
	public void changePassword(User user, String password) {
		String username = user.getUsername();
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("UPDATE user_table SET password="+password+" WHERE username="+username);
		if (status > 0) System.out.println("Password Updated");
		else System.out.println("Problem in query");
	}
	
	public boolean requestBook(int bookId, String username) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("INSERT INTO request_table (username, book_id, request_date) VALUES ('"+username+"',"+bookId+",sysdate)");
		if (status > 0) {
			System.out.println("Book requested");
			return true;
		} else {
			System.out.println("Book request failed..");
			return false;
		}
	}
	public List<ExtendedRequest> listRequests(String username) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		List<ExtendedRequest> requestList = new ArrayList<ExtendedRequest>();
		ResultSet resultSet = handler.executeQuery("SELECT * FROM request_table WHERE username='"+username+"'");
		try {
			while (resultSet.next()) {
				ExtendedRequest request = Request.requestBuilder(resultSet.getInt("sn"), context);
				requestList.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestList;
	}
}
