package com.hb.picom;

import java.sql.Connection;
import java.sql.SQLException;
/*import java.time.LocalDateTime;
import java.time.LocalTime;*/
//import java.util.Scanner;

/*import org.json.*;

import com.hb.picom.pojos.Advertisment;
import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.BusStop;*/
import com.hb.picom.pojos.City;
import com.hb.picom.pojos.Client;
import com.hb.picom.pojos.Country;
import com.hb.picom.services.Service;
import com.hb.picom.services.jdbc.ClientServiceJDBC;
import com.hb.picom.services.jdbc.ConnectionService;

public class PiComMain {
	
	public static void main(String[] args){
		
		
	/*	String basePath = env.getProperty("basePath");
		System.out.println("basePath="+basePath);
		String serverUrl = env.getProperty("server.url");
		System.out.println("server url="+serverUrl);
		String appName = env.getProperty("app.name");
		System.out.println("app Name="+appName);
		String appDesc= env.getProperty("app.desc");
		System.out.println("app Description="+appDesc);
		String appVersion= env.getProperty("app.version");
		System.out.println("app Version="+appVersion);*/
		
		 try {
	            // The newInstance() call is a work around for some
	            // broken Java implementations

	            Class.forName("com.mysql.cj.jdbc.Driver'");
	        } catch (Exception ex) {
	            // handle the error
	        }
		 
		 try (Connection conn = ConnectionService.getConnection()){
			 Service<Client> clients = new ClientServiceJDBC(conn);
		     //afficher tous les clients
		     System.out.println("liste des clients");
		    clients.showItems();
		    
		    //insertion client
		    Client client = new Client(33, "Toto", "Delgado", "toto@delgado.fr", 
		    		"$2y$10$M09LY1dfH/Kg3t7ovtaOpOgDkRvfOtuQQ4VEQgxkSIM3QaRFHzxMi");
		    client.setPhone("+33698785652");
		    client.setCompanyName("Pomona");
			client.setCompanyAddress("44, av. Paul Krüger");
			City city = new City("Villeurbanne", "69100", Country.France);
			city.setID(44);
			client.setCompanyCity(city);
			System.out.println("\n insérer client");
			clients.createItem(client);
			
			 //afficher un client
		    System.out.println("\n infos client");
		    clients.showItem(client.getId());
		    
		    clients.createItem(client);
		    
			//update client
			client.setCompanyAddress("25 rue du Plat");
			City city2 = new City("Lyon 2", "69002", Country.France);
			city2.setID(49);
			client.setCompanyCity(city2);
			clients.updateItem(client.getId(), client);
			
			System.out.println("\n infos client");
		    clients.showItem(client.getId());
			
		    //suppression client
		    clients.deleteItem(client.getId());
		 } catch (SQLException ex) {
		     // handle any errors
		     System.out.println("SQLException: " + ex.getMessage());
		     System.out.println("SQLState: " + ex.getSQLState());
		     System.out.println("VendorError: " + ex.getErrorCode());
		 }
		
		/*Service<City> cities = new CityServiceImpl();
		
		AdServiceImpl ads = new AdServiceImpl();
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
		InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
		CalcVatPrice calcVat = new CalcVatPriceImpl();
		double vatPrice = calcVat.calcVatPrice(price, 0.2);
		CalcNetPrice calcNet = new CalcNetPriceImpl();
		double netPrice = calcNet.calcNetPrice(price, 0.2);
		System.out.println("vat price: "+vatPrice);
		System.out.println("net price: "+netPrice);
		Invoice invoice = new Invoice(1, LocalDateTime.now(), "Coca Cola", "115150", "44, av. Paul Krüger, 69100 Villeurbanne", price, vatPrice, netPrice);
		System.out.println(invoice);
		
		Service<Area> areaService = new AreaServiceImpl();
		Service<BusStop> busStopService = new BusStopServiceImpl();
		Service<TimeSlot> timeSlotService = new TimeSlotServiceImpl();
		AdminConsole.showConsole(sc, areaService, busStopService, timeSlotService);
		
		cities.addItem(city2);
		
		ClientConsole.showConsole(sc, clients, cities);
		
		Service<Advertisment> adService = new AdServiceImpl();
		AdConsole.showConsole(sc, clients.getItem(0), adService, areaService, timeSlotService);
	
		sc.close();
		*/
	}
	

}
