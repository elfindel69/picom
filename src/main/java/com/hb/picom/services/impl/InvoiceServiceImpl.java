package com.hb.picom.services.impl;

import com.hb.picom.pojos.Invoice;

public class InvoiceServiceImpl extends ServiceImpl<Invoice> {
	
	@Override
	public int createItem(Invoice item) {
		items.add(item);
		return items.indexOf(item);
	}
	
	@Override
	public Invoice getItem(int id) {
		for (Invoice invoice : items) {
			if(invoice.getId() == id) {
				return invoice;
			}
		}
		return null;
	}

	@Override
	public void deleteItem(int id) {
		for (Invoice invoice: items) {
			if(invoice.getId() == id) {
				items.remove(invoice);
			}
		}
		
	}
	
	@Override
	public void showItem(int id) {
		for (Invoice advertisment : items) {
			if(advertisment.getId() == id) {
				System.out.println(advertisment);
			}
		}
		
	}
}
