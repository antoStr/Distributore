package com;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gestore extends ListaProdotti {

	Scanner scan = new Scanner(System.in);

	public void visInventario() { // metodo che mostra l'inventario completo, compreso di bavande esaurite
		System.out.println(ListaProdotti.lista().toString());
	}

	// Aggiunge un nuovo prodotto
	public void addProdotto() {
		boolean vaBene = false;
		boolean vaBenissimo = false;
		double prezzo = 0;
		int quantita = 0;

		// Aggiunge il nome della bevanda
		System.out.println("Inserire nome bevanda:");
		String nome = scan.nextLine();

		// Aggiunge il codice della bevanda
		System.out.println("Inserire codice bevanda:");
		String codice = scan.nextLine().toUpperCase();

		// Controlla se il codice esiste già
		for (Prodotto prodotto : ListaProdotti.lista()) {
			do {
				if (prodotto.getCodice().equals(codice)) {
					System.out.println("Codice già esistente, riprova");
					codice = scan.next().toUpperCase();
				} else {
					vaBenissimo = true;
				}
			} while (!vaBenissimo);

		}

		// Aggiunge il prezzo della bevanda
		do {
			System.out.println("Inserire prezzo bevanda:");
			try {
				prezzo = scan.nextDouble();
				if (prezzo <= 0) {
					throw new PrezzoNonValidoException("Importo non valido, riprova.");
				}
				vaBene = true;
			} catch (InputMismatchException e) {
				System.out.println("Importo non valido, riprova.");
				scan.next();
				vaBene = false;
			} catch (PrezzoNonValidoException e) {
				System.out.println("Importo non valido, riprova.");
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
		ListaProdotti.lista().add(newProdotto);
		System.out.println("Prodotto aggiunto con successo");
		scan.nextLine();
	}

	// Rimozione di un prodotto
	public void delBevanda() {
		System.out.println("Inserire codice del prodotto da rimuovere");
		String codice = scan.next().toUpperCase();

		// Controlla se esiste il codice
		for (Prodotto prodotto : ListaProdotti.lista()) {
			if (prodotto.getCodice().equals(codice)) {
				ListaProdotti.lista().remove(prodotto);
				break;
			}
		}
		System.out.println("Prodotto eliminato con successo!");
		scan.nextLine();
	}

	public void modBevanda() { // metodo che può modificare codice, nome, prezzo e quantità di una bevanda
		System.out.println("Inserire codice del prodotto da modificare");
		System.out.println(" ");
		System.out.println(ListaProdotti.lista());
		String codice = scan.next().toUpperCase();
		for (Prodotto prodotto : ListaProdotti.lista()) {
			if (prodotto.getCodice().equals(codice)) {
				boolean run = true;
				boolean vaBene = false;
				boolean vaBenissimo = false;
				double newPrz = 0;
				int select = 0;

				do {// menu per modificare i campi
					do {
						try {
							System.out.println("Seleziona campo da modificare");
							System.out.println("1. Codice");
							System.out.println("2. Nome");
							System.out.println("3. Prezzo");
							System.out.println("4. Esci");

							select = scan.nextInt();
							vaBene = true;
						} catch (InputMismatchException e) {
							System.out.println("Scelta non valida, riprova.");
							scan.nextLine();
							vaBene = false;
						}
					} while (!vaBene);

					switch (select) {
					case 1:
						System.out.println("Inserisci nuovo codice");
						String newCod = scan.next();

						for (Prodotto prodotto2 : ListaProdotti.lista()) {
							if (prodotto2.getCodice().equals(codice)) {
								System.out.println("Codice già esistente, riprova:");
							} else {
								prodotto.codice = newCod;
							}
						}
						break;
					case 2:
						System.out.println("Inserisci nuovo nome");
						String newNome = scan.next();
						prodotto.nome = newNome;
						break;
					case 3:
						System.out.println("Inserisci nuovo prezzo");

						do {
							try {
								newPrz = scan.nextDouble();
								if (newPrz <= 0) {
									throw new PrezzoNonValidoException("Importo non valido, riprova.");
								}
								vaBenissimo = true;
							} catch (PrezzoNonValidoException e) {
								System.out.println(e.getMessage());
								vaBenissimo = false;
							} catch (InputMismatchException e) {
								System.out.println("Importo non valido, riprova.");
								scan.nextLine();
								vaBenissimo = false;
							}
						} while (!vaBenissimo);

						prodotto.prezzo = newPrz;
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
		scan.nextLine();
	}

	public void rifBevanda() {// metodo per aggiungere o rimuovere bevanda al rifornimento
		System.out.println("Inserire codice del prodotto da rifornire: ");
		String codice = scan.next().toUpperCase();
		for (Prodotto prodotto : ListaProdotti.lista()) {
			if (prodotto.getCodice().equals(codice)) {
				System.out.println("Inserisci numero di prodotti da aggiungere");
				System.out.println(" ");
				System.out.println(ListaProdotti.lista());
				int numero = scan.nextInt();
				prodotto.quantita += numero;
				System.out.println("Prodotto rifornito correttamente.");
			}
		}
		scan.nextLine();
	}

	public static void visIncasso(double creditoDistributore) { // metodo che mostra l'incasso totale
		System.out.printf("Credito: %.2f \u20AC \n", creditoDistributore);
	}

	public double resetCredito(double credito) { // metodo per azzerare il credito
		System.out.println("Credito azzerato.");
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

		scan.nextLine();
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
				System.out.println("Seleziona un opzione:");
				System.out.println("1) Visualizza inventario completo");
				System.out.println("2) Aggiungere un nuovo prodotto");
				System.out.println("3) Rimuovere un prodotto");
				System.out.println("4) Modificare un prodotto");
				System.out.println("5) Rifornire un prodotto");
				System.out.println("6) Visualizzare l'incasso totale ");
				System.out.println("7) Resettare incasso distributore");
				System.out.println("8) Uscire dalla modalità gestore ");
				System.out.println("9) Spegni");

				selectMenu = scan.nextInt();
				switch (selectMenu) {

				case 1: {
					System.out.println("Visualizza inventario completo ");
					System.out.println(" ");
					gestore.visInventario();
					break;
				}

				case 2: {
					System.out.println("Aggiungere nuova bevanda.");
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
					System.out.println("Incasso distributore:");
					Gestore.visIncasso(DistributoreLogic.getCreditoDistributore());
					break;
				}

				case 7: {
					System.out.println("Azzeramento del credito.");
					DistributoreLogic.resetCredito();
					break;
				}

				case 8: {
					System.out.println("Uscita dalla modalità gestore.");
					gestore.exit(true);
					vaBene = true;
					break;
				}

				case 9: {
					System.out.println("Spegnimento distributore.");
					System.exit(0);
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

		scan.nextLine();
	}
}
