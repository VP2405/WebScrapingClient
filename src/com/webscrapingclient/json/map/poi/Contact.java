package com.webscrapingclient.json.map.poi;

import java.util.*;

/**
 * Classe relativa ai contatti del Poi
 *
 */
public class Contact {

	private List<String> telephoneNumber;
	private String website;
	private String email;


	public List<String> getTelephoneNumber() {
		return this.telephoneNumber;
	}
	
	/**
	 * Restituisce il sito web del Poi
	 * 
	 * @return
	 */

	public String getWebsite() {
		return this.website;
	}

	/**
	 * Restituisce la mail del Poi
	 * 
	 * @return
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Aggiunge tn alla lista dei numeri di telefono dei contatti
	 * 
	 * @param tn
	 */
	public void addTelephoneNumber(String tn) {
		this.telephoneNumber.add(tn);
	}
	
	/**
	 * Imposta website come sito web di Contact
	 * 
	 * @param website
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	/**
	 * Imposta email come mail di Contact
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	
		
}
