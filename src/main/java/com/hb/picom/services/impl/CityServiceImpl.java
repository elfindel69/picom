package com.hb.picom.services.impl;

import com.hb.picom.pojos.City;

public class CityServiceImpl extends ServiceImpl<City> {
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
	public void deleteItem(int id) {
		for (City city : items) {
			if(city.getId() == id) {
				items.remove(city);
			}
		}
		
	}
}
