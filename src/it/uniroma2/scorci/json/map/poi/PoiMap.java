package it.uniroma2.scorci.json.map.poi;

/**
 * Prima nested class del json relativo ai ristoranti
 * 
 * @author Vanessa
 * 
 */
public class PoiMap {

	private PoiRestaurant restaurant;

	/**
	 * Costruttore che riceve il tipo Restaurant
	 */
	public PoiMap(PoiRestaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	// metodi getter e setter
	public PoiRestaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(PoiRestaurant restaurant) {
		this.restaurant = restaurant;
	}

}
