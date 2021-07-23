package com.hb.picom.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceImplFactoryTest {
	private ServiceImplFactory factory ;
	@BeforeEach
	void setUp() throws Exception {
		factory = ServiceImplFactory.getInstance();
	}

	@Test
	void testGetAdAreaServiceImpl() {
		assertNotNull(factory.getAdAreaServiceImpl());
	}

	@Test
	void testGetAdminServiceImpl() {
		assertNotNull(factory.getAdminServiceImpl());
	}

	@Test
	void testGetAdServiceImpl() {
		assertNotNull(factory.getAdServiceImpl());
	}

	@Test
	void testGetAdTimeSlotServiceImpl() {
		assertNotNull(factory.getAdTimeSlotServiceImpl());
	}

	@Test
	void testGetAreaServiceImpl() {
		assertNotNull(factory.getAreaServiceImpl());
	}

	@Test
	void testGetBusStopServiceImpl() {
		assertNotNull(factory.getBusStopServiceImpl());
	}

	@Test
	void testGetCityServiceImpl() {
		assertNotNull(factory.getCityServiceImpl());
	}

	@Test
	void ClientServiceImpl() {
		assertNotNull(factory.getCityServiceImpl());
	}

	@Test
	void testGetInvoiceItemServiceImpl() {
		assertNotNull(factory.getInvoiceItemServiceImpl());
	}

	@Test
	void testGetInvoiceServiceImpl() {
		assertNotNull(factory.getInvoiceServiceImpl());
	}

	@Test
	void testGetTimeSlotServiceImpl() {
		assertNotNull(factory.getTimeSlotServiceImpl());
	}

}
