package com.hb.picom.services;

import java.util.List;

public interface Service<T> {
	
	public List<T> getItems();
	
	public T getItem(int id) ;
	
	public void addItem(T item);
	
	public void deleteItem(int id);
	
	public void updateItem(int id, T item) ;

	public void showItems();

}
