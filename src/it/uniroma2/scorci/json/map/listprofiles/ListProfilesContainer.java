package it.uniroma2.scorci.json.map.listprofiles;

/**
 * Classe esterna relativa al Json per il profilo utente
 * 
 * @author Vanessa
 * 
 */

public class ListProfilesContainer {
	private int status;
	private String description;
	private ListProfilesMap map;
	private int request_id;

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

	public ListProfilesMap getMap() {
		return map;
	}

	public void setMap(ListProfilesMap map) {
		this.map = map;
	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

}
