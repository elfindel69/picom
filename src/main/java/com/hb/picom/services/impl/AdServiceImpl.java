package com.hb.picom.services.impl;


import com.hb.picom.pojos.Advertisment;

public class AdServiceImpl extends ServiceImpl<Advertisment> {
	
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
	
	
}
