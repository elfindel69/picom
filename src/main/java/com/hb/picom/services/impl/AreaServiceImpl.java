package com.hb.picom.services.impl;

import com.hb.picom.pojos.Area;

public class AreaServiceImpl extends ServiceImpl<Area> {
	@Override
	public int createItem(Area item) {
		items.add(item);
		return items.indexOf(item);
	}
	
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
	public boolean deleteItem(int id) {
		for (Area area : items) {
			if(area.getId() == id) {
				items.remove(area);
				return true;
			}
		}
		return false;
		
	}

	@Override
	public void showItem(int id) {
		for (Area advertisment : items) {
			if(advertisment.getId() == id) {
				System.out.println(advertisment);
			}
		}
		
	}
}
