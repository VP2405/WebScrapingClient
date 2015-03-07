package com.webscrapingclient.profile;

import java.util.List;

/**
 * Definisce l'astrazione del profilo utente.
 * 
 * @author Emanuele Altomare
 */

public abstract class Profile {

	private final static int MIN_MISER = 1;
	private final static int MAX_MISER = 4;

	private int id;
	private final PoiProfileStrategy strategy;
	private final UserPosition lastPosition;
	private int isMiser;

	public Profile(PoiProfileStrategy strategy, UserPosition lastPosition) {
		this.strategy = strategy;
		this.lastPosition = lastPosition;
		this.isMiser = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsMiser() {
		return this.isMiser;
	}

	public void setIsMiser(int isMiser) throws Exception {
		if (isMiser >= MIN_MISER && isMiser <= MAX_MISER)
			this.isMiser = isMiser;
		else
			throw new Exception(String.format(
					"The isMiser value must be between %d and %d", MIN_MISER,
					MAX_MISER));
	}

	/**
	 * Restituisce la percentuale di taccagneria dell'utente profilato.
	 * 
	 * @return
	 */
	public double getIsMiserPercentage() {
		return (this.isMiser * 100) / MAX_MISER;
	}

	public PoiProfileStrategy getStrategy() {
		return strategy;
	}

	public UserPosition getLastPosition() {
		return lastPosition;
	}

	public List<Integer> getIdsPoiSorted() {
		return this.strategy.getPoiSortedByProfile(this);
	}
}
