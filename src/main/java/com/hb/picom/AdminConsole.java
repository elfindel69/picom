package com.hb.picom;

import java.time.LocalTime;
import java.util.Scanner;

import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.BusStop;
import com.hb.picom.pojos.TimeSlot;
import com.hb.picom.services.Service;

public class AdminConsole {
	
	public static void showConsole(Scanner sc, Service<Area> areas, Service<BusStop> busStops, Service<TimeSlot> timeSlots) {
		int menu = 0;
		do {
			System.out.println("1. gérér les Areas");
			System.out.println("2. gérer les BusStops");
			System.out.println("3. gérér les TimeSlots");
			System.out.println("#####");
			System.out.println("4. Quitter");
			try {
				menu = Integer.parseInt(sc.nextLine());
			}
			catch(Exception e) {
				System.err.println(e.getMessage());
			}
			switch (menu) {
			case 1: {
				try {
					manageArea(sc, areas);
				}
				catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			}
			case 2: {
				try {
					manageBusStops(sc, areas,busStops);
				}catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			}
			case 3: {
				try {
					manageTimeSlots(sc, timeSlots);
				}catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
				break;
			}
			case 4:{
				return;
			}
			default:
				System.err.println("saisie invalide :(");
			}
			
			
			
		}while(menu != 4);
		
		
	}
	
	private static void manageTimeSlots(Scanner sc, Service<TimeSlot> timeSlots) {
		int menu = 0;
		
		System.out.println("1. lister les TimeSlots");
		System.out.println("2. ajouter un TimeSlot");
		System.out.println("3. modifier un TimeSlot");
		System.out.println("4. supprimer un TimeSlot");
		menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1: {
			timeSlots.showItems();
			break;
		}
		case 2: {
			TimeSlot timeSlot = addTimeSlot(sc, new TimeSlot(),true);
			timeSlots.addItem(timeSlot);
			break;
		}
		case 3: {
			timeSlots.showItems();
			System.out.println("entrer un id :");
			int id = 0;
			try {
				id = Integer.parseInt(sc.nextLine());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				break;
			}
			
			TimeSlot timeSlot = timeSlots.getItem(id);
			timeSlot = addTimeSlot(sc, timeSlot,false);
			timeSlots.updateItem(id, timeSlot);
			break;
		}
		case 4: {
			timeSlots.showItems();
			System.out.println("entrer un id :");
			int id = 0;
			try {
				id = Integer.parseInt(sc.nextLine());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				break;
			}
			
			timeSlots.deleteItem(id);
			break;
		}
		default:
			System.err.println("saisie invalide :(");
		}
		
	}

	private static TimeSlot addTimeSlot(Scanner sc, TimeSlot timeSlot, boolean create) {
		if(create) {
			System.out.println("entrer un id: ");
			int id = Integer.parseInt(sc.nextLine());
			timeSlot.setId(id);	
		}
		
		System.out.println("entrer une heure de début: ");
		LocalTime startTime = LocalTime.parse(sc.nextLine());
		timeSlot.setStartTime(startTime);
		System.out.println("entrer une heure de fin: ");
		LocalTime endTime = LocalTime.parse(sc.nextLine());
		timeSlot.setEndTime(endTime);
		System.out.println("entrer un multiplicateur: ");
		Double price = Double.parseDouble(sc.nextLine());
		timeSlot.setPrice(price);
		return timeSlot;
	}

	private static void manageBusStops(Scanner sc, Service<Area> areas, Service<BusStop> busStops) {
		int menu = 0;
		
		System.out.println("1. lister les BusStops");
		System.out.println("2. ajouter un BusStop");
		System.out.println("3. modifier un BusStop");
		System.out.println("4. supprimer un BusStop");
		menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1: {
			busStops.showItems();
			break;
		}
		case 2: {
			areas.showItems();
			System.out.println("saisir un id d'Area");
			int id = 0;
			try {
				id = Integer.parseInt(sc.nextLine());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				break;
			}
			
			Area area = areas.getItem(id);
			BusStop busStop = addBusStop(sc, new BusStop(),true);
			area.addBusStop(busStop);
			busStops.addItem(busStop);
			break;
		}
		case 3: {
			areas.showItems();
			System.out.println("saisir un id d'Area");
			int id = 0;
			try {
				id = Integer.parseInt(sc.nextLine());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				break;
			}
			
			Area area = areas.getItem(id);
			area.showBusStops();
			System.out.println("entrer un id de BusStop:");
			try {
				id = Integer.parseInt(sc.nextLine());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				break;
			}
			
			BusStop busStop = busStops.getItem(id);
			busStop = addBusStop(sc, busStop,false);
			area.setBusStop(id, busStop);
			busStops.updateItem(id, busStop);
			break;
		}
		case 4: {
			areas.showItems();
			System.out.println("saisir un id d'Area");
			int id = 0;
			try {
				id = Integer.parseInt(sc.nextLine());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				break;
			}
			Area area = areas.getItem(id);
			area.showBusStops();
			System.out.println("entrer un id de BusStop:");
			try {
				id = Integer.parseInt(sc.nextLine());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				break;
			}
			area.deleteBusStop(id);
			busStops.deleteItem(id);
			break;
		}
		default:
			System.err.println("saisie invalide :(");
		}
		
	}

	private static BusStop addBusStop(Scanner sc, BusStop busStop, boolean create) {
		if(create) {
			System.out.println("entrer un id: ");
			int id = Integer.parseInt(sc.nextLine());
			busStop.setId(id);	
		}
		
		System.out.println("entrer un nom: ");
		String name = sc.nextLine();
		busStop.setName(name);
		System.out.println("entrer une adresse IP: ");
		String iPAddress = sc.nextLine();
		busStop.setIPAdress(iPAddress);
		System.out.println("entrer un point GPS: ");
		String gps = sc.nextLine();
		busStop.setGps(gps);
		return busStop;
	}

	private static void manageArea(Scanner sc, Service<Area> areas) {
		int menu = 0;
		
		System.out.println("1. lister les Areas");
		System.out.println("2. ajouter une Area");
		System.out.println("3. modifier une Area");
		System.out.println("4. supprimer une Area");
		menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1: {
			areas.showItems();
			break;
		}
		case 2: {
			Area area = addArea(sc, new Area(),true);
			areas.addItem(area);
			break;
		}
		case 3: {
			areas.showItems();
			System.out.println("entrer un id:");
			int id = Integer.parseInt(sc.nextLine());
			
			Area area = areas.getItem(id);
			area = addArea(sc, area,false);
			areas.updateItem(id, area);
			break;
		}
		case 4: {
			areas.showItems();
			System.out.println("entrer un id:");
			int id = 0;
			try {
				id = Integer.parseInt(sc.nextLine());
			}catch (Exception e) {
				System.err.println(e.getMessage());
				break;
			}
			
			areas.deleteItem(id);
			break;
		}
		default:
			System.err.println("saisie invalide :(");
		}
		
	}

	private static Area addArea(Scanner sc, Area area, boolean create) {
		if(create) {
			System.out.println("entrer un id: ");
			int id = Integer.parseInt(sc.nextLine());
			area.setId(id);	
		}
		
		System.out.println("entrer un nom: ");
		String name = sc.nextLine();
		area.setName(name);
		System.out.println("entrer un prix de base: ");
		double basePrice = Double.parseDouble(sc.nextLine());
		area.setBasePrice(basePrice);
		return area;
	}
}
