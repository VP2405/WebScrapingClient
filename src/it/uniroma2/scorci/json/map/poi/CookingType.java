package it.uniroma2.scorci.json.map.poi;

/**
 * Classe relativa al tipo di cucina
 * 
 * @author Vanessa
 * 
 */
public class CookingType {
	private String name;

	/**
	 * Costruttore per la classe CookingType che riceve il nome del tipo di
	 * cucina
	 * 
	 * @param name
	 */
	public CookingType(String name) {
		super();
		this.name = name;
	}

	// Metodi getter e setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "\n";
	}

}
