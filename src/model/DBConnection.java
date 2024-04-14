package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private Connection connection;
	private final String URLDB = "jdbc:mysql://localhost:3306/users";
	private final String user = "root";
	private final String password = "root";
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URLDB, user, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		try {
			if(this.connection!=null)
				this.connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
