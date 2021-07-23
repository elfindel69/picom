package com.hb.picom.pojos;

public class AdArea {
	private int id;
	private int areaId;
	private int adId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	
	public AdArea() {
		
	}
	
	public AdArea(int id, int adId, int areaId) {
		this.id = id;
		this.adId = adId;
		this.areaId = areaId;
	}
	
	@Override
	public boolean equals(Object o) {
		AdArea adArea2 = (AdArea)o;
		
		boolean testAreaId = (this.areaId == adArea2.getAreaId());
		boolean testAdId = (this.adId == adArea2.getAdId());
		
		return testAreaId && testAdId;
	}
}
