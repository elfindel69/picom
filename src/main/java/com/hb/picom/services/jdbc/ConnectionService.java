package com.hb.picom.services.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hb.picom.PiComContext;

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
		PiComContext context = PiComContext.getInstance();
		 String jdbcUrl = context.getDatbaseUrl();
		 String jdbcDB = context.getDatbaseName();
		 String jdbcUser = context.getDatbaseUser();
		 String jdbcPassword = context.getDatbasePassword();
		 
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
