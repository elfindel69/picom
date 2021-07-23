package com.hb.picom.services.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hb.picom.pojos.AdArea;

class AdAreaServiceJDBCTest {
	
	private Connection conn ;
	private AdArea item1;
	private AdArea item2;
	private AdAreaServiceJDBC areas;
	private int testId;
	
	@BeforeEach
	void setUp() throws Exception {
		 testId = 66;
		conn = ConnectionService.getConnection();
		item1 = new AdArea(testId,2,3); 
		
		item2 = new AdArea(testId,4,5); 
		areas = new AdAreaServiceJDBC(conn);
	}

	@Test
	void testCreateItem() {
		int id = areas.createItem(item1);
		
		assertEquals(testId,id);
	}
	
	@Test
	void testGetItem() {
		AdArea testItem = areas.getItem(testId);
		boolean test= item1.equals(testItem);
		assertEquals(true,test);
	}
	
	@Test
	void testUpdateItem() {
		int id = areas.updateItem(testId,item2);
		
		AdArea testItem = areas.getItem(testId);
		assertEquals(testId,id);
		boolean test= item2.equals(testItem);
		assertEquals(true,test);
	}
	
	@Test
	void testDeleteItem() {
		boolean deleted = areas.deleteItem(testId);
		
		AdArea testItem = areas.getItem(testId);
		assertTrue(deleted);
		assertNull(testItem);
	}
	

}
