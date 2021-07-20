package com.hb.picom.services.impl;

import java.time.Period;

import com.hb.picom.pojos.Advertisment;
import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.TimeSlot;
import com.hb.picom.services.CalcPrice;

public class CalcPriceImpl implements CalcPrice {

	@Override
	public double calcPrice(Advertisment item) {
		double price = 0.0;
		Period period = Period.between(item.getStartDate().toLocalDate(), item.getEndDate().toLocalDate());
		for(int i=0;i <= period.getDays();i++) {
			for(Area area : item.getAreas()) {
				for(TimeSlot timeSlot : item.getTimeSlot()) {
					price += (area.getBasePrice()*timeSlot.getPrice());
				}
			}
		}
		
		return price;
	}

}
