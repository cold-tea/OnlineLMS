package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import online.lms.database.DBHandler;

public class Login {
	private String username;
	private String password;
	private final String ADMIN_QUERY = "SELECT * FROM admin_table";
	private final String USER_QUERY = "SELECT * FROM user_table";
	
	public Login(String username, String password) {
		this.username = username;
		this.password = password;	
		
	}
	
	public boolean nullValidator() {
		if (username.isEmpty()) return false;
		if (password.isEmpty()) return false;
		return true;
	}

	public String requestLogin(ServletContext context) {
		if (!nullValidator()) {
			return "field_nulls";
		}			
		else {
			if (username.equals("admin")) {
				if(validator(context,ADMIN_QUERY).equals("credentials_valid")) {
					return "admin_successful";
				} else {
					return "admin_unsuccessful";
				}
			} else {
				if(validator(context,USER_QUERY).equals("credentials_valid")) {
					return "user_successful";
				} else {
					return "user_unsuccessful";
				}
			}
		}
	}

	
	private String validator(ServletContext context, String query) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet resultSet = handler.executeQuery(query);
		Boolean status = false;
		try {
			while(resultSet.next()) {
				if(resultSet.getString("username").equals(username) 
						&& resultSet.getString("password").equals(password)) {
					status = true;
					break;
				}
			}
			if(status){
				return "credentials_valid";
			} else {
				return "credentials_invalid";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
