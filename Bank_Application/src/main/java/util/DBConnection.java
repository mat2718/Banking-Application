package util;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {
	
	private static final Logger logger = LogManager.getLogger(DBConnection.class);
	
	private static DBConnection instance;
	private Connection conn = null;
	
	private DBConnection() throws Exception {
			try {
				String url = ConfigReader.getInstance().getProperty("DB_URL");
				String user = ConfigReader.getInstance().getProperty("DB_USER");
				String password = ConfigReader.getInstance().getProperty("DB_PASSWORD");
				this.conn = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static DBConnection getInstance() throws Exception {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}

}
