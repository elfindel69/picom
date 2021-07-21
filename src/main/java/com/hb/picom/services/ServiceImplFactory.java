package com.hb.picom.services;
import java.sql.Connection;

import com.hb.picom.services.jdbc.*;

public class ServiceImplFactory {
	private static ServiceImplFactory instance;
	private static Connection conn = null;
	
	private ServiceImplFactory() {
		
	}

	private synchronized static ServiceImplFactory createInstance() {
		
		 if(instance == null) {
			 conn = ConnectionService.getConnection();
			 return new ServiceImplFactory();
		 }else {
			 return instance;
		 }
		 
		
		 
		
	}
	
	public static ServiceImplFactory getInstance() {
		if(instance == null) {
			instance = createInstance();
		}
		return instance;
	}

	public AdAreaServiceJDBC getAdAreaServiceImpl() {
		return new AdAreaServiceJDBC(conn);
	}
	
	public AdminServiceJDBC getAdminServiceImpl() {
		return new AdminServiceJDBC(conn);
	}
	
	public AdServiceJDBC getAdServiceImpl() {
		return new AdServiceJDBC(conn);
	}
	
	public AdTimeSlotServiceJDBC getAdTimeSlotServiceImpl() {
		return new AdTimeSlotServiceJDBC(conn);
	}
	
	public AreaServiceJDBC getAreaServiceImpl() {
		return new AreaServiceJDBC(conn);
	}
	
	public BusStopServiceJDBC getBusStopServiceImpl() {
		return new BusStopServiceJDBC(conn);
	}
	
	public CityServiceJDBC getCityServiceImpl() {
		return new CityServiceJDBC(conn);
	}
	
	public ClientServiceJDBC getClientServiceImpl() {
		return new ClientServiceJDBC(conn);
	}
	
	public InvoiceItemServiceJDBC getInvoiceItemServiceImpl() {
		return new InvoiceItemServiceJDBC(conn);
	}
	
	public InvoiceServiceJDBC getInvoiceServiceImpl() {
		return new InvoiceServiceJDBC(conn);
	}
	public TimeSlotServiceJDBC getTimeSlotServiceImpl() {
		return new TimeSlotServiceJDBC(conn);
	}
	
}
