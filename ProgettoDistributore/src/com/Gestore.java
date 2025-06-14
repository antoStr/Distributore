package com;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gestore extends ListaBevande {
	

	Scanner scan = new Scanner(System.in);

	public void visInventario() { // metodo che mostra l'inventario completo, compreso di bavande esaurite
		System.out.println(ListaBevande.loadBevanda().toString());
	}
	
	// Aggiunge un nuovo prodotto
	public void addProdotto() {
		boolean vaBene = false;
		boolean vaBenissimo = false;
		double prezzo = 0;
		int quantita = 0;
		
		// Aggiunge il nome della bevanda
		System.out.println("Inserire nome bevanda:");
		String nome = scan.next();
		
		// Aggiunge il codice della bevanda
		System.out.println("Inserire codice bevanda:");
		String codice = scan.next().toUpperCase();
		
		// Controlla se il codice esiste già
		for (Bevanda bevanda : ListaBevande.loadBevanda()) {
			do {
				if (bevanda.getCodice().equals(codice)) {
					System.out.println("Codice già esistente, riprova");
					codice = scan.next().toUpperCase();
				} else {
					vaBenissimo = true;
				}
			} while (!vaBenissimo);
				
		}			
		
		// Aggiunge il prezzo della bevanda
		do {
			try {
				System.out.println("Inserire prezzo bevanda:");
				prezzo = scan.nextDouble();
				vaBene = true;
			} catch (InputMismatchException e) {
				System.out.println("Inserire prezzo bevanda:");
				scan.next();
				vaBene = false;
			}
		} while (!vaBene);
		vaBene = false;

		// Aggiunge la quantità della bevanda
		do {
			try {
				System.out.println("Inserire quantita bevanda:");
				quantita = scan.nextInt();
				vaBene = true;
			} catch (InputMismatchException e) {
				System.out.println("Inserire quantità bevanda:");
				scan.next();
				vaBene = false;
			}
		} while (!vaBene);
		vaBene = false;

		Bevanda newProdotto = new Bevanda(codice, nome, prezzo, quantita);
		ListaBevande.loadBevanda().add(newProdotto);
		System.out.println("Prodotto aggiunto con successo");
	}
	
	// Rimozione di un prodotto
	public void delBevanda() { 
		System.out.println("Inserire codice del prodotto da rimuovere");
		String codice = scan.next().toUpperCase();
		
		// Controlla se esiste il codice
		for (Bevanda bevanda : ListaBevande.loadBevanda()) {
			if (bevanda.getCodice().equals(codice)) {
				ListaBevande.loadBevanda().remove(bevanda);
				break;
			}
		}
		System.out.println("Prodotto eliminato con successo!");
	}

	public void modBevanda() { // metodo che può modificare codice, nome, prezzo e quantità di una bevanda
		System.out.println("Inserire codice del prodotto da modificare");
		String codice = scan.next();
		for (Prodotto p : ListaBevande.loadBevanda()) {
			if (codice.equals(p.codice)) {
				boolean run = true;
				do {// menu per modificare i campi
					System.out.println("Seleziona campo da modificare");
					System.out.println("1. codice");
					System.out.println("2. nome");
					System.out.println("3. prezzo");
					System.out.println("4. esci");
					int select = scan.nextInt();
					switch (select) {
					case 1:
						System.out.println("Inserisci nuovo codice");
						String newCod = scan.next();
						p.codice = newCod;
						break;
					case 2:
						System.out.println("Inserisci nuovo nome");
						String newNome = scan.next();
						p.nome = newNome;
						break;
					case 3:
						System.out.println("Inserisci nuovo prezzo");
						double newPrz = scan.nextDouble();
						p.prezzo = newPrz;
						break;
					case 4:
						run = false;
						break;
					default:
						System.out.println("Input non valido");
					}
				} while (run);
			}
		}

	}

	public void rifBevanda() {// metodo per aggiungere o rimuovere bevanda al rifornimento
		System.out.println("Inserire codice del prodotto da rifornire: ");
		String codice = scan.next();
		for (Prodotto p : ListaBevande.loadBevanda()) {
			if (codice.equals(p.codice)) {
				System.out.println("Inserisci numero di prodotti da aggiungere(usa '-' per rimuovere)");
				int numero = scan.nextInt();
				p.quantita += numero;
			}
		}
	}

	public void visIncasso(double credito) { // metodo che mostra l'incasso totale
		System.out.println("Credito: " + credito);
	}

	public double resetCredito(double credito) { // metodo per azzerare il credito
		System.out.println("Credito azzerato");
		credito = 0;
		return credito;
	}

	public boolean exit(boolean continua) {
		continua = false;
		return false;
	}

	public void Menu() {
		System.out.println("Menu");
	}

	public void inserisciPassword() {// metodo che chiede di reinserire la password o tornare indietro
		System.out.println("1. Accedere al menu gestore. ");
		System.out.println("Per uscire inserisci un numero diverso da 1. ");
		int opzione = scan.nextInt();

		if (opzione == 1) {
			System.out.println("Inserire password: ");
			String password = scan.next();
			if (password == "p@ssw0rd") {
				System.out.println("Password corretta.");
				Menu();
			} else {
				System.out.println("Reinserire la password (ultimo tentativo).");
				if (password == "p@ssw0rd") {
					System.out.println("Password corretta.");
					Menu();
				} else {
					System.out.println("Ultimo tentativo fallito. Uscita dal programma.");
					exit(true);
				}
			}
		} else {
			System.out.println("Uscita");
			exit(true);
		}
	}

	public static void menuAdmin() {
		Scanner scan = new Scanner(System.in);
		boolean vaBene = false;

		Gestore gestore = new Gestore();
		System.out.println("Benvenuto!");

		// Init variabili menu
		int selectMenu = 0;
		do {
			try {
				System.out.println(" ");
				System.out.println("Seleziona un opzione: ");
				System.out.println("1) Visualizza inventario completo");
				System.out.println("2) Aggiungere una nuova bevanda ");
				System.out.println("3) Rimuovere una bevanda");
				System.out.println("4) Modificare una bevanda ");
				System.out.println("5) Rifornire una bevanda ");
				System.out.println("6) Visualizzare l'incasso totale ");
				System.out.println("7) Resettare il credito inserito o l’incasso totale");
				System.out.println("8) Uscire dalla modalità gestore ");
				System.out.println("9) Spegni");
				
				selectMenu = scan.nextInt();
				switch (selectMenu) {
	
				case 1: {
					System.out.println("Visualizza inventario completo ");
					gestore.visInventario();
					break;
				}
	
				case 2: {
					System.out.println("Aggiungere una nuova bevanda ");
					gestore.addProdotto();
					break;
				}
	
				case 3: {
					System.out.println("Rimuovere una bevanda ");
					gestore.delBevanda();
					break;
				}
	
				case 4: {
					System.out.println("Modificare una bevanda: ");
					gestore.modBevanda();
					break;
				}
	
				case 5: {
					System.out.println("Rifornire una bevanda ");
					gestore.rifBevanda();
					break;
				}
	
				case 6: {
					System.out.println("Visualizzare l'incasso totale ");
					gestore.visIncasso(0.0);
					break;
				}
	
				case 7: {
					System.out.println("Resettare il credito inserito o l’incasso totale");
					gestore.resetCredito(0.0);
					break;
				}
	
				case 8: {
					System.out.println("Uscita dalla modalità gestore.");
					gestore.exit(true);
					vaBene = true;
					break;
				}
	
				case 9: {
					System.out.println("Spegni");
					break;
				}
	
				default: {
					System.out.println("Scelta non valida, riprova: ");
				}
				}
			} catch (InputMismatchException e) {
				System.out.println("Scelta non valida, riprova: ");
				scan.nextLine();
			}
		} while (!vaBene);

	}
}
