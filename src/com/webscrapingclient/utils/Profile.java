/**
 * 
 */
package com.webscrapingclient.utils;

/**
 * @author Vanessa
 *
 */
public class Profile
{

	private int status;
	private String description;
	private Map map;
	
	

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
