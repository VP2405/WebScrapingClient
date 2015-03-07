package com.webscrapingclient.profile;

/**
 * Definisce l'astrazione della classe responsabile della creazione del Profilo
 * e della Strategia relativa.
 * 
 * @author Emanuele Altomare
 */
public abstract class ProfileAbstractFactory {

	public ProfileAbstractFactory() {
	}

	/**
	 * Consente di creare il profilo utente ed abbinargli la strategia relativa.
	 * 
	 * @return un oggetto di tipo {@link Profile} che rappresenta il profilo
	 *         utente.
	 */
	public abstract Profile createProfile();

	/**
	 * Consente di creare la strategia per il recupero di POI ordinati secondo
	 * il profilo utente.
	 * 
	 * @return l'oggetto di tipo {@link PoiProfileStrategy} che rapresenta la
	 *         strategia per il recupero di una lista ordinata di oggetti di
	 *         tipo {@link Poi} in base al profilo utente.
	 */
	public abstract PoiProfileStrategy createProfileStrategy();
}
