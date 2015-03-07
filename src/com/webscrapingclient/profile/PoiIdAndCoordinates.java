package com.webscrapingclient.profile;

import it.uniroma2.scorci.util.GoogleUtils;

/**
 * E' una classe di servizio per conservare solo i dati necessari
 * all'elaborazione per il servizio di ordinamento dei POI in base al profilo.
 * 
 * @author Emanuele Altomare
 */
public class PoiIdAndCoordinates implements Comparable<PoiIdAndCoordinates> {

	private int id;
	private double longitude;
	private double latitude;
	private Profile userProfile;
	private double distanceBetweenThisAndUser;

	public PoiIdAndCoordinates() {
		id = 0;
		longitude = 0.0;
		latitude = 0.0;
		userProfile = null;
		distanceBetweenThisAndUser = 0.0;
	}

	public PoiIdAndCoordinates(int id, double longitude, double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.userProfile = null;
		distanceBetweenThisAndUser = -1.0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Profile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
	}

	/**
	 * Restituisce la distanza tra l'utente e questo punto.
	 * 
	 * @return
	 * @throws Exception
	 */
	public double getDistanceBetweenThisAndUser() throws Exception {
		if (userProfile == null) {
			throw new Exception("The User Profile is not set!");
		}
		if (distanceBetweenThisAndUser == -1.0) {
			this.distanceBetweenThisAndUser = GoogleUtils
					.getDistanceBetweenPoints(this.latitude, this.longitude,
							userProfile.getLastPosition().getLatitude(),
							userProfile.getLastPosition().getLongitude());
		}
		return distanceBetweenThisAndUser;
	}

	/**
	 * Consente di dire se questo punto è più distante dall'utente rispetto ad
	 * un altro.
	 * 
	 */
	@Override
	public int compareTo(PoiIdAndCoordinates o) {
		try {
			if (this.getDistanceBetweenThisAndUser() > o
					.getDistanceBetweenThisAndUser()) {
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (this.getDistanceBetweenThisAndUser() == o
					.getDistanceBetweenThisAndUser()) {
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}