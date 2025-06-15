package com;

public abstract class Prodotto {
	protected String codice;
	protected String nome;
	protected double prezzo;
	protected int quantita;
	
	
	public Prodotto(String codice, String nome, double prezzo, int quantita) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.prezzo = prezzo;
		this.quantita = quantita;
	}


	public String getCodice() {
		return codice;
	}


	public void setCodice(String codice) {
		this.codice = codice;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	@Override
	public String toString() {
		return "Prodotto [codice=" + codice + ", nome=" + nome + ", prezzo=" + prezzo + ", quantita=" + quantita + "]";
	}
	
	public String toStringUser() {
		return String.format("%s | %s, %.2f\u20AC", codice, nome, prezzo);
	}
	
}


