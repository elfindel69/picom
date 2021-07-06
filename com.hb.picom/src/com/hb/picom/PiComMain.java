package com.hb.picom;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.hb.picom.pojos.Advertisment;
import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.BusStop;
import com.hb.picom.pojos.City;
import com.hb.picom.pojos.Client;
import com.hb.picom.pojos.Country;
import com.hb.picom.pojos.Invoice;
import com.hb.picom.pojos.TimeSlot;
import com.hb.picom.services.AdService;
import com.hb.picom.services.CityService;
import com.hb.picom.services.ClientService;
import com.hb.picom.services.InvoiceService;

public class PiComMain {

	
	public static void main(String[] args) {
		CityService cities = new CityService();
		ClientService clients = new ClientService();
		AdService ads = new AdService();
		Client client = new Client(1, "Toto", "Delgado", "toto@delgado.fr", "frfrr");
		client.setCompanyAddress("44, av. Paul Krüger");
		City city = new City("Villeurbanne", "69100", Country.France);
		cities.addItem(city);
		client.setCompanyCity(city);
		clients.addItem(client);
		
		clients.showItems();
		
		Advertisment advertisment = new Advertisment(1, "Mangez des Pommes !", "les pommes c'est bon!", "img.jpeg");
		advertisment.setStartDate(LocalDateTime.parse("2021-07-07T06:00:00"));
		advertisment.setEndDate(LocalDateTime.parse("2021-07-15T20:00:00"));
		ads.addItem(advertisment);
		client.addAdvertisment(advertisment);
		client.showAds();
		
		Area area = new Area(1,"Bellecour",20);
		advertisment.addArea(area);
		advertisment.showAreas();
		
		BusStop busStop = new BusStop(1,"Bellecour","10.0.0.4","QR5M+4F Lyon");
		area.addBusStop(busStop);
		area.showBusStops();
		TimeSlot timeSlot = new TimeSlot(1, LocalTime.parse("09:00:00"), LocalTime.parse("10:00:00"), 1.2);
		advertisment.addTimeSlot(timeSlot);
		advertisment.showTimeSlots();
		double price = ads.calcPrice(advertisment);
		
		System.out.println("price: "+price);
		InvoiceService invoiceService = new InvoiceService();
		double vatPrice = invoiceService.calcVatPrice(price, 0.2);
		double netPrice = invoiceService.calcNetPrice(price, 0.2);
		System.out.println("vat price: "+vatPrice);
		System.out.println("net price: "+netPrice);
		Invoice invoice = new Invoice(1, LocalDateTime.now(), "Coca Cola", "115150", "44, av. Paul Krüger, 69100 Villeurbanne", price, vatPrice, netPrice);
		System.out.println(invoice);
	}

}
