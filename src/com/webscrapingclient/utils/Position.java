/**
 * 
 */
package com.webscrapingclient.utils;

/**
 * @author Vanessa
 *
 */
public class Position
{
	private double latitude;
	private double longitude;
	
	public Position(double latitude, double longitude)
	{
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	@Override
	public String toString()
	{
		return "Position [latitude=" + latitude + ", longitude=" + longitude + "]";
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
