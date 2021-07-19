package com.hb.picom.services;

import java.util.ArrayList;
import java.util.List;

import com.hb.picom.pojos.BusStop;

public class BusStopService implements Service<BusStop> {
	private List<BusStop> stops = new ArrayList<BusStop>();

	public List<BusStop> getItems() {
		// TODO Auto-generated method stub
		return stops;
	}

	public BusStop getItem(int id) {
		// TODO Auto-generated method stub
		return stops.get(id);
	}

	public void addItem(BusStop item) {
		stops.add(item);
		
	}

	public void deleteItem(int id) {
		stops.remove(id);
		
	}

	public void updateItem(int id, BusStop item) {
		stops.set(id, item);
		
	}

	public void showItems() {
		for (BusStop timeSlot : stops) {
			System.out.println(timeSlot);
		}
		
	}
}
