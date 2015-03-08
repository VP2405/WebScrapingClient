package poi;

/**
 * Classe Position che rappresenta la posizione del Poi. Essa è formata dalle coordinate geografiche,dall'indirizzo,
 * dal codice postale e dalla città
 * @author Andrea
 *
 */
public class Position {

	private double latitude;
	private double longitude;
	private String address;
	private String zipCode;
	private String city;

	/**
	 * Costruttore di default per la classe Position
	 */
	public Position() {
		this.latitude = 0.0;
		this.longitude = 0.0;
	}

	/**
	 * Costruttore per la classe Position
	 * @param latitude
	 * @param longitude
	 * @param address
	 * @param zip_code
	 * @param city
	 */
	public Position(double latitude, double longitude, String address,
			String zip_code, String city) {

		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.zipCode = zip_code;
		this.city = city;
	}
	
	/**
	 * Restituisce la latitudine
	 * @return
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * Imposta la latitudine
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Restituisce la longituidine
	 * @return
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * Imposta la longituidne
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Restituisce l'indirizzo
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Imposta l'indirizzo
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Restituisce il codice postale
	 * @return
	 */
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * Imposta il codice postale
	 * @param zip_code
	 */
	public void setZipCode(String zip_code) {
		this.zipCode = zip_code;
	}
	
	/**
	 * Restituisce la città
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Imposta la città
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Metodo toString per la classe Position
	 */
	@Override
	public String toString() {
		return String.format("[%s - %s %s %s (%f,%f)]", getClass()
				.getSimpleName(), address, city, zipCode, latitude, longitude);
	}

	/**
	 * Metodo equals per la classe Position
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.getClass() != obj.getClass())
			return false;

		Position pos = (Position) obj;

		return this.address.equals(pos.getAddress())
				&& this.city.equals(pos.getCity())
				&& this.zipCode.equals(pos.getZipCode());
	}

}
