package it.uniroma2.scorci.json.map.poi;

/**
 * Prima nested class del json relativo ai ristoranti
 * 
 * @author Vanessa
 * 
 */
public class PoiMap {

	private Restaurant restaurant;
	private Hotel hotel;
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * Costruttore che riceve il tipo Restaurant
	 */
	public PoiMap(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	public PoiMap(Hotel hotel) {
		super();
		this.hotel = hotel;
	}
	// metodi getter e setter
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
