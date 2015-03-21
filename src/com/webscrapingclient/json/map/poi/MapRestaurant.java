package com.webscrapingclient.json.map.poi;

/**
 * Prima nested class del json relativo ai ristoranti
 * 
 * @author Vanessa
 * 
 */
public class MapRestaurant {

	private Restaurant restaurant;

	/**
	 * Costruttore che riceve il tipo Restaurant
	 */
	public MapRestaurant(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	// metodi getter e setter
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
