package com.webscrapingclient.utils;

import android.R.integer;

public class CommercialProfile {



	private final static int MIN_MISER = 1;
	private final static int MAX_MISER = 4;
	
	private final static int MIN_DEMANDING = 1;
	private final static int MAX_DEMANDING = 3;

	
	private int id;
	private int isMiser;
	private String favouriteCuisine;
	private int isDemanding;
	private double isDemandingPercentage;
	private double isMiserPercentage;
	private Position lastPosition;
	
	public CommercialProfile(int id,int isMiser,String favouriteCuisine,int isDemanding, double isDemandingPercentage, double isMiserPercentage, Position position) {
		this.id = id;
		this.isMiser = isMiser;
		this.favouriteCuisine = favouriteCuisine;
		this.isDemanding = isDemanding;
		this.isDemandingPercentage = isDemandingPercentage;
		this.isMiserPercentage = isMiserPercentage;
		this.lastPosition = position;
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
	
	
	
	@Override
	public String toString()
	{
		return "Profile [id=" + id + ", isMiser=" + isMiser + ", favouriteCuisine=" + favouriteCuisine + ", isDemanding=" + isDemanding + "]";
	}

	public void setIsDemandingPercentage(double isDemandingPercentage)
	{
		this.isDemandingPercentage = isDemandingPercentage;
	}
}

