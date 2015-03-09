/**
 * 
 */
package com.webscrapingclient.json.map.profile;

/**
 * @author Vanessa Classe esterna per il parsing del Json relativo al profilo
 *         utente
 *
 */
public class Profile
{

	private int status;
	private String description;
	private Map map;

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
