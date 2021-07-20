package com.hb.picom.services.impl;

import com.hb.picom.services.CalcVatPrice;

public class CalcVatPriceImpl implements CalcVatPrice {

	@Override
	public double calcVatPrice(double grossPrice, double vat) {
		return grossPrice * vat;
	}

}
