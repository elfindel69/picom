package com.hb.picom.services.impl;

import com.hb.picom.pojos.Client;

public class ClientServiceImpl extends ServiceImpl<Client> {
	
	@Override
	public int createItem(Client item) {
		items.add(item);
		return items.indexOf(item);
	}
	
	@Override
	public Client getItem(int id) {
		for (Client client : items) {
			if(client.getId() == id) {
				return client;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		for (Client client: items) {
			if(client.getId() == id) {
				items.remove(client);
			}
		}
		
	}
	
	@Override
	public void showItem(int id) {
		for (Client advertisment : items) {
			if(advertisment.getId() == id) {
				System.out.println(advertisment);
			}
		}
		
	}
}
