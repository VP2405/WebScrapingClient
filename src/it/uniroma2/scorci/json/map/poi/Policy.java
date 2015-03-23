package it.uniroma2.scorci.json.map.poi;

/**
 * Classe Policy che rappresente le politiche che un Poi pu� attuare,quali ad
 * esempio la possibilit� di fumare, la possibilit� di fa entrare gli
 * animali etc.
 * 
 * @author Andrea
 * 
 */
public class Policy {

	private String name;
	private String description;

	/**
	 * Costruttore di default della classe Policy
	 */
	public Policy() {

	}

	/**
	 * Costruttore per la classe Policy
	 * 
	 * @param name
	 * @param description
	 */
	public Policy(String name, String description) {

		this.name = name;
		this.description = description;
	}

	/**
	 * Restituisce il nome della policy
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Imposta il nome della policy
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Restituisce la descrizione della policy
	 * 
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Imposta la descrizione della policy
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Metodo toString per la classe Policy
	 */
	public String toString() {
		if (this.description != null) {
			String desc = String.format("Name: %s, Description: %s\n",
					this.name, this.description);

			return desc;
		} else {
			String desc = String.format("Name: %s\n", this.name);

			return desc;
		}

	}

	/**
	 * Metodo equals per la classe Policy
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.getClass() != obj.getClass())
			return false;

		Policy p = (Policy) obj;

		return this.description.equals(p.getDescription())
				&& this.name.equals(p.getName());
	}

}
