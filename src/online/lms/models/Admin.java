package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import online.lms.database.DBHandler;
import online.lms.validators.BookValidator;
import online.lms.validators.FacultyValidator;
import online.lms.validators.UserValidator;

public class Admin extends Member {
	
	private static ServletContext context;
	
	public  Admin(String username, String password, String fullName, String address,
			 String email) {
		super(username, password, fullName, address, email);
	}
	public Admin() { }
	public static Admin adminBuilder(String username, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		Admin.context = context;
		ResultSet resultSet = handler.executePreparedQuery("SELECT * FROM admin_table WHERE "
				+ "username = ?", username);
		try {
			resultSet.next();
			String user = resultSet.getString("username");
			String password = resultSet.getString("password");
			String fullName = resultSet.getString("fullName");
			String address = resultSet.getString("address");
			String email = resultSet.getString("email");
			Admin admin = new Admin(user, password, fullName, address, email);
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public boolean createUser(User user) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if(!UserValidator.validate(user)) {System.out.println("Problem in validator"); return false; }
		int status = handler.executeAction("INSERT INTO user_table VALUES ('"+user.getUsername()+"','" + user.getPassword() 
		+"','"+user.getFullName()+"','" +user.getAddress()+"','" +user.getEmail()+"'," +user.getFacultyId()+",'"+ user.getGender()
		+"',SYSDATE)");
		if (status > 0) { System.out.println("User created"); return true;}
		else { System.out.println("Not Created !! Problem in query"); return false;}
	}
	
	public boolean approveUser(User user) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if(!UserValidator.validate(user)) {System.out.println("Problem in validator"); return false; }
		int status = handler.executeAction("INSERT INTO user_table VALUES ('"+user.getUsername()+"','" + user.getPassword() 
		+"','"+user.getFullName()+"','" +user.getAddress()+"','" +user.getEmail()+"'," +user.getFacultyId()+",'"+ user.getGender()
		+"',SYSDATE)");
		if (status > 0) {
			System.out.println("User created");
			handler.executeAction("DELETE FROM register_table WHERE username='"+user.getUsername()+"'");
			return true;
		}
		else { System.out.println("Not Approved !! Problem in query"); return false;}
	}
	
