package com;

import java.util.ArrayList;

public abstract class ListaProdotti {
	// cambiare da lista bevande a llista prodotti e fare prodotto bevanda 1 new bevanda 
	protected static ArrayList<Prodotto> listaProdotti = null;
	
	public static ArrayList<Prodotto> lista() {		
		
		if (listaProdotti == null) {
			listaProdotti = new ArrayList<>();
			
			Prodotto bevanda1 = new Bevanda("A01", "Fanta", 1.50, 3 );
			Prodotto bevanda2 = new Bevanda("A02", "Coca Cola", 1.70, 7 );
			Prodotto bevanda3 = new Bevanda("A03", "Acqua", 1, 8 );
			
			listaProdotti.add(bevanda1);
			listaProdotti.add(bevanda2);
			listaProdotti.add(bevanda3);
			
			return listaProdotti;
			
		}
		
		return listaProdotti;
	}
	

}
