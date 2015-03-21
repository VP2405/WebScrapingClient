package com.webscrapingclient.json.map.profile;

/**
 * Classe per la definizione degli attributi relativi al profilo utente in base
 * a cui verranno recuperati i poi ordinati. Nel momento in cui un utente
 * intende visualizzare gli attributi di un particolare profilo scelto dallo
 * spinner della MainActivity, questa classe viene utilizzata per costruire un
 * oggetto a partire dal recupero del Json correlato, ottenuto dalla chiamata
 * all'API REST ../profile/id e dal parsing dello stesso
 * 
 * @author Vanessa
 * 
 */

public class CommercialProfile {

	/**
	 * Costruttore per la classe CommercialProfile
	 * 
	 * @param id
	 * @param isMiser
	 * @param favouriteCuisine
	 * @param isDemanding
	 * @param isDemandingPercentage
	 * @param isMiserPercentage
	 * @param lastPosition
	 */
	public CommercialProfile(int id, int isMiser, String favouriteCuisine,
			int isDemanding, double isDemandingPercentage,
			double isMiserPercentage, Position lastPosition) {
		super();
		this.id = id;
		this.isMiser = isMiser;
		this.favouriteCuisine = favouriteCuisine;
		this.isDemanding = isDemanding;
		this.isDemandingPercentage = isDemandingPercentage;
		this.isMiserPercentage = isMiserPercentage;
		this.lastPosition = lastPosition;
	}

	private int id;
	private int isMiser;
	private String favouriteCuisine;
	private int isDemanding;
	private double isDemandingPercentage;
	private double isMiserPercentage;
	private Position lastPosition;

	/**
	 * Restituisce l'id del profilo
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Imposta l'id del profilo
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Restituisce la percentuale di ricchezza
	 * 
	 * @return
	 */
	public double getIsMiserPercentage() {
		return isMiserPercentage;
	}

	/**
	 * Imposta la percentuale di ricchezza
	 * 
	 * @param isMiserPercentage
	 */
	public void setIsMiserPercentage(double isMiserPercentage) {
		this.isMiserPercentage = isMiserPercentage;
	}

	/**
	 * Imposta l'ultima posizione dell'utente
	 * 
	 * @return
	 */
	public Position getLastPosition() {
		return lastPosition;
	}

	/**
	 * Imposta l'ultima posizione dell'utente
	 * 
	 * @param lastPosition
	 */
	public void setLastPosition(Position lastPosition) {
		this.lastPosition = lastPosition;
	}

	/**
	 * Restituisce la percentuale di richiesta in fatto di rating
	 * 
	 * @return
	 */
	public double getIsDemandingPercentage() {
		return isDemandingPercentage;
	}

	/**
	 * Imposta la percentuale di richiesta in fatto di rating
	 * 
	 * @param isDemandingPercentage
	 */
	public void setIsDemandingPercentage(double isDemandingPercentage) {
		this.isDemandingPercentage = isDemandingPercentage;
	}

	/**
	 * Restituisce il valore di richiesta in fatto di rating
	 * 
	 * @return
	 */
	public int getIsDemanding() {
		return isDemanding;
	}

	/**
	 * Imposta il valore di richiesta in fatto di rating
	 * 
	 * @param isDemanding
	 */
	public void setIsDemanding(int isDemanding) {
		this.isDemanding = isDemanding;
	}

	/**
	 * Restituisce la cucina preferita dall'utente del profilo
	 * 
	 * @return
	 */
	public String getFavouriteCuisine() {
		return favouriteCuisine;
	}

	/**
	 * Imposta la cucina preferita dall'utente del profilo
	 * 
	 * @param favouriteCuisine
	 */
	public void setFavouriteCuisine(String favouriteCuisine) {
		this.favouriteCuisine = favouriteCuisine;
	}

	/**
	 * Restituisce il valore di ricchezza
	 * 
	 * @return
	 */
	public int getIsMiser() {
		return isMiser;
	}

	/**
	 * Imposta il valore di ricchezza
	 * 
	 * @param isMiser
	 */
	public void setIsMiser(int isMiser) {
		this.isMiser = isMiser;
	}

	@Override
	public String toString() {
		return " Profile ID = " + id + "\n Miser = " + isMiser
				+ "\n Favourite Cuisine = " + favouriteCuisine
				+ "\n Demanding = " + isDemanding
				+ "\n Demanding Percentage = " + isDemandingPercentage
				+ "\n Miser Percentage = " + isMiserPercentage
				+ "\n Last Position = " + lastPosition;
	}

}
