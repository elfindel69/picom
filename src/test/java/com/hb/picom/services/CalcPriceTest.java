package com.hb.picom.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hb.picom.pojos.Advertisment;
import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.TimeSlot;
import com.hb.picom.services.impl.CalcPriceImpl;

class CalcPriceTest {
	
	private Advertisment ad;
	public CalcPriceImpl calcPrice;
	
	@BeforeEach
	void setUp() {
		ad = new Advertisment() {
			public LocalDateTime getStartDate() {
				return LocalDateTime.of(2021,07,23,6,0,0);
			}
			public LocalDateTime getEndDate() {
				return LocalDateTime.of(2021,07,25,20,0,0);
			}
		};
		Area area1 = new Area() {
			public double getBasePrice(){
				return 20;
			}
			
		};
		ad.addArea(area1);
		Area area2 = new Area() {
			public double getBasePrice(){
				return 10;
			}
			
		};
		ad.addArea(area2);
		
		TimeSlot ts1 = new TimeSlot() {
			public double getPrice() {
				return 1.0;
			}
		};
		ad.addTimeSlot(ts1);
		TimeSlot ts2 = new TimeSlot() {
			public double getPrice() {
				return 0.5;
			}
		};
		ad.addTimeSlot(ts2);
		
		calcPrice = new CalcPriceImpl();
	}

	@Test
	void testCalcPrice() {
		double resPrice = calcPrice.calcPrice(ad);
		assertEquals(135,resPrice);
	}

}
