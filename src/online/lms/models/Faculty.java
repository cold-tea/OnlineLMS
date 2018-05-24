package online.lms.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import online.lms.database.DBHandler;

public class Faculty {
	private int id;
	private String name;
	private String system;
	private int length;
	private String description;
	
	public Faculty(int id, String name, String system, int length, String descripiton) {
		this.id = id;
		this.name = name;
		this.system = system;
		this.length = length;
		this.description = descripiton;
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
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Faculty facultyBuilder(int ids, ServletContext context) {
		DBHandler handler = (DBHandler) context.getAttribute("dbHandler");
		ResultSet resultSet = handler.executePreparedQuery("SELECT * FROM faculty_table WHERE "
				+ "id = ?", ids);
		try {
			resultSet.next();
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String system = resultSet.getString("system");
			int length = resultSet.getInt("length");
			String description = resultSet.getString("description");
			Faculty faculty = new Faculty (id, name, system, length, description);
			return faculty;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}
