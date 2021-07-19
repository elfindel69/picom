package com.hb.picom.pojos;

public class AdTimeSlot {
	private int id;
	private int adId;
	private int timeSlotId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public int getTimeSlotId() {
		return timeSlotId;
	}
	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}
	
	public AdTimeSlot() {
		
	}
	
	public AdTimeSlot(int id, int adId, int timeSlotId) {
		this.id = id;
		this.adId = adId;
		this.timeSlotId = timeSlotId;
	}
	
}
