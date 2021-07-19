package com.hb.picom.services.impl;

import com.hb.picom.pojos.Invoice;

public class InvoiceServiceImpl extends ServiceImpl<Invoice> {
	
	public double calcVatPrice(double grossPrice, double vat) {
		return grossPrice * vat;
	}
	
	public double calcNetPrice(double grossPrice, double vat) {
		double vatPrice = calcVatPrice(grossPrice, vat);
		return grossPrice+vatPrice;
	}
}
