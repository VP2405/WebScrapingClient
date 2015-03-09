package com.webscrapingclient.json.map.listprofiles;

/**
 * @author Vanessa 
 * Classe esterna relativa al Json per il profilo utente
 */

public class ListProfilesJson
{
	private int status;
	private String description;
	private MapListProfiles map;

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

	public MapListProfiles getMap()
	{
		return map;
	}

	public void setMap(MapListProfiles map)
	{
		this.map = map;
	}

}
