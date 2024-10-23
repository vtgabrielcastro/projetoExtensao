package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
	 private static final String URL = "jdbc:mysql://localhost:3306/sistema_fiado";
	    private static final String USER = "root";
	    private static final String PASSWORD = "1234567";

	    public static Connection getConnection() {
	        try {
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
