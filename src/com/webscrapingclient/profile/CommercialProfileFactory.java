package com.webscrapingclient.profile;

/**
 * Rappresenta l'oggetto che ha la responsabilità della creazione del profilo
 * commerciale e della strategia relativa.
 * 
 * @author Emanuele Altomare
 */
public class CommercialProfileFactory extends ProfileAbstractFactory {

	public CommercialProfileFactory() {
		super();
	}

	@Override
	public Profile createProfile() {
		throw new UnsupportedOperationException("Not supported yet.");
		/*
		 * Dopo aver recuperato i dati del profilo commerciale dall'esterno,
		 * seguirà l'operazione di creazione della strategia tramite il metodo
		 * sotto, poi la creazione del profilo con injection della strategia
		 * appena creata.
		 */

	}

	@Override
	public PoiProfileStrategy createProfileStrategy() {
		return new PoiCommercialProfileStrategy();
	}

}
