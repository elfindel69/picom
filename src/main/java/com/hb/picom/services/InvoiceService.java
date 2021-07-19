package com.hb.picom.services;

import java.util.ArrayList;
import java.util.List;

import com.hb.picom.pojos.Invoice;

public class InvoiceService implements Service<Invoice> {
	
	private List<Invoice> invoices = new ArrayList<Invoice>();

	public List<Invoice> getItems() {
		// TODO Auto-generated method stub
		return invoices;
	}

	public Invoice getItem(int id) {
		// TODO Auto-generated method stub
		return invoices.get(id);
	}

	public void addItem(Invoice item) {
		invoices.add(item);
		
	}

	public void deleteItem(int id) {
		invoices.remove(id);
		
	}

	public void updateItem(int id, Invoice item) {
		invoices.set(id, item);
		
	}

	public void showItems() {
		for (Invoice timeSlot : invoices) {
			System.out.println(timeSlot);
		}
		
	}
	
	public double calcVatPrice(double grossPrice, double vat) {
		return grossPrice * vat;
	}
	
	public double calcNetPrice(double grossPrice, double vat) {
		double vatPrice = calcVatPrice(grossPrice, vat);
		return grossPrice+vatPrice;
	}
}
