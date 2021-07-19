package com.hb.picom.services;

import java.util.ArrayList;
import java.util.List;

import com.hb.picom.pojos.City;

public class CityService implements Service<City> {
	private List<City> cities = new ArrayList<City>();

	public List<City> getItems() {
		// TODO Auto-generated method stub
		return cities;
	}

	public City getItem(int id) {
		// TODO Auto-generated method stub
		return cities.get(id);
	}

	public void addItem(City item) {
		cities.add(item);
		
	}

	public void deleteItem(int id) {
		cities.remove(id);
		
	}

	public void updateItem(int id, City item) {
		cities.set(id, item);
		
	}

	public void showItems() {
		for (City timeSlot : cities) {
			System.out.println(timeSlot);
		}
		
	}
}
