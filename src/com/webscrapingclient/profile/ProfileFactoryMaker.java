package com.webscrapingclient.profile;

/**
 * Consente di creare la ProfileFactory corretta.
 * 
 * @author Emanuele Altomare
 */
public class ProfileFactoryMaker {

	public enum ProfileType {
		COMMERCIAL
	};

	/**
	 * Consente di creare la ProfileFactory in base al tipo di profilo che andrà
	 * gestito.
	 * 
	 * @param choice
	 *            rappresenta la scelta del tipo di ProfileFactory che dipende
	 *            dal profilo da gestire
	 * @return l'oggetto di tipo {@link ProfileAbstractFactory}
	 */
	public static ProfileAbstractFactory getProfileFactory(ProfileType type) {
		ProfileAbstractFactory paf;
		switch (type) {
		case COMMERCIAL:
			paf = new CommercialProfileFactory();
			break;
		default:
			paf = null;
			break;
		}
		/*
		 * if(type == ProfileType.ALTRO){ paf = new AltroProfileFactory(); }
		 */
		return paf;
	}
}