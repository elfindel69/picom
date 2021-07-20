package com.hb.picom.services.impl;

import com.hb.picom.pojos.Client;

public class ClientServiceImpl extends ServiceImpl<Client> {
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
}
