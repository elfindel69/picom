package com.hb.picom.services.impl;

import com.hb.picom.pojos.Area;

public class AreaServiceImpl extends ServiceImpl<Area> {
	@Override
	public Area getItem(int id) {
		for (Area area : items) {
			if(area.getId() == id) {
				return area;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		for (Area area : items) {
			if(area.getId() == id) {
				items.remove(area);
			}
		}
		
	}
}
