package com.webscrapingclient.profile;

import java.io.Serializable;

/**
 * Definisce la posizione che viene usata nell'ambito delle richieste con
 * profilo.
 *
 * @author Emanuele Altomare
 */
public class UserPosition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6829826059718381594L;
	private double longitude;
	private double latitude;

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

}
