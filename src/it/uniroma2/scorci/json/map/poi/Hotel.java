package it.uniroma2.scorci.json.map.poi;

import java.util.*;

/**
 * Classe Hotel che estende Poi.Essa definisce le caratteristiche degli hotel
 * 
 * @author Andrea
 * 
 */
// TODO da modificare in base al JSON
public class Hotel {

	private int id;
	private String name;
	private String url;
	private String openingTimes;
	private Contact contact;
	private Position position;
	private List<Service> services;
	private List<Policy> policies;
	private HotelRating rating;
	private int stars;
	private List<Deal> deal;
	private Restaurant hotelRestaurant;

	/**
	 * Costruttore per la classe Hotel dove vengono passati tutti i parametri
	 * 
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
			int stars, List<Deal> deal, Restaurant hotel_restaurant,
			Rating rating) {
		this.stars = stars;
		this.deal = deal;
		this.hotelRestaurant = hotel_restaurant;
	}

	/**
	 * Costruttore per la classe Hotel
	 * 
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
	public Hotel() {
		super();
		this.stars = 0;
		this.deal = new ArrayList<Deal>();
	}

	/**
	 * Restituisce l'intero che corrisponde al numero di stelle dell'hotel
	 * 
	 * @return
	 */
	public int getStars() {
		return this.stars;
	}

	/**
	 * Restituisce la lista delle offerte
	 * 
	 * @return
	 */
	public List<Deal> getDeal() {
		return this.deal;
	}

	/**
	 * Restituisce il ristorante dell'Hotel
	 * 
	 * @return
	 */
	public Restaurant getHotelRestaurant() {
		return this.hotelRestaurant;
	}

	/**
	 * Imposta il numero di stelle dell'Hotel con s
	 * 
	 * @param s
	 */
	public void setStars(int s) {
		this.stars = s;
	}

	/**
	 * Aggiunge d alla lista delle offerte
	 * 
	 * @param d
	 */
	public void addDeal(Deal d) {
		this.deal.add(d);
	}

	/**
	 * Imposta r come ristorante di Hotel
	 * 
	 * @param r
	 */
	public void setHotelRestaurant(Restaurant r) {
		this.hotelRestaurant = r;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpeningTimes() {
		return openingTimes;
	}

	public void setOpeningTimes(String openingTimes) {
		this.openingTimes = openingTimes;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public HotelRating getRating() {
		return rating;
	}

	public void setRating(HotelRating rating) {
		this.rating = rating;
	}
}
