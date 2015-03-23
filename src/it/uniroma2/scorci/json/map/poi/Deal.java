package it.uniroma2.scorci.json.map.poi;

/**
 * Classe Deal che corrisponde alle offerte periodiche
 * 
 * @author Andrea
 * 
 */

public class Deal {

	private double price;
	private String description;

	/**
	 * Costruttore di default
	 */
	public Deal() {
		this.price = 0;
	}

	/**
	 * Costruttore che imposta price e description con quelli passati come
	 * paramentro
	 * 
	 * @param price
	 * @param description
	 */
	public Deal(double price, String description) {
		this.price = price;
		this.description = description;
	}

	/**
	 * Restituisce il prezzo dell'offerta
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Imposta il prezzo dell'offerta con price
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Restituisce la descrizione dell'offerta
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Imposta la descrizione del'offerta con description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
