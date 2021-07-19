package com.hb.picom.pojos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Client {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String creditCardNb;
	private String expirationDate;
	private String cVVCode;
	private String companyName;
	private String companySIRET;
	private String companyAddress;
	private City companyCity;
	private LocalDateTime creationDate = LocalDateTime.now();
	private List<Advertisment> ads = new ArrayList<Advertisment>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreditCardNb() {
		return creditCardNb;
	}
	public void setCreditCardNb(String creditCardNb) {
		this.creditCardNb = creditCardNb;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getcVVCode() {
		return cVVCode;
	}
	public void setcVVCode(String cVVCode) {
		this.cVVCode = cVVCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanySIRET() {
		return companySIRET;
	}
	public void setCompanySIRET(String companySIRET) {
		this.companySIRET = companySIRET;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	public City getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(City companyCity) {
		this.companyCity = companyCity;
	}
	
	public List<Advertisment> getAds() {
		return ads;
	}
	
	public void addAdvertisment(Advertisment ad) {
		ads.add(ad);
	}
	
	public Client() {
		
	}
	
	public Client(int id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("first name: "+firstName+",\n");
		sb.append("last name: "+lastName+",\n");
		sb.append("email: "+email+",\n");
		
		if(creditCardNb != null) {
			sb.append("Credit card number: "+creditCardNb+",\n");
		}
		
		if(expirationDate != null) {
			sb.append("expiration date: "+expirationDate+",\n");
		}
		
		if(cVVCode != null) {
			sb.append("CVV code: "+cVVCode+",\n");
		}
		
		if(companyName != null) {
			sb.append("company name: "+companyName+",\n");
		}
		
		if(companySIRET != null) {
			sb.append("company SIRET: "+companySIRET+",\n");
		}
		
		if(companyAddress != null) {
			sb.append("company address: "+companyAddress+",\n");
		}
		
		if(companyCity != null) {
			sb.append("company city:\n"+companyCity+",\n");
		}
		
		sb.append("creation date:\n"+FORMATTER.format(creationDate));
		
		return sb.toString();
		
	}
	public void showAds() {
		int idx = 0;
		for (Advertisment advertisment : ads) {
			System.out.println(idx+":");
			System.out.println(advertisment);
			idx++;
		}
	}
	public void addAd(Advertisment ad) {
		ads.add(ad);
		
	}
	
	public void setAd(int idx, Advertisment ad) {
		ads.set(idx, ad);
	}
	
	public void deleteAd(int idx) {
		ads.remove(idx);
		
	}
}
