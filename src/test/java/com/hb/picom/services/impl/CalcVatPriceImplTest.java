package com.hb.picom.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalcVatPriceImplTest {

	@Test
	void testCalcNetPrice() {
		double vatPrice = new CalcVatPriceImpl().calcVatPrice(100, 0.2);
		
		assertEquals(20,vatPrice);
	}

}
