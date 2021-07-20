package com.hb.picom;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/*import java.time.LocalDateTime;
import java.time.LocalTime;*/
import java.util.Scanner;



/*import org.json.*;

import com.hb.picom.pojos.Advertisment;
import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.BusStop;
import com.hb.picom.pojos.City;
import com.hb.picom.pojos.Client;
import com.hb.picom.pojos.Country;
import com.hb.picom.pojos.Invoice;
import com.hb.picom.pojos.TimeSlot;
import com.hb.picom.services.Service;
import com.hb.picom.services.impl.AdServiceImpl;
import com.hb.picom.services.impl.AreaServiceImpl;
import com.hb.picom.services.impl.BusStopServiceImpl;
import com.hb.picom.services.impl.CityServiceImpl;
import com.hb.picom.services.impl.ClientServiceImpl;
import com.hb.picom.services.impl.InvoiceServiceImpl;
import com.hb.picom.services.impl.TimeSlotServiceImpl;*/

public class PiComMain {
	//private static Scanner sc = new Scanner(System.in);
	
	private static void getClient(Connection conn, int id) {
		 ResultSet rs = null;
		 PreparedStatement ps = null;
		 try {
			 String query = "SELECT client_id,client_first_name,client_last_name,client_email,"
			 		+ "client_phone,client_company_name,client_address,city_name,country_name"
			 		+ " FROM client INNER JOIN city ON client.id_city = city.city_id "
			 		+ "INNER JOIN country ON city.id_country= country.country_id"
			 		+ " WHERE client.client_id = ?";
			 ps = conn.prepareStatement(query);
			 ps.setInt(1, id);
			 
			 rs = ps.executeQuery();
			 ResultSetMetaData metaData  = rs.getMetaData();
			 int columsNb = metaData.getColumnCount();
			 showColumnNames(metaData, columsNb);
			 
			 
			 while(rs.next()){
				 for (int i = 1;i<=columsNb;i++) {
		        	  if(i>1) {
		        		  System.out.print("|");
		        		
		        	  }
		        	  Object value = rs.getObject(i);
		        	  System.out.print(value);
		          }
		            System.out.println("");
		            
	        }

		    
			
		 } catch (SQLException ex) {
		     // handle any errors
		     System.out.println("SQLException: " + ex.getMessage());
		     System.out.println("SQLState: " + ex.getSQLState());
		     System.out.println("VendorError: " + ex.getErrorCode());
		 }
		 finally {
			    // it is a good idea to release
			    // resources in a finally{} block
			    // in reverse-order of their creation
			    // if they are no-longer needed

			    if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException sqlEx) { } // ignore

			        rs = null;
			    }

			    if (ps != null) {
			        try {
			            ps.close();
			        } catch (SQLException sqlEx) { } // ignore

			        ps = null;
			    }
			}
		 
	}
	
	private static void getClients(Connection conn) {
		 Statement stmt = null;
		 ResultSet rs = null;
		 
		 try {
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("SELECT client_id,client_first_name,client_last_name,client_email,"
			 		+ "client_phone,client_company_name,client_address,city_name,country_name"
			 		+ " FROM client INNER JOIN city ON client.id_city = city.city_id "
			 		+ "INNER JOIN country ON city.id_country= country.country_id");
			 
			 ResultSetMetaData metaData  = rs.getMetaData();
			 int columsNb = metaData.getColumnCount();
			 showColumnNames(metaData, columsNb);
			 
			 while(rs.next()){
				 for (int i = 1;i<=columsNb;i++) {
		        	  if(i>1) {
		        		  System.out.print("|");
		        		
		        	  }
		        	  Object value = rs.getObject(i);
		        	  System.out.print(value);
		          }
		            System.out.println("");
		            
	        }

		    
			
		 } catch (SQLException ex) {
		     // handle any errors
		     System.out.println("SQLException: " + ex.getMessage());
		     System.out.println("SQLState: " + ex.getSQLState());
		     System.out.println("VendorError: " + ex.getErrorCode());
		 }
		 finally {
			    // it is a good idea to release
			    // resources in a finally{} block
			    // in reverse-order of their creation
			    // if they are no-longer needed

			    if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException sqlEx) { } // ignore

			        rs = null;
			    }

			    if (stmt != null) {
			        try {
			            stmt.close();
			        } catch (SQLException sqlEx) { } // ignore

			        stmt = null;
			    }
			}
		 
	}


	private static void showColumnNames(ResultSetMetaData metaData, int columsNb) throws SQLException {
		for (int i = 1;i<=columsNb;i++) {
			  if(i>1) {
				  System.out.print("|");
				
			  }
			  String column = metaData.getColumnName(i);
			  System.out.print(column);
		 }
		 System.out.println("");
	}
	
	
	public static void main(String[] args)throws Exception {
		
		InputStream aInputStream = PiComMain.class.getResourceAsStream("/env/app.env");
		Properties env = new Properties();
		
		env.load(aInputStream);
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
		 
		 Connection conn = null;
		
		 String jdbcUrl = env.getProperty("jdbc.url");
		 String jdbcDB = env.getProperty("jdbc.db");
		 String jdbcUser = env.getProperty("jdbc.user");
		 String jdbcPassword = env.getProperty("jdbc.password");
		 
		 try {
		     conn = DriverManager.getConnection(jdbcUrl+"/"+jdbcDB,jdbcUser,jdbcPassword);
		     System.out.println("liste des clients");
		    getClients(conn);
		    System.out.println("\n infos client");
		    getClient(conn, 1);
			
		 } catch (SQLException ex) {
		     // handle any errors
		     System.out.println("SQLException: " + ex.getMessage());
		     System.out.println("SQLState: " + ex.getSQLState());
		     System.out.println("VendorError: " + ex.getErrorCode());
		 }
		
		/*Service<City> cities = new CityServiceImpl();
		Service<Client> clients = new ClientServiceImpl();
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
		City city2 = new City("Lyon 2", "69002", Country.France);
		cities.addItem(city2);
		
		ClientConsole.showConsole(sc, clients, cities);
		
		Service<Advertisment> adService = new AdServiceImpl();
		AdConsole.showConsole(sc, clients.getItem(0), adService, areaService, timeSlotService);
	
		sc.close();
		*/
	}

}
