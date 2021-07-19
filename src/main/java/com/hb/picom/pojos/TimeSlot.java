package com.hb.picom.pojos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeSlot {
	private int id;
	private LocalTime startTime;
	private LocalTime endTime;
	private double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public TimeSlot() {
		
	}
	
	public TimeSlot(int id, LocalTime startTime, LocalTime endTime, double price) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
	}
	
	@Override
	public String toString() {
		final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("start time: "+FORMATTER.format(startTime)+",\n");
		sb.append("end time: "+FORMATTER.format(endTime)+",\n");
		sb.append("price: "+price);
		return sb.toString();
	}
	
	
}
