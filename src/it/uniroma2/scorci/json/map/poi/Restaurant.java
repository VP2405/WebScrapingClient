package it.uniroma2.scorci.json.map.poi;

import java.util.List;

/**
 * Classe relativa ad un poi di tipo Ristorante e che lo identifica con le sue
 * specifiche caratteristiche
 * 
 * @author Andrea
 * 
 */

public class Restaurant {

	private int id;
	private String name;
	private String url;
	private String openingTimes;
	private Contact contact;
	private Position position;
	private List<Service> services;
	private List<Policy> policies;
	private Rating rating;
	private String averagePrice;
	private List<CookingType> cookingType;
	private Hotel linkedPoi;
	private String genericRequest;

	public Restaurant(int id, String name, String opening_times,
			Contact contact, Position position, List<Service> services,
			List<Policy> policies, Rating rating, int id2, String name2,
			String url, String openingTimes, Contact contact2,
			Position position2, List<Service> services2,
			List<Policy> policies2, Rating rating2, String averagePrice,
			List<CookingType> cookingType, Hotel linkedPoi,
			String genericRequest) {

		id = id2;
		name = name2;
		this.url = url;
		this.openingTimes = openingTimes;
		contact = contact2;
		position = position2;
		services = services2;
		policies = policies2;
		rating = rating2;
		this.averagePrice = averagePrice;
		this.cookingType = cookingType;
		this.linkedPoi = linkedPoi;
		this.genericRequest = genericRequest;
	}

	/**
	 * Restituisce il prezzo medio
	 * 
	 * @return
	 */
	public String getAveragePrice() {
		return this.averagePrice;
	}

	/**
	 * Restituisce la lista dei tipi di cucina
	 * 
	 * @return
	 */
	public List<CookingType> getCookingType() {
		return this.cookingType;
	}

	/**
	 * Restituisce l'hotel che contiene il ristorante
	 * 
	 * @return
	 */
	public Hotel getRestaurantHotel() {// gestire quando � null
		return this.linkedPoi;
	}

	/**
	 * Imposta il prezzo medio
	 * 
	 * @param ap
	 */
	public void setAveragePrice(String ap) {
		this.averagePrice = ap;
	}

	/**
	 * Imposta h come hotel relativo al ristorante
	 * 
	 * @param h
	 */
	public void setRestaurantHotel(Hotel h) {
		this.linkedPoi = h;
	}

	// metodi getter e setter
	public String getGenericRequest() {
		return genericRequest;
	}

	public void setGenericRequest(String genericRequest) {
		this.genericRequest = genericRequest;
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

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Hotel getLinkedPoi() {
		return linkedPoi;
	}

	public void setLinkedPoi(Hotel linkedPoi) {
		this.linkedPoi = linkedPoi;
	}

	public void setCookingType(List<CookingType> cookingType) {
		this.cookingType = cookingType;
	}

}
