package com.hb.picom.services;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.hb.picom.pojos.Advertisment;
import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.TimeSlot;

public class AdService implements Service<Advertisment> {
	private List<Advertisment> ads = new ArrayList<Advertisment>();

	public List<Advertisment> getItems() {
		// TODO Auto-generated method stub
		return ads;
	}

	public Advertisment getItem(int id) {
		// TODO Auto-generated method stub
		return ads.get(id);
	}

	public void addItem(Advertisment item) {
		ads.add(item);
		
	}

	public void deleteItem(int id) {
		ads.remove(id);
		
	}

	public void updateItem(int id, Advertisment item) {
		ads.set(id, item);
		
	}

	public void showItems() {
		for (Advertisment timeSlot : ads) {
			System.out.println(timeSlot);
		}
		
	}
	
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
