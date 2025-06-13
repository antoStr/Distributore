package com;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class DistributoreLogic {
	
	static void menuInit() {
		System.out.println("Benvenutə!");
		System.out.println("Inserisci codice: ");
	}
	
	static void prezzoMenu() {
		System.out.println("Inserire monete:");
		System.out.println("1) 0,10€");
		System.out.println("2) 0,20€");
		System.out.println("3) 0,50€");
		System.out.println("4) 1,00€");
		System.out.println("5) 2,00€");
	}

	public static void mainDistributore() {
		Scanner scan = new Scanner(System.in);
		
		// Inizializzazione ambiente distributore
		String admin = "admin123";
		String inputUtente;
		boolean continua = true;
		boolean corretto = false;
		boolean vaBene = false;
		double creditoOperazione = 0;
		double restoOperazione = 0;
		int selezioneMoneta = 0;
		
		// Mini Data beis
		ArrayList<Bevanda> codiciBevanda = new ArrayList<>();
		double[] moneteAccettate = {0.1, 0.2, 0.5, 1.0, 2.0};

		Bevanda bevanda1 = new Bevanda("A01", "Fanta", 1.50, 3 );
		Bevanda bevanda2 = new Bevanda("A02", "Coca Cola", 1.70, 7 );
		Bevanda bevanda3 = new Bevanda("A03", "Acqua", 1, 8 );
		
		codiciBevanda.add(bevanda1);
		codiciBevanda.add(bevanda2);
		codiciBevanda.add(bevanda3);
		
		System.out.println("Bevande disponibili:");
		System.out.println(bevanda1);
		System.out.println(bevanda2);
		System.out.println(bevanda3);
		
		
		// Main input Utente
		
		inputUtente = scan.next();
		
		
		do {
			// Controllo Admin
			if (inputUtente.equals(admin)) {
				System.out.println("bro sei un pro");
				
			} else {
				// Ricerca bevanda
				for (Bevanda bevanda : codiciBevanda) {
					// Controllo dell codice della bevanda se uguale all'input e controllo della quantità
					if (inputUtente.equalsIgnoreCase(bevanda.getCodice()) && bevanda.getQuantita() > 0) {
						System.out.println("Prezzo: " + bevanda.getPrezzo());
						corretto = true;
						prezzoMenu();
						
						// Programma di vendita
						do {	
							selezioneMoneta = scan.nextInt();
							// Controllo errore input
							/*
							do {
								try {
									vaBene = true;
								} catch (Exception e) {
									System.out.println("erors");
								}
							} while (!vaBene);
							*/								
							
							// Selezione moneta da inserire
							switch (selezioneMoneta) {
							case 1: {
								creditoOperazione += moneteAccettate[0];
								break;
							}
							case 2: {
								creditoOperazione += moneteAccettate[1];
								break;
							}
							case 3: {
								creditoOperazione += moneteAccettate[2];
								break;
							}
							case 4: {
								creditoOperazione += moneteAccettate[3];
								break;
							}
							case 5: {
								creditoOperazione += moneteAccettate[4];
								break;
							}
							default:
								System.out.println("Da vedere che cosa mettere qua");
							}
							
							System.out.printf("%.2f \u20AC \n", creditoOperazione);
		
						} while (bevanda.getPrezzo() > creditoOperazione); 
						
						// Gestione resto
						
						if ( creditoOperazione > bevanda.getPrezzo()) {
							
							restoOperazione = creditoOperazione - bevanda.getPrezzo();
							
							while (restoOperazione  > creditoOperazione) {
								restoOperazione -= moneteAccettate[4];
							} 
							while (restoOperazione  > creditoOperazione) {
								restoOperazione -= moneteAccettate[3];
							} 
							while (restoOperazione  > creditoOperazione) {
								restoOperazione -= moneteAccettate[2];
							} 
							while (restoOperazione  > creditoOperazione) {
								restoOperazione -= moneteAccettate[1];
							} 
							while (restoOperazione  > creditoOperazione) {
								restoOperazione -= moneteAccettate[0];
							} 
								
							System.out.println("Erogazione resto.");
						}

						// Aggiungo creditoOperazione al credito della macchinetta
				
						bevanda.setQuantita(bevanda.getQuantita() -1);

						System.out.printf("%.2f \u20AC \n", restoOperazione); // bruh
						break;
					} 
				}
				
				// Prodotto inesistente o esaurito
				if (!corretto) {
					System.out.println("Prodotto inesistente o esaurito");
				}
				
				corretto = false;
				restoOperazione = 0;
				creditoOperazione = 0;
			}
			System.out.println("Inserisci un codice");
			
			inputUtente = scan.next();
			
		} while (continua);

	}

}
