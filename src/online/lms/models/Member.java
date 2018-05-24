package online.lms.models;

public abstract class Member {	
	private String username;	
	private String password;
	private String fullName;
	private String address;
	private String email;
	
	protected Member(String username, String password, String fullName, String address,
			 String email) {
			this.username = username;
			this.password = password;
			this.fullName = fullName;
			this.address = address;
			this.email = email;
	}
	protected Member () { }
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
