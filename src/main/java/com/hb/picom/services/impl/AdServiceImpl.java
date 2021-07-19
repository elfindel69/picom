package com.hb.picom.services.impl;

import java.time.Period;

import com.hb.picom.pojos.Advertisment;
import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.TimeSlot;

public class AdServiceImpl extends ServiceImpl<Advertisment> {
	
	public double calcPrice(Advertisment item) {
		double price = 0.0;
		Period period = Period.between(item.getStartDate(), item.getEndDate());
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
