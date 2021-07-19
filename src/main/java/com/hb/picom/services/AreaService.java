package com.hb.picom.services;

import java.util.ArrayList;
import java.util.List;

import com.hb.picom.pojos.Area;

public class AreaService implements Service<Area> {
	private List<Area> areas = new ArrayList<Area>();

	public List<Area> getItems() {
		// TODO Auto-generated method stub
		return areas;
	}

	public Area getItem(int id) {
		// TODO Auto-generated method stub
		return areas.get(id);
	}

	public void addItem(Area item) {
		areas.add(item);
		
	}

	public void deleteItem(int id) {
		areas.remove(id);
		
	}

	public void updateItem(int id, Area item) {
		areas.set(id, item);
		
	}

	public void showItems() {
		for (Area timeSlot : areas) {
			System.out.println(timeSlot);
		}
		
	}
}
