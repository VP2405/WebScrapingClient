package com.webscrapingclient.json.map.poi;

/**
 * @author Vanessa
 *
 */
public class CookingType
{
	private String name;
	
	

	public CookingType(String name)
	{
		super();
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name +"\n";
	}

}
