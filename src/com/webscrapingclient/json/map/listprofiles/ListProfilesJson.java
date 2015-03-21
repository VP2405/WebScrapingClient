package com.webscrapingclient.json.map.listprofiles;

/**
 * Classe esterna relativa al Json per il profilo utente
 * 
 * @author Vanessa
 * 
 */

public class ListProfilesJson {
	private int status;
	private String description;
	private MapListProfiles map;

	// metodi getter e setter
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MapListProfiles getMap() {
		return map;
	}

	public void setMap(MapListProfiles map) {
		this.map = map;
	}

}
