package com.hb.picom.pojos;

import java.time.LocalDateTime;

public class Invoice {
	private int id;
	private LocalDateTime invoiceDate =LocalDateTime.now();
	private String companyName;
	private String companySIRET;
	private String companyAddress;
	private double grossPrice;
	private double vatPrice;
	private double netPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
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
	public double getGrossPrice() {
		return grossPrice;
	}
	public void setGrossPrice(double grossPrice) {
		this.grossPrice = grossPrice;
	}
	public double getVATPrice() {
		return vatPrice;
	}
	public void setVATPrice(double vatPrice) {
		this.vatPrice = vatPrice;
	}
	public double getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}
	
	public Invoice() {
		
	}
	
	public Invoice(int id, LocalDateTime invoiceDate, String companyString, String companySIRET, String companyAddress,
			double grossPrice, double vatPrice, double netPrice) {
		this.id = id;
		this.invoiceDate = invoiceDate;
		this.companyName = companyString;
		this.companySIRET = companySIRET;
		this.companyAddress = companyAddress;
		this.grossPrice = grossPrice;
		this.vatPrice = vatPrice;
		this.netPrice = netPrice;
	}
	
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceDate=" + invoiceDate + ", companyString=" + companyName
				+ ", companySIRET=" + companySIRET + ", companyAddress=" + companyAddress + ", grossPrice=" + grossPrice
				+ ", vATPrice=" + vatPrice + ", netPrice=" + netPrice + "]";
	}
	
	
	
	
	
	
}
