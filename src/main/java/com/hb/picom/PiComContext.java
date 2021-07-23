package com.hb.picom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PiComContext {
	private static PiComContext instance;
	private static Properties env;
	
	private PiComContext() {
		
	}
	
	public static PiComContext getInstance() {
		if(instance == null) {
			
			synchronized(PiComContext.class) {
				instance = createInstance();
			}
		
		}
		return instance;
	}
	
	private static PiComContext createInstance() {
		if (instance == null ) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputStream aInputStream = PiComMain.class.getResourceAsStream("/env/app.env");
			env = new Properties();
			
			try {
				
				env.load(aInputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return new PiComContext();
		}else {
			return instance;
		}
	}
	public String getDatbaseUrl() {
		return env.getProperty("jdbc.url");
	}
	
	public String getDatbaseName() {
		return env.getProperty("jdbc.db");
	}
	
	public String getDatbaseUser() {
		return env.getProperty("jdbc.user");
	}
	
	public String getDatbasePassword() {
		return env.getProperty("jdbc.password");
	}
	
}
