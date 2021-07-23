package com.hb.picom.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalcNetPriceImplTest {

	@Test
	void testCalcNetPrice() {
		double netPrice = new CalcNetPriceImpl().calcNetPrice(100, 0.2);
		
		assertEquals(120,netPrice);
	}

}
