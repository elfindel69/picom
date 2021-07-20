package com.hb.picom.services.impl;

import com.hb.picom.pojos.BusStop;

public class BusStopServiceImpl extends ServiceImpl<BusStop> {
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
	public void deleteItem(int id) {
		for (BusStop busStop : items) {
			if(busStop.getId() == id) {
				items.remove(busStop);
			}
		}
		
	}
}
