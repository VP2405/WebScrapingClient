/**
 * 
 */
package com.webscrapingclient.json.map.profile;

/**
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
