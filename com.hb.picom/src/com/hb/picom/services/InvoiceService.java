package com.hb.picom.services;

import com.hb.picom.pojos.Invoice;

public class InvoiceService extends Service<Invoice> {
	
	public double calcVatPrice(double grossPrice, double vat) {
		return grossPrice * vat;
	}
	
	public double calcNetPrice(double grossPrice, double vat) {
		double vatPrice = calcVatPrice(grossPrice, vat);
		return grossPrice+vatPrice;
	}
}
