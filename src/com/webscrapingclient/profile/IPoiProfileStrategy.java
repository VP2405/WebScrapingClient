package com.webscrapingclient.profile;

import java.util.List;
import it.uniroma2.scorci.poi.Poi;

/**
 * Definisce l'interfaccia per la strategia utile a costruire la lista di
 * oggetti di tipo {@link Poi} ordinata in base al profilo utente.
 * 
 * @author Emanuele Altomare
 */
public interface IPoiProfileStrategy {

	/**
	 * Consente di ottenere una lista di oggetti di tipo {@link Poi} ordinata in
	 * base al profilo utente.
	 * 
	 * @param profile
	 *            l'oggetto di tipo {@link Profile} che rappresenta il profilo
	 *            utente
	 * @return una lista di id di oggetti di tipo {@link Poi} ordinata secondo il
	 *         profilo utente
	 */
	public List<Integer> getPoiSortedByProfile(Profile profile);
}
