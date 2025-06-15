package com;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class DistributoreLogic {
	protected static double creditoDistributore = 0;

	// Main menu
	static void menuInit() {
		System.out.println("Benvenutə!");
		System.out.println("Inserisci codice prodotto: ");
		System.out.println("____________________________ ");
	}

	public static double getCreditoDistributore() {
		return creditoDistributore;
	}

	public static void resetCredito() {
		creditoDistributore = 0;
		System.out.println("Credito azzerato.");
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
		double[] moneteAccettate = { 0.1, 0.2, 0.5, 1.0, 2.0 };

		// Main input Utente
		System.out.println(" ");
		ListaProdotti.stampaListaUtente();
		System.out.println("____________________________ ");
		System.out.println(" ");
		inputUtente = scan.next();

		do {
			// Controllo Admin
			if (inputUtente.equals(admin)) {
				Gestore.menuAdmin();

			} else {
				// Ricerca bevanda
				for (Prodotto prodotto : ListaProdotti.lista()) {
					// Controllo dell codice della bevanda se uguale all'input e controllo della
					// quantità
					if (inputUtente.equalsIgnoreCase(prodotto.getCodice()) && prodotto.getQuantita() > 0) {
						System.out.println(" ");
						System.out.printf("Prezzo: %.2f\u20AC\n", prodotto.getPrezzo());
						corretto = true;

						// Programma di vendita
						do {
							// Controllo errore input
							do {
								try {
									prezzoMenu();
									selezioneMoneta = scan.nextInt();
									vaBene = true;
								} catch (InputMismatchException e) {
									System.out.println("Scelta non valida, riprova:");
									System.out.printf("Credito inserito: " + "%.2f \u20AC \n", creditoOperazione);
									System.out.println(" ");
									scan.nextLine();
									vaBene = false;
								}
							} while (!vaBene);

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
								System.out.println("Moneta non valida, inseriscine una nuova.");
							}

							System.out.println(" ");
							System.out.printf("Credito inserito: " + "%.2f \u20AC \n", creditoOperazione);

						} while (prodotto.getPrezzo() > creditoOperazione);

						// Gestione resto

						if (creditoOperazione > prodotto.getPrezzo()) {

							restoOperazione = creditoOperazione - prodotto.getPrezzo();

							while (restoOperazione > creditoOperazione) {
								restoOperazione -= moneteAccettate[4];
							}
							while (restoOperazione > creditoOperazione) {
								restoOperazione -= moneteAccettate[3];
							}
							while (restoOperazione > creditoOperazione) {
								restoOperazione -= moneteAccettate[2];
							}
							while (restoOperazione > creditoOperazione) {
								restoOperazione -= moneteAccettate[1];
							}
							while (restoOperazione > creditoOperazione) {
								restoOperazione -= moneteAccettate[0];
							}
						}

						// Aggiungo creditoOperazione al credito della macchinetta

						creditoDistributore += prodotto.getPrezzo();
						prodotto.setQuantita(prodotto.getQuantita() - 1);

						System.out.printf("Erogazione resto: " + "%.2f \u20AC \n", restoOperazione); // bruh
						break;
					}
				}

				// Prodotto inesistente o esaurito
				if (!corretto) {
					try {
						throw new ProdottoNonTrovatoException("Prodotto non trovato o esaurito");
					} catch (ProdottoNonTrovatoException e) {
						System.out.println(e.getMessage());
					}
				}

				corretto = false;
				restoOperazione = 0;
				creditoOperazione = 0;
			}
			scan.nextLine();
			System.out.println(" ");
			System.out.println("Inserisci un codice prodotto:");
			System.out.println("____________________________ ");
			System.out.println(" ");
			ListaProdotti.stampaListaUtente();
			System.out.println("____________________________ ");
			System.out.println(" ");
			inputUtente = scan.next();

		} while (continua);
		
		scan.close();

	}

}
