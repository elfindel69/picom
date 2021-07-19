package com.hb.picom.services;

import java.util.ArrayList;
import java.util.List;

import com.hb.picom.pojos.TimeSlot;

public class TimeSlotService implements Service<TimeSlot> {
	private List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

	public List<TimeSlot> getItems() {
		// TODO Auto-generated method stub
		return timeSlots;
	}

	public TimeSlot getItem(int id) {
		// TODO Auto-generated method stub
		return timeSlots.get(id);
	}

	public void addItem(TimeSlot item) {
		timeSlots.add(item);
		
	}

	public void deleteItem(int id) {
		timeSlots.remove(id);
		
	}

	public void updateItem(int id, TimeSlot item) {
		timeSlots.set(id, item);
		
	}

	public void showItems() {
		for (TimeSlot timeSlot : timeSlots) {
			System.out.println(timeSlot);
		}
		
	}

}
