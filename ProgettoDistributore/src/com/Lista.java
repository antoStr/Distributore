package com;

import java.util.ArrayList;
import java.util.List;

public class Lista {
	List<Prodotto> lista;
	int numeoPosti; // questo sarebbe il numero massimo di posti ma l'ho aggiunto all'ultimo momento. Per ora è inutilizzato

	public Lista() {
		this.lista = new ArrayList<>();
		
	}
}