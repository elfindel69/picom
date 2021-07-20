package com.hb.picom.services.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.hb.picom.PiComMain;

public class ConnectionService {

	private static Connection connection = null;
	
	private ConnectionService() {
		
		
	}
	
	public final static Connection getConnection() {
		if(connection == null) {
			//check thread safe
			synchronized (ConnectionService.class) {
				if (connection == null) {
					createConnection();
				}
			}
		}
		
		return connection;
	}

	private static Connection createConnection() {
		InputStream aInputStream = PiComMain.class.getResourceAsStream("/env/app.env");
		Properties env = new Properties();
		
		try {
			env.load(aInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		 String jdbcUrl = env.getProperty("jdbc.url");
		 String jdbcDB = env.getProperty("jdbc.db");
		 String jdbcUser = env.getProperty("jdbc.user");
		 String jdbcPassword = env.getProperty("jdbc.password");
		 
		 try {
			connection = DriverManager.getConnection(jdbcUrl+"/"+jdbcDB,jdbcUser,jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		 
		 return connection;
		
	}
	
}
