package com.webscrapingclient.json.map.poi;

/**
 * La classe Rating � la classe astratta relativa alla votazione degli utenti sulle varie piattaforme dalle
 * quali vengono prelevati i dati
 * @author Andrea
 *
 */
public class Rating {

	private int review;
	private double value;

	/**
	 * Costruttore di default della classe Rating
	 */
	public Rating() {
		review = 0;
		value = 0.0;
	}
	
	/**
	 * Costruttore per la classe Rating
	 * @param review
	 * @param value
	 */
	public Rating(int review, int value) {
		this.review = review;
		this.value = value;
	}

	/**
	 * Restituisce il numero di review del Rating
	 * @return
	 */
	public int getReview() {
		return review;
	}

	/**
	 * Imposta il numero delle review
	 * @param review
	 */
	public void setReview(int review) {
		this.review = review;
	}
	
	/**
	 * Restituisce il valore del Rating
	 * @return
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Imposta il valore del Rating
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * Metodo toString per la classe Rating
	 */
	@Override
	public String toString() {
		return String.format("[%s - Value:%.2f, Review:%d]", getClass()
				.getSimpleName(), value, review);
	}
}
