package com.hb.picom.services.impl;

import com.hb.picom.pojos.TimeSlot;

public class TimeSlotServiceImpl extends ServiceImpl<TimeSlot> {
	@Override
	public TimeSlot getItem(int id) {
		for (TimeSlot timeSlot : items) {
			if(timeSlot.getId() == id) {
				return timeSlot;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		for (TimeSlot timeSlot: items) {
			if(timeSlot.getId() == id) {
				items.remove(timeSlot);
			}
		}
		
	}
}
