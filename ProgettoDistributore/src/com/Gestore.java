package com;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gestore extends Lista {
	public Gestore() {
		super();

	}

	Scanner scan = new Scanner(System.in);

	public void visInventario() { // metodo che mostra l'inventario completo, compreso di bavande esaurite
		for (Prodotto p : lista)
			p.toString();
	}

	 public void addProdotto() {// metodo che aggiunge un nuovo prodotto alla lista
		System.out.println("Inserire dati del nuovo prodotto");
		String nome = scan.next();
		String codice = scan.next();
		double prezzo = scan.nextDouble();
		int quantita = scan.nextInt();
		// Roba da sistemare
		// Prodotto non poteva essere istanziato perchè astract
		Prodotto p = new Bevanda(codice, nome, prezzo, quantita);
		lista.add(p);
	}
	

	public void delBevanda() { // metodo per rimuovere una bevanda
		System.out.println("Inserire codice del prodotto da rimuovere");
		String codice = scan.next();
		for (Prodotto p : lista) {
			if (codice.equals(p.codice))
				lista.remove(p);
		}
	}

	public void modBevanda() { // metodo che può modificare codice, nome, prezzo e quantità di una bevanda
		System.out.println("Inserire codice del prodotto da modificare");
		String codice = scan.next();
		for (Prodotto p : lista) {
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
		for (Prodotto p : lista) {
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

		// Init variabili menu
		int selectMenu = 0;
		do {
			try {
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
