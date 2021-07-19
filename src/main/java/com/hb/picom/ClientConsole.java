package com.hb.picom;

import java.util.Scanner;

import com.hb.picom.pojos.City;
import com.hb.picom.pojos.Client;
import com.hb.picom.pojos.Country;
import com.hb.picom.services.Service;
public class ClientConsole {

	public static void showConsole(Scanner sc, Service<Client> clients, Service<City> cities) {
		
		int menu = 0;
		do {
			System.out.println("1. lister les Clients");
			System.out.println("2. ajouter un Client");
			System.out.println("3. modifier un Client");
			System.out.println("4. supprimer un Client");
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
				clients.showItems();
				break;
			case 2:
				Client client = new Client();
				try {
					client = addClient(sc, cities, client,true);
				}catch (Exception e) {
					System.err.println(e.getMessage());
				}
				clients.addItem(client);
				break;
			case 3:
				clients.showItems();
				System.out.println("saisir un id:");
				int id = 0;
				try {
					id = Integer.parseInt(sc.nextLine());
				}catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				Client client2 = clients.getItem(id);
				try {
					client2 = addClient(sc, cities, client2,false);
				}catch (Exception e) {
					System.err.println(e.getMessage());
				}
				clients.updateItem(id, client2);
				break;
			case 4:
				clients.showItems();
				System.out.println("saisir un id:");
				int id2 = 0;
				try {
					id2 = Integer.parseInt(sc.nextLine());
				}catch (Exception e) {
					System.err.println(e.getMessage());
					break;
				}
				
				clients.deleteItem(id2);
				break;
			case 5:
				return;
			default:
				System.err.println("mauvaise saisie :(");
			}
		}while(menu != 5);
		
	}

	private static Client addClient(Scanner sc, Service<City> cities, Client client, boolean create) {
		if(create){
			System.out.println("saisir un id: ");
			int id = 0;
			id = Integer.parseInt(sc.nextLine());
			client.setId(id);
		}
		
		System.out.println("saisir un prenom: ");
		String fistName  = sc.nextLine();
		client.setFirstName(fistName);
		
		System.out.println("saisir un nom: ");
		String lastName  = sc.nextLine();
		client.setLastName(lastName);
		
		System.out.println("saisir un email: ");
		String email  = sc.nextLine();
		client.setEmail(email);
		
		System.out.println("saisir un nom de société: ");
		String companyName  = sc.nextLine();
		client.setCompanyName(companyName);
		
		System.out.println("saisir un SIRET: ");
		String companySIRET  = sc.nextLine();
		client.setCompanySIRET(companySIRET);
		
		System.out.println("saisir une adresse: ");
		String address  = sc.nextLine();
		client.setCompanyAddress(address);
		
		System.out.println("liste des villes: ");
		cities.showItems();
		City city = new City();
		System.out.println("saisir un nom de ville: ");
		String cityName = sc.nextLine();
		city.setName(cityName);
		
		System.out.println("saisir un code postal: ");
		String cityZipCode = sc.nextLine();
		city.setZipCode(cityZipCode);
		
		System.out.println("liste des pays");
		for (Country country : Country.values()) {
			System.out.println(country);
		}
		System.out.println("saisir un pays:");
		String countryName = sc.nextLine();
		city.setCountry(countryName);
		
		client.setCompanyCity(city);
		
		return client;
	}
}
