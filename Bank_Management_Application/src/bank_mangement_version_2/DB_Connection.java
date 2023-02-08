package bank_mangement_version_2;
/**
 * This class is used to establish a connection with the database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
	// Create a connection object
	private static Connection connection;
	// URL of the database
	private static final String url="jdbc:mysql://localhost:3306/bankapplication";
	// User name to connect to the database
	private static final String user="root";
	// Password to connect to the database
	private static final String password ="sarvagya@12";
	/**
	 * This method is used to establish a connection with the database
	 * @return Connection object
	 */
	public static Connection connectDb() {
		try {
			// check if connection is already established
			if(connection==null) {
				// load the driver
				Class.forName("com.mysql.jdbc.Driver");
				// establish the connection
				connection = DriverManager.getConnection(url, user, password);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}