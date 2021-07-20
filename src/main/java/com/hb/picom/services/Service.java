package com.hb.picom.services;

import java.util.List;

public interface Service<T> {
	
	public int createItem(T item);
	
	public List<T> getItems();
	
	public T getItem(int id) ;
	
	public void addItem(T item);
	
	public void deleteItem(int id);
	
	public int updateItem(int id, T item) ;

	public void showItems();
	
	public void showItem(int id);

}
