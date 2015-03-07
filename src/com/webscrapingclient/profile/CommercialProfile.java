package com.webscrapingclient.profile;

/**
 * Definisce la rappresentazione del profilo commerciale relativo, nello
 * specifico, ad Hotels e Ristoranti
 * 
 * @author Emanuele Altomare
 */

public class CommercialProfile extends Profile {

	private final static int MIN_DEMANDING = 1;
	private final static int MAX_DEMANDING = 3;

	private String favouriteCuisine;
	private int isDemanding;

	public CommercialProfile(PoiProfileStrategy strategy,
			UserPosition lastPosition) {
		super(strategy, lastPosition);
		this.favouriteCuisine = null;
		this.isDemanding = 0;
	}

	public String getFavouriteCuisine() {
		return this.favouriteCuisine;
	}

	public void setFavouriteCuisine(String favouriteCuisine) {
		this.favouriteCuisine = favouriteCuisine;
	}

	public int getIsDemanding() {
		return this.isDemanding;
	}

	/**
	 * Restituisce la percentuale di pignoleria dell'utente profilato.
	 * 
	 * @return
	 */
	public double getIsDemandingPercentage() {
		return (this.isDemanding * 100) / MAX_DEMANDING;
	}

	public void setIsDemanding(int isDemanding) throws Exception {
		if (isDemanding >= MIN_DEMANDING && isDemanding <= MAX_DEMANDING)
			this.isDemanding = isDemanding;
		else
			throw new Exception(String.format(
					"The isDemanding value must be between %d and %d",
					MIN_DEMANDING, MAX_DEMANDING));
	}
}
