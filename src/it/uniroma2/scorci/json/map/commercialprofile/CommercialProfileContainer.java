package it.uniroma2.scorci.json.map.commercialprofile;

/**
 * Classe esterna per il parsing del Json relativo al profilo utente
 * 
 * @author Vanessa
 * 
 */
public class CommercialProfileContainer {

	private int status;
	private String description;
	private CommercialProfileMap map;

	// metodi getter e setter riferiti ai campi privati della classe Profile
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

	public CommercialProfileMap getMap() {
		return map;
	}

	public void setMap(CommercialProfileMap map) {
		this.map = map;
	}

}
