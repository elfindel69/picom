package com.hb.picom.services.impl;

import com.hb.picom.pojos.TimeSlot;

public class TimeSlotServiceImpl extends ServiceImpl<TimeSlot> {
	
	@Override
	public int createItem(TimeSlot item) {
		items.add(item);
		return items.indexOf(item);
	}
	
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
	
	@Override
	public void showItem(int id) {
		for (TimeSlot advertisment : items) {
			if(advertisment.getId() == id) {
				System.out.println(advertisment);
			}
		}
		
	}
}
