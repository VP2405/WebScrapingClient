/**
 * 
 */
package it.uniroma2.scorci.json.map.commercialprofile;

/**
 * Classe che gestisce la posizione dell'utente
 * @author Vanessa
 *
 */
public class Position
{
	public Position(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	private double latitude;
	private double longitude;
	

	@Override
	public String toString()
	{
		return "latitude: " + latitude + ", longitude:" + longitude;
	}

	//metodi getter e setter
	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
}
