package com.webscrapingclient.profile;

import it.uniroma2.scorci.poi.Poi;

import java.io.Serializable;
import java.util.List;

/**
 * Definisce l'astrazione dell'oggetto che contiene la strategia per recuperare
 * una lista ordinata di id di oggetti di tipo {@link Poi} in base al profilo.
 * 
 * @author Emanuele Altomare
 */
public abstract class PoiProfileStrategy implements IPoiProfileStrategy,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4009591379286842386L;

	public PoiProfileStrategy() {
	}

	public abstract List<Integer> getPoiSortedByProfile(Profile profile);

}
