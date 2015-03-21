package com.webscrapingclient.json.map.poi;

/**
 * Classe Service relativa ai servizi offerti da un Poi
 * 
 * @author Andrea
 * 
 */
public class Service {

	private String name;
	private String description;

	/**
	 * Costruttore per la classe Service
	 * 
	 * @param name
	 */
	public Service(String name) {
		this.name = name;
	}

	/**
	 * Costruttore della classe Service
	 * 
	 * @param name
	 * @param description
	 */
	public Service(String name, String description) {

		this.name = name;
		this.description = description;
	}

	/**
	 * Restituisce il tipo di servizio offerto
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Imposta il tipo di servizio offerto
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Restituisce la descrizione del servizio offerto
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Imposta la descrizione del servizio offerto
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Metodo toString per la classe Service
	 */
	public String toString() {
		if (this.description != null) {
			String desc = this.name + this.description + "\n";
			return desc;
		} else {
			String desc = this.name + "\n";
			return desc;
		}
	}

	/**
	 * Metodo equals per la classe Service
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.getClass() != obj.getClass())
			return false;

		Service s = (Service) obj;

		return this.description.equals(s.getDescription())
				&& this.name.equals(s.getName());
	}

}
