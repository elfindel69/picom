package com.hb.picom.services;

import java.util.ArrayList;
import java.util.List;

public abstract class Service<T> {
	protected List<T> items= new ArrayList<T>();
	
	
	public List<T> getItems(){
		return items;
	}
	
	public T getItem(int id) {
		return items.get(id);
	}
	
	public void addItem(T item) {
		items.add(item);
	}
	
	public void deleteItem(int id) {
		items.remove(id);
	}
	
	public void updateItem(int id, T item) {
		items.set(0, item);
	}
	
	public void showItems() {
		for (T item : items) {
			System.out.println(item);
		}
	}
}
