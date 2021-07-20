package com.hb.picom.services.impl;

import com.hb.picom.pojos.Advertisment;

public class AdServiceImpl extends ServiceImpl<Advertisment> {
	@Override
	public int createItem(Advertisment item) {
		items.add(item);
		return items.indexOf(item);
	}
	
	@Override
	public Advertisment getItem(int id) {
		for (Advertisment advertisment : items) {
			if(advertisment.getId() == id) {
				return advertisment;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		for (Advertisment advertisment : items) {
			if(advertisment.getId() == id) {
				items.remove(advertisment);
			}
		}
		
	}

	@Override
	public void showItem(int id) {
		for (Advertisment advertisment : items) {
			if(advertisment.getId() == id) {
				System.out.println(advertisment);
			}
		}
		
	}
	
	
}
