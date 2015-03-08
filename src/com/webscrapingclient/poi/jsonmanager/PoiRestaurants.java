/**
 * 
 */
package com.webscrapingclient.poi.jsonmanager;

import android.R.integer;

/**
 * @author Vanessa
 *
 */
public class PoiRestaurants
{


	private int status;
	private String description;
	private MapRestaurant map;

	
	
	
	public PoiRestaurants(int status, String description)
	{
		super();
		this.status = status;
		this.description = description;
	}
	
	
	
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}



	public MapRestaurant getMap()
	{
		return map;
	}



	public void setMap(MapRestaurant map)
	{
		this.map = map;
	}
}
