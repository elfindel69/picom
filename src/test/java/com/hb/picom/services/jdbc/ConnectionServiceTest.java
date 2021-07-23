package com.hb.picom.services.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class ConnectionServiceTest {

	@Test
	@RepeatedTest(100)
	void testGetConnection() {
		assertNotNull(ConnectionService.getConnection());
		
	}

}
