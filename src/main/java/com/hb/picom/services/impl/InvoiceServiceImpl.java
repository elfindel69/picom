package com.hb.picom.services.impl;

import com.hb.picom.pojos.Invoice;

public class InvoiceServiceImpl extends ServiceImpl<Invoice> {
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
}
