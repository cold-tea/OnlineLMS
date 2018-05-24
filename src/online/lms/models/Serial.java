package online.lms.models;

public class Serial {
	
	private int id;
	private int bookId;
	
	public Serial(int id, int bookId) {
		this.id = id;
		this.bookId = bookId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
}
