package com.hb.picom.pojos;

import java.util.ArrayList;
import java.util.List;

public class Area {
	private int id;
	private String name;
	private double basePrice;
	private List<BusStop> busStops = new ArrayList<BusStop>();
	
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
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	public List<BusStop> getBusStop() {
		return busStops;
	}
	
	public void addBusStop(BusStop busStop) {
		busStops.add(busStop);
	}
	
	public Area() {
		
	}
	
	public Area(int id, String name, double basePrice) {
		this.id = id;
		this.name = name;
		this.basePrice = basePrice;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: "+name+",\n");
		sb.append("base price: "+basePrice);
		
		return sb.toString();
	}
	
	public void showBusStops() {
		for (BusStop busStop : busStops) {
			System.out.println(busStop);
		}
	}
	
}