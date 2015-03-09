/**
 * 
 */
package com.webscrapingclient.json.map.poi;

/**
 * @author Vanessa
 *
 *         Prima nested class del json relativo ai ristoranti
 *
 */
public class MapRestaurant
{

	private Restaurant restaurant;

	/**
	 * Costruttore
	 */
	public MapRestaurant(Restaurant restaurant)
	{
		super();
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant()
	{
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant)
	{
		this.restaurant = restaurant;
	}

}
