package online.lms.models;

public abstract class ExtendedFine {
	private String userName;
	private String bookName;
	
	public ExtendedFine() {
		super();
	}
	public ExtendedFine(String userName, String bookName) {
		super();
		this.userName = userName;
		this.bookName = bookName;
	}
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
