package com;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestore extends ListaProdotti {

	Scanner scan = new Scanner(System.in);
	
	// Metodo che mostra l'inventario completo, compreso di bavande esaurite
	public void visInventario() { 
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
				if ( quantita < 0) {
					System.out.println("Numero quantità non valida, riprova.");
				} else {
					vaBene = true;					
				}
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
	
	// Metodo che può modificare codice, nome, prezzo e quantità di una bevanda
	public void modBevanda() { 
		System.out.println("Inserire codice del prodotto da modificare:");
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
				
				// Menu per modificare codice, nome, prezzo di un prodotto
				do {
					do {
						try {
							System.out.println(" ");
							System.out.println("Seleziona campo da modificare:");
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
					
					// Switch per selezione caso
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

	// Metodo per rifornire un prodotto esistente
	public void rifBevanda() {
		boolean trovato = false;
		boolean vaBene = false;
		boolean vaBenissimo = false;
		int numero = 0;
		
		// Controllo del prodotto in base all' input dell'utente
		do {
			System.out.println("Inserire codice del prodotto da rifornire: ");
			System.out.println(" ");
			System.out.println(ListaProdotti.lista());
			String codice = scan.next().toUpperCase();
			for (Prodotto prodotto : ListaProdotti.lista()) {
				if (prodotto.getCodice().equals(codice)) {
					System.out.println("Inserisci numero di prodotti da aggiungere");
					System.out.println(" ");
					
					do {
						try {
							numero = scan.nextInt();
							vaBenissimo = true;
						} catch (InputMismatchException e) {
							System.out.println("Input non corretto, riprova.");
							scan.next();
							vaBenissimo = false;
						}
					} while (!vaBenissimo);
					
					prodotto.quantita += numero;
					System.out.println("Prodotto rifornito correttamente.");
					trovato = true;
					vaBene = true;
					break;
				} 
			}

			if (!trovato) {
				System.out.println("Prodotto non trovato.");
				System.out.println(" ");
			}
		} while (!vaBene);
		
		scan.nextLine();
		trovato = false;
		vaBene = false;
	}
	
	// Metodo che mostra l'incasso totale del distributore
	public static void visIncasso(double creditoDistributore) { 
		System.out.printf("Credito: %.2f \u20AC \n", creditoDistributore);
	}
	
	// Metodo per azzerare il credito del distributore
	public double resetCredito(double credito) { 
		System.out.println("Credito azzerato.");
		credito = 0;
		return credito;
	}

	//Metodo per uscire dal menù gestore
	public boolean exit(boolean continua) {
		continua = false;
		return false;
	}
	
	//Metodo per print menu
	public void Menu() {
		System.out.println("Menu");
	}
	
	// Metodo che chiede di reinserire la password o tornare indietro - da implementare
	public void inserisciPassword() {
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

	// Menù gestore principale
	public static void menuAdmin() {
		Scanner scan = new Scanner(System.in);
		boolean vaBene = false;

		Gestore gestore = new Gestore();
		System.out.println("Benvenuto!");

		// Init variabili menu e vari casi per programma
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
		scan.close();
	}
	
}
