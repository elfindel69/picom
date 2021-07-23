package com.hb.picom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PiComContextTest {
	
	private PiComContext instance1;
	private PiComContext instance2;
	@BeforeEach
	void setUp() {
		Thread th1 = new Thread(() -> instance1 = PiComContext.getInstance());
        th1.start();
        try {
			th1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void testSingletonUnique() {
			Thread th2 = new Thread(() -> instance2 = PiComContext.getInstance());
			th2.start();
			
			try {
				th2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertSame(instance2, instance1);
	}
	
	@Test
	void testGetDatabaseUrl() {
		String dbUrl = instance1.getDatbaseUrl();
		
		assertEquals("jdbc:mysql://localhost",dbUrl);
	}
	
	@Test
	void testGetDatabaseName() {
		String dbName = instance1.getDatbaseName();
		
		assertEquals("picom",dbName);
	}
	
	@Test
	void testGetDatabaseUser() {
		String dbUser = instance1.getDatbaseUser();
		
		assertEquals("root",dbUser);
	}
	
	@Test
	void testGetDatabasePassword() {
		String dbPasswd = instance1.getDatbasePassword();
		
		assertEquals("alpine",dbPasswd);
	}
	
}
