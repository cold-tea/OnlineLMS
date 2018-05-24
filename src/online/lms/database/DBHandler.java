package online.lms.database;

import java.sql.*;

public class DBHandler {
	
	private Connection connection = null;
	Statement statement = null;
	PreparedStatement prepStatement = null;
	private static DBHandler handler = null;
	
	private DBHandler() {
		getConnection();
	}
	
	public Connection getHandlerConnection(){
		return connection;
	}

	
	public static DBHandler getInstance() {
		if(handler == null) {
			handler = new DBHandler();
		}
			return handler;
	}
	
	private void getConnection() {
		if(connection == null){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl"
						, "aims", "college");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Connection already established.");
		}
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		try {		
			statement = connection.createStatement();
			rs = statement.executeQuery(query);	
			System.out.println(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {		
		}
		return rs;	
	}
	

	public ResultSet executePreparedQuery(String query, String username) {
		ResultSet rs = null;
		try {		
			prepStatement = connection.prepareStatement(query);
			prepStatement.setString(1, username);
			rs = prepStatement.executeQuery();
			System.out.println(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {		
		}
		return rs;	
	}
	
	public ResultSet executePreparedQuery(String query, int id) {
		ResultSet rs = null;
		try {		
			prepStatement = connection.prepareStatement(query);
			prepStatement.setInt(1, id);
			rs = prepStatement.executeQuery();
			System.out.println(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {		
		}
		return rs;	
	}
	
	public int executeAction(String query) {
		int status = -1; 
		try {
			System.out.println(query);
			statement = connection.createStatement();
			status = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
}

