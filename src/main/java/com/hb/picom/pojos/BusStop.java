package com.hb.picom.pojos;

public class BusStop {
	private int id;
	private String name;
	private String iPAddress;
	private String gps;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIPAdress() {
		return iPAddress;
	}
	public void setIPAdress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	public String getGps() {
		return this.gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	
	public BusStop() {
		
	}
	
	public BusStop(int id, String name, String iPAddress, String gps ){
		this.id = id;
		this.name = name;
		this.iPAddress = iPAddress;
		this.gps = gps;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id: "+id+",\n");
		sb.append("name: "+name+",\n");
		sb.append("IP address:\n"+iPAddress+",\n");
		sb.append("GPS: "+gps);
		
		return sb.toString();
	}

}
