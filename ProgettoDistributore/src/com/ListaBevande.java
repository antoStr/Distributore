package com;

import java.util.ArrayList;

public abstract class ListaBevande {
	
	protected static ArrayList<Bevanda> listaBevande = null;
	
	public static ArrayList<Bevanda> loadBevanda() {		
		
		if (listaBevande == null) {
			listaBevande = new ArrayList<>();
			
			Bevanda bevanda1 = new Bevanda("A01", "Fanta", 1.50, 3 );
			Bevanda bevanda2 = new Bevanda("A02", "Coca Cola", 1.70, 7 );
			Bevanda bevanda3 = new Bevanda("A03", "Acqua", 1, 8 );
			
			listaBevande.add(bevanda1);
			listaBevande.add(bevanda2);
			listaBevande.add(bevanda3);
			
			return listaBevande;
			
		}
		
		return listaBevande;
	}
	

}
