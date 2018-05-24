package online.lms.models;

public abstract class ExtendedGrantedSerial {
	private String userName;
	private String bookName;
	
	public ExtendedGrantedSerial(String userName, String bookName) {
		this.userName = userName;
		this.bookName = bookName;
	}
	
	public ExtendedGrantedSerial() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	
}
