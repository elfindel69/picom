package com.hb.picom.services.impl;

import com.hb.picom.pojos.BusStop;

public class BusStopServiceImpl extends ServiceImpl<BusStop> {
	@Override
	public int createItem(BusStop item) {
		items.add(item);
		return items.indexOf(item);
	}
	
	@Override
	public BusStop getItem(int id) {
		for (BusStop busStop : items) {
			if(busStop.getId() == id) {
				return busStop;
			}
		}
		return null;
	}

	@Override
	public boolean deleteItem(int id) {
		for (BusStop busStop : items) {
			if(busStop.getId() == id) {
				items.remove(busStop);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void showItem(int id) {
		for (BusStop advertisment : items) {
			if(advertisment.getId() == id) {
				System.out.println(advertisment);
			}
		}
		
	}
}
