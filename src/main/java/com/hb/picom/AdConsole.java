package com.hb.picom;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.hb.picom.pojos.Advertisment;
import com.hb.picom.pojos.Area;
import com.hb.picom.pojos.Client;
import com.hb.picom.pojos.TimeSlot;
import com.hb.picom.services.Service;

public class AdConsole {
	public static void showConsole(Scanner sc, Client client, Service<Advertisment> adService, 
			 Service<Area> areaService, Service<TimeSlot> timeSlotService ) {
		int menu = 0;
		do {
			System.out.println("1. lister les Advertisments");
			System.out.println("2. ajouter un Advertisment");
			System.out.println("3. modifier un Advertisment");
			System.out.println("4. supprimer un Advertisment");
			System.out.println("#####");
			System.out.println("5. Quitter");
			try {
				menu = Integer.parseInt(sc.nextLine());
			}
			catch(Exception e) {
				System.err.println(e.getMessage());
			}
			switch (menu) {
			case 1:
				adService.showItems();
				break;
			case 2:
				Advertisment ad = new Advertisment();
				try {
					ad = addAdd(sc, ad, areaService, timeSlotService);
				}catch (Exception e) {
					System.err.println(e.getMessage());
				}
				ad.setClientID(client.getId());
				client.addAd(ad);
				adService.createItem(ad);
				break;
			case 3:
				adService.showItems();
				System.out.println("saisir un id:");
				int id = 0;
				try {
					id = Integer.parseInt(sc.nextLine());
				}catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				Advertisment ad2 = adService.getItem(id);
				try {
					ad2 = addAdd(sc, ad2, areaService, timeSlotService);
				}catch (Exception e) {
					System.err.println(e.getMessage());
				}
				client.setAd(id, ad2);
				adService.updateItem(id, ad2);
				break;
			case 4:
				adService.showItems();
				System.out.println("saisir un id:");
				int id2 = 0;
				try {
					id2 = Integer.parseInt(sc.nextLine());
				}catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				client.deleteAd(id2);
				adService.deleteItem(id2);
				break;
			case 5:
				return;
			default:
				System.err.println("mauvaise saisie :(");
			}
		}while(menu != 5);
		
	}

	private static Advertisment addAdd(Scanner sc, Advertisment ad, Service<Area> areaService,
			Service<TimeSlot> timeSlotService) {
		
		System.out.println("saisir un titre:");
		String title = sc.nextLine();
		ad.setTitle(title);
		
		System.out.println("saisir une description:");
		String desc = sc.nextLine();
		ad.setDescription(desc);
		
		System.out.println("saisir un chemin de fichier:");
		String urlImage = sc.nextLine();
		ad.setUrlImage(urlImage);
		
		System.out.println("saisir une date de début:");
		LocalDateTime startDate = LocalDateTime.parse(sc.nextLine());
		ad.setStartDate(startDate);
		
		System.out.println("saisir une date de fin:");
		LocalDateTime endDate = LocalDateTime.parse(sc.nextLine());
		ad.setEndDate(endDate);
		
		char quit = 'y';
		do{
			System.out.println("liste des Areas");
			areaService.showItems();
			System.out.println("saisir un id d'Area");
			int id = Integer.parseInt(sc.nextLine());
			Area area = areaService.getItem(id);
			System.out.println("voulez vous continuer? (y/n)");
			quit = sc.nextLine().charAt(0);
			ad.addArea(area);
		}while(quit != 'n');
		
		
		quit = 'y';
		do{
			System.out.println("liste des TimeSlots");
			areaService.showItems();
			System.out.println("saisir un id de TimeSlot");
			int id = Integer.parseInt(sc.nextLine());
			TimeSlot timeSlot = timeSlotService.getItem(id);
			System.out.println("voulez vous continuer? (y/n)");
			quit = sc.nextLine().charAt(0);
			ad.addTimeSlot(timeSlot);
		}while(quit != 'n');
		
		
		return ad;
	}
}
