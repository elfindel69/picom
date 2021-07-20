package com.hb.picom.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.hb.picom.services.Service;

public abstract class ServiceImpl<T> implements Service<T> {
	protected List<T> items= new ArrayList<T>();
	
	
	public List<T> getItems(){
		return items;
	}
	
	public abstract T getItem(int id) ;
	
	public void addItem(T item) {
		items.add(item);
	}
	
	public abstract void deleteItem(int id) ;
	
	public void updateItem(int id, T item) {
		items.set(id, item);
	}
	
	public void showItems() {
		int idx = 0;
		for (T item : items) {
			System.out.println(idx+":");
			System.out.println(item);
			idx++;
		}
	}
}
