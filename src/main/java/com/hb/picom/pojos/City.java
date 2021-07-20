package com.hb.picom.pojos;

public class City {
	private String name;
	private String zipCode;
	private Country country;
	private int id;
	
	public int getId() {
		return this.id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public City() {
		
	}
	
	public City(String name, String zipCode, Country country) {
		this.name = name;
		this.zipCode = zipCode;
		this.country = country;
		
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("city name: "+name+",\n" );
		sb.append("city zip code: "+zipCode+",\n" );
		sb.append("city country: "+country );
		return sb.toString();
	}
	public void setCountry(String name) {
		this.country = Country.valueOf(name);
		
	}
	
	

}