	public void removeUser(User user) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("DELETE FROM user_table WHERE username='"+user.getUsername()+"'");
		if (status > 0) System.out.println("User removed");
		else System.out.println("Not Removed !! Problem in query");
	}
	
	public void removeRegistrant(User user) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("DELETE FROM register_table WHERE username='"+user.getUsername()+"'");
		if (status > 0) System.out.println("User removed");
		else System.out.println("Not Removed !! Problem in query");
	}
	
	public List<User> listUsers() {
		List<User> userList = new ArrayList<User>();
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet resultSet = handler.executeQuery("SELECT * FROM user_table");
		try {
			while(resultSet.next()) {
				User user = User.userBuilder(resultSet.getString("username"), context);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public List<User> listRegistrants() {
		List<User> registrantList = new ArrayList<User>();
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet resultSet = handler.executeQuery("SELECT * FROM register_table");
		try {
			while(resultSet.next()) {
				User user = User.registrantBuilder(resultSet.getString("username"), context);
				registrantList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registrantList;
	}
	
	public boolean editUser(User user) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if(!UserValidator.validate(user)) return false;
		int status = handler.executeAction("UPDATE user_table SET password='"+user.getPassword()+"',address='"
				+user.getAddress()+"',email='"+user.getEmail()+"',fullname='"+user.getFullName()+"',faculty_id="
				+user.getFacultyId()+",gender='"+user.getGender()+"' WHERE username='"+user.getUsername()+"'");
		if (status > 0) { System.out.println("User Edited"); return true; }
		else { System.out.println("Problem in query"); return false; }
	}
	
	public void changePassword(Admin admin, String password) {
		String username = admin.getUsername();
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("UPDATE admin_table SET password="+password+" WHERE username="+username);
		if (status > 0) System.out.println("Password Updated");
		else System.out.println("Problem in query");
	}
	
	public boolean createBook(Book book) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if (!BookValidator.validate(book)) return false;
		int status = handler.executeAction("INSERT INTO book_table VALUES("+book.getId()
		+",'"+book.getName()+"','"+book.getAuthor()+"','"+book.getPublisher()+"','"+book.getEdition()
		+"',"+book.getFacultyId()+","+book.getQuantity()+","+book.getPrice()+")" );
		if (status > 0) { System.out.println("Book Created"); return true; }
		else { System.out.println("Problem in query"); return false; }
	}
	
	public List<Book> listBooks() {
		List<Book> bookList = new ArrayList<Book>();
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet resultSet = handler.executeQuery("SELECT * FROM book_table");
		try {
			while(resultSet.next()) {
				Book book = Book.bookBuilder(resultSet.getInt("id"), context);
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public void removeBook(Book book) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("DELETE FROM book_table WHERE id="+book.getId());
		if (status > 0) System.out.println("Book removed");
		else System.out.println("Not Removed !! Problem in query");
	}
	
	public boolean editBook(Book book) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if(!BookValidator.validate(book)) return false;
		int status = handler.executeAction("UPDATE book_table SET name='"+book.getName()+"',author='"
				+book.getAuthor()+"',publisher='"+book.getPublisher()+"',edition='"+book.getEdition()+"',faculty_id="
				+book.getFacultyId()+",quantity="+book.getQuantity()+",price="+book.getPrice()+" WHERE id="+book.getId());
		if (status > 0) { System.out.println("Book Edited"); return true; }
		else { System.out.println("Problem in query"); return false; }
	}
	
	public List<Faculty> listFaculties() {
		List<Faculty> facultyList = new ArrayList<Faculty>();
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet resultSet = handler.executeQuery("SELECT * FROM faculty_table");
		try {
			while(resultSet.next()) {
				Faculty faculty = Faculty.facultyBuilder(resultSet.getInt("id"), context);
				facultyList.add(faculty);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facultyList;
	}
	
	public boolean createFaculty(Faculty faculty) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if(!FacultyValidator.validate(faculty)) {System.out.println("Problem in validator"); return false; }
		int status = handler.executeAction("INSERT INTO faculty_table VALUES ("+faculty.getId()+",'" +faculty.getName()
		+"','"+faculty.getSystem()+"'," +faculty.getLength()+",'" +faculty.getDescription()+"')");
		if (status > 0) { System.out.println("Faculty created"); return true;}
		else { System.out.println("Not Created !! Problem in query"); return false;}
	}
	
	public void removeFaculty(Faculty faculty) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("DELETE FROM faculty_table WHERE id="+faculty.getId());
		if (status > 0) System.out.println("Faculty removed");
		else System.out.println("Not Removed !! Problem in query");
	}
	
	public boolean editFaculty(Faculty faculty) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		if(!FacultyValidator.validate(faculty)) return false;
		int status = handler.executeAction("UPDATE faculty_table SET name='"+faculty.getName()+"',system='"
				+faculty.getSystem()+"',length="+faculty.getLength()+",description='"+faculty.getDescription()
				+"' WHERE id="+faculty.getId());
		if (status > 0) { System.out.println("Faculty Edited"); return true; }
		else { System.out.println("Problem in query"); return false; }
	}
	public List<AvailableSerial> listAvailableSerials() {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		List<AvailableSerial> availableList = new ArrayList<AvailableSerial>();
		ResultSet resultSet = handler.executeQuery("select book_serials.id as serial,book_table.id as id,name,author,edition "
			+"from book_table inner join book_serials "
			+"on book_table.id = book_serials.book_id "
			+"where book_serials.id not in(select book_serial from grant_table)");
		availableList = AvailableSerial.availableSerialBuilder(resultSet, context);

		return availableList;
	}
	
	public List<ExtendedGrantedSerial> listGrantedSerials() {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		List<ExtendedGrantedSerial> grantedList = new ArrayList<ExtendedGrantedSerial>();
		ResultSet resultSet = handler.executeQuery("SELECT * FROM grant_table");
		try {
			while (resultSet.next()) {
				ExtendedGrantedSerial serial = GrantedSerial.grantedSerialBuilder(resultSet.getInt("book_serial"), context);
				grantedList.add(serial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grantedList;
	}
	
	public boolean grantSerial(GrantedSerial serial, String dateString) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		//if(!FacultyValidator.validate(faculty)) {System.out.println("Problem in validator"); return false; }
		int status = handler.executeAction("INSERT INTO grant_table VALUES ("+serial.getBookSerial()+",'" +serial.getUsername()
		+"',SYSDATE,"+"TO_DATE('"+dateString+"', 'YYYY/MM/DD')"+")");
		if (status > 0) { 
			System.out.println("Book Granted"); 
			return true;
		}
		else { System.out.println("Not Granted !! Problem in query"); return false;}
	}
	
	public boolean renewGrantedBooks(int serial, String date) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("UPDATE grant_table SET return_date=TO_DATE('"+date+"', 'YYYY/MM/DD') WHERE book_serial="+serial);
		if (status > 0) { 
			System.out.println("Renewal Successful"); 
			return true;
		}
		else { System.out.println("Renewal Unsuccessful !! Problem in query"); return false;}
	}
	
	public void removeGrantedSerial(int serial) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("DELETE FROM grant_table WHERE book_serial="+serial);
		if (status > 0) System.out.println("Granted book removed");
		else System.out.println("Not Removed !! Problem in query");
	}
	public List<ExtendedRequest> listRequests() {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		List<ExtendedRequest> requestList = new ArrayList<ExtendedRequest>();
		ResultSet resultSet = handler.executeQuery("SELECT * FROM request_table");
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
	
	public void removeRequest(int sn) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("DELETE FROM request_table WHERE sn="+sn);
		if (status > 0) System.out.println("Request removed");
		else System.out.println("Not Removed !! Problem in query");
	}
	
	public GrantedSerial grantRequestHelper(int sn) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet rs = handler.executePreparedQuery("SELECT  * FROM request_table WHERE sn=?", sn);
		try {
			rs.next();
			String username = rs.getString("username");
			int bookId = Integer.valueOf(rs.getInt("book_id"));
			List<AvailableSerial> listAvailables = listAvailableSerials();
			for(AvailableSerial avSerial : listAvailables) {
				if (avSerial.equals(bookId)) {
					int serial = avSerial.getBookSerial();
					GrantedSerial serialGranted = new GrantedSerial(serial, username, null, null);
					return serialGranted;
				} else {
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ExtendedFine> listFines() {
		List<ExtendedFine> fineList = new ArrayList<ExtendedFine>();
		List<ExtendedGrantedSerial> extSerials= listGrantedSerials();
		for(ExtendedGrantedSerial extSerial : extSerials) {
			GrantedSerial grantSerial = (GrantedSerial) extSerial;
			int serial = grantSerial.getBookSerial();
			Date date1 = new Date();
			Date date2 = grantSerial.getReturnDate();
			long dateDiff = (date1.getTime() - date2.getTime())/(1000 * 60 * 60 * 24);
			if (dateDiff > 0) {
				System.out.println("I am executed for serial : " + serial);
				ExtendedFine extFine = Fine.fineBuilder(serial, context);
				fineList.add(extFine);			
			}
		}
		return fineList;
	}
	
	public boolean updateSocialSites(String facebook, String twitter, String linkedIn, int fine) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int num = handler.executeAction("update social_table set facebook='"+facebook+"',twitter='"+twitter+"',linkedIn='"+
				linkedIn+"',fine_per_day="+fine);
		if (num > 0 ) return true;
		else return false;
	}
}

