package com.hb.picom.services.impl;

import com.hb.picom.pojos.City;

public class CityServiceImpl extends ServiceImpl<City> {
	@Override
	public int createItem(City item) {
		items.add(item);
		return items.indexOf(item);
	}
	
	@Override
	public City getItem(int id) {
		for (City city : items) {
			if(city.getId() == id) {
				return city;
			}
		}
		return null;
	}

	@Override
	public boolean deleteItem(int id) {
		for (City city : items) {
			if(city.getId() == id) {
				items.remove(city);
			}
			return true;
		}
		return false;
	}

	@Override
	public void showItem(int id) {
		for (City advertisment : items) {
			if(advertisment.getId() == id) {
				System.out.println(advertisment);
			}
		}
		
	}
}
