package com.webscrapingclient.json.map.poi;

import android.R.integer;

/**
 * Classe esterna relativa al Json per i poi di tipo Restaurant
 * 
 * @author Vanessa
 * 
 */
public class PoiRestaurants {
	private int status;
	private String description;
	private MapRestaurant map;

	/**
	 * Costruttore per la classe PoiRestaurants
	 * 
	 * @param status
	 * @param description
	 */
	public PoiRestaurants(int status, String description) {
		super();
		this.status = status;
		this.description = description;
	}

	// metodi getter e setter
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MapRestaurant getMap() {
		return map;
	}

	public void setMap(MapRestaurant map) {
		this.map = map;
	}
}
