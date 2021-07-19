package com.hb.picom.services;

import java.util.ArrayList;
import java.util.List;

import com.hb.picom.pojos.Admin;

public class AdminService implements Service<Admin> {
	private List<Admin> admins = new ArrayList<Admin>();

	public List<Admin> getItems() {
		// TODO Auto-generated method stub
		return admins;
	}

	public Admin getItem(int id) {
		// TODO Auto-generated method stub
		return admins.get(id);
	}

	public void addItem(Admin item) {
		admins.add(item);
		
	}

	public void deleteItem(int id) {
		admins.remove(id);
		
	}

	public void updateItem(int id, Admin item) {
		admins.set(id, item);
		
	}

	public void showItems() {
		for (Admin timeSlot : admins) {
			System.out.println(timeSlot);
		}
		
	}
}
