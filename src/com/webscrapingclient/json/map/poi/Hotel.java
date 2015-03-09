package com.webscrapingclient.json.map.poi;

import java.util.*;

/**
 * Classe Hotel che estende Poi.Essa definisce le caratteristiche degli hotel
 *
 * @author Andrea
 *
 */


//TODO da modificare in base al JSON
public class Hotel{

	private int stars;
	private List<Deal> deal;
	private Restaurant hotelRestaurant;
	
	/**
	 * Costruttore per la classe Hotel dove vengono passati tutti i parametri
	 * @param id
	 * @param name
	 * @param opening_times
	 * @param contact
	 * @param position
	 * @param services
	 * @param policies
	 * @param stars
	 * @param deal
	 * @param hotel_restaurant
	 * @param rating
	 */
	public Hotel(int id, String name, String opening_times, Contact contact, 
			Position position, List<Service> services, List<Policy> policies,
			int stars,List<Deal> deal,Restaurant hotel_restaurant,Rating rating) {
		this.stars = stars;
		this.deal = deal;
		this.hotelRestaurant = hotel_restaurant;
	}
	
	/**
	 * Costruttore per la classe Hotel  
	 * @param stars
	 * @param deal
	 * @param hotel_restaurant
	 */
	public Hotel(int stars, List<Deal> deal, Restaurant hotel_restaurant) {
		super();
		this.stars = stars;
		this.deal = deal;
		this.hotelRestaurant = hotel_restaurant;
	}
	
	/**
	 * Costruttore di default per la classe Hotel
	 */
	public Hotel()
	{
		super();
		this.stars = 0;
		this.deal = new ArrayList<Deal>();
	}
	
	/**
	 * Restituisce l'intero che corrisponde al numero di stelle dell'hotel
	 * @return
	 */
	public int getStars()
	{
		return this.stars;
	}
	
	/**
	 * Restituisce la lista delle offerte
	 * @return
	 */
	public List<Deal> getDeal()
	{
		return this.deal;
	}
	
	/**
	 * Restituisce il ristorante dell'Hotel
	 * @return
	 */
	public Restaurant getHotelRestaurant()
	{
		return this.hotelRestaurant;		
	}
	
	/**
	 * Imposta il numero di stelle dell'Hotel con s 
	 * @param s
	 */
	public void setStars(int s)
	{
		this.stars = s;
	}
	
	/**
	 * Aggiunge d alla lista delle offerte
	 * 
	 * @param d
	 */
	public void addDeal(Deal d)
	{
		this.deal.add(d);
	}
	
	/**
	 * Imposta r come ristorante di Hotel
	 * @param r
	 */
	public void setHotelRestaurant(Restaurant r)
	{
		this.hotelRestaurant = r;
	}





	
}
