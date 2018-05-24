package online.lms.models;

public abstract class ExtendedRequest {
	private String userName;
	private String bookName;
	public ExtendedRequest(String userName, String bookName) {
		super();
		this.userName = userName;
		this.bookName = bookName;
	}
	public ExtendedRequest() {}
	
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
