package com.hb.picom.services.impl;

import com.hb.picom.pojos.Admin;

public class AdminServiceImpl extends ServiceImpl<Admin> {

	@Override
	public int createItem(Admin item) {
		items.add(item);
		return items.indexOf(item);
	}
	
	@Override
	public Admin getItem(int id) {
		for (Admin advertisment : items) {
			if(advertisment.getId() == id) {
				return advertisment;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		for (Admin advertisment : items) {
			if(advertisment.getId() == id) {
				items.remove(advertisment);
			}
		}
		
	}

	@Override
	public void showItem(int id) {
		for (Admin advertisment : items) {
			if(advertisment.getId() == id) {
				System.out.println(advertisment);
			}
		}
		
	}

}
