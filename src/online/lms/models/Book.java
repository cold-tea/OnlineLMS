package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import online.lms.database.DBHandler;

public class Book {
	private int id;
	private String name;
	private String author;
	private String publisher;
	private String edition;
	private int facultyId;
	private int quantity;
	private double price;
	private static ServletContext context;
	public Book(int id, String name, String author, String publisher, String edition,
			int facultyId, int quantity, double price) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.facultyId = facultyId;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public static Book bookBuilder(int ids, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		Book.context = context;
		ResultSet resultSet = handler.executePreparedQuery("SELECT * FROM book_table WHERE "
				+ "id = ?", ids);
		try {
			resultSet.next();
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String author = resultSet.getString("author");
			String publisher = resultSet.getString("publisher");
			String edition = resultSet.getString("edition");
			int faculty = resultSet.getInt("faculty_Id");
			int quantity = resultSet.getInt("quantity");
			double price = resultSet.getDouble("price");
			Book book = new Book(id, name, author, publisher, edition, faculty, quantity, price);
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	public static boolean addSerial(Serial serial) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		int status = handler.executeAction("INSERT INTO book_serials VALUES("+serial.getId()
		+","+serial.getBookId()+")");
		if (status > 0) { 
			System.out.println("Book Created");
			ResultSet rs = handler.executePreparedQuery("SELECT * FROM book_table WHERE id=?", serial.getBookId());
			int quantity = 0;
			try {
				rs.next();
				quantity = rs.getInt("quantity");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			handler.executeAction("UPDATE book_table SET quantity="+ ++quantity +" WHERE id="+serial.getBookId());
			return true; 
		}
		else { System.out.println("Problem in query"); return false; }
	}
}
