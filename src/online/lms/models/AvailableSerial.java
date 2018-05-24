package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class AvailableSerial {
	
	private int bookSerial;
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private String bookEdition;
	
	public AvailableSerial (int serial, int id, String name, String author, String edition) {
		this.bookSerial = serial;
		this.bookId = id;
		this.bookName = name;
		this.bookAuthor = author;
		this.bookEdition = edition;
	}
	
	public static List<AvailableSerial> availableSerialBuilder(ResultSet resultSet, ServletContext context) {
		List<AvailableSerial> availableList = new ArrayList<AvailableSerial>();
		try {
			while (resultSet.next()) {
			int serial = resultSet.getInt("serial");
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String author = resultSet.getString("author");
			String edition = resultSet.getString("edition");
			AvailableSerial availableSerial = new AvailableSerial(serial, id, name, author, edition);
			availableList.add(availableSerial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return availableList;
	}

	public int getBookSerial() {
		return bookSerial;
	}

	public void setBookSerial(int bookSerial) {
		this.bookSerial = bookSerial;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookEdition() {
		return bookEdition;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}
	
	public boolean equals(int bookId) {
		//super.equals(obj);
		if (bookId == getBookId()) {
			return true;
		}
		return false;
	}
}
