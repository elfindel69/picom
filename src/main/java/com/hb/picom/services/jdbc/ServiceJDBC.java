package com.hb.picom.services.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hb.picom.services.Service;

public abstract class ServiceJDBC<T> implements Service<T> {
	protected Connection connection = null;
	protected ResultSet rs = null;
	protected PreparedStatement ps = null;
	protected Statement stmt = null;
	
	protected List<T> items= new ArrayList<T>();
	
	public ServiceJDBC(Connection connection){
		this.connection = connection;
		initList();
	}
	
	
	
	protected abstract void initList();



	public List<T> getItems(){
		return items;
	}
	
	public abstract T getItem(int id) ;
	
	public void addItem(T item) {
		items.add(item);
	}
	
	public abstract void deleteItem(int id) ;
	
	public abstract int updateItem(int id, T item) ;
	
	public void showItems() {
		int idx = 0;
		for (T item : items) {
			System.out.println(idx+":");
			System.out.println(item);
			idx++;
		}
	}
	
	public abstract void showItem(int id) ;
	
	protected void showColumnNames(ResultSetMetaData metaData, int columsNb) {
		for (int i = 1;i<=columsNb;i++) {
			  if(i>1) {
				  System.out.print("|");
				
			  }
			  String column = null;
			try {
				column = metaData.getColumnName(i);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  System.out.print(column);
		 }
		 System.out.println("");
		
	}

}
