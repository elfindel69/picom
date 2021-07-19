package com.hb.picom.services;

import java.util.ArrayList;
import java.util.List;

import com.hb.picom.pojos.Client;

public class ClientService implements Service<Client> {
	private List<Client> clients = new ArrayList<Client>();

	public List<Client> getItems() {
		// TODO Auto-generated method stub
		return clients;
	}

	public Client getItem(int id) {
		// TODO Auto-generated method stub
		return clients.get(id);
	}

	public void addItem(Client item) {
		clients.add(item);
		
	}

	public void deleteItem(int id) {
		clients.remove(id);
		
	}

	public void updateItem(int id, Client item) {
		clients.set(id, item);
		
	}

	public void showItems() {
		for (Client timeSlot : clients) {
			System.out.println(timeSlot);
		}
		
	}
}
