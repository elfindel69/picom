package com.hb.picom.services.impl;

import com.hb.picom.services.CalcNetPrice;
import com.hb.picom.services.CalcVatPrice;

public class CalcNetPriceImpl implements CalcNetPrice {

	@Override
	public double calcNetPrice(double grossPrice, double vat) {
		CalcVatPrice calcVat = new CalcVatPriceImpl();
		double vatPrice = calcVat.calcVatPrice(grossPrice, vat);
		return grossPrice+vatPrice;
	}

}
