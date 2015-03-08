/**
 * 
 */
package com.webscrapingclient.utils;

import android.R.string;

/**
 * @author Vanessa
 *
 */
public class Profile
{

	private int status;
	private String description;
	private Map map;
	
	/**
	 * 
	 */
	public Profile(int status, String description, Map map)
	{
		// TODO Auto-generated constructor stub
		this.setStatus(status);
		this.setDescription(description);
		this.setMap(map);
	}

	@Override
	public String toString()
	{
		return "Profile [status=" + status + ", description=" + description + ", map=" + map + "]";
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

	public Map getMap()
	{
		return map;
	}

	public void setMap(Map map)
	{
		this.map = map;
	}

}
