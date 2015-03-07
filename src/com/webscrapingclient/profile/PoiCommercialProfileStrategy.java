package com.webscrapingclient.profile;

import it.uniroma2.scorci.poi.Poi;
import it.uniroma2.scorci.service.ServiceFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Definisce l'oggetto che contiene la strategia per recuperare una lista
 * ordinata di oggetti di tipo {@link Poi} in base al profilo commerciale.
 * 
 * @author Emanuele Altomare
 */
@Component
@Scope(value = "prototype")
public class PoiCommercialProfileStrategy extends PoiProfileStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2646554537879633282L;
	@Autowired
	@Transient
	private ServiceFacade service;

	public PoiCommercialProfileStrategy() {
		super();
	}

	@Override
	public List<Integer> getPoiSortedByProfile(Profile profile) {
		List<PoiIdAndCoordinates> restaurantsIdAndCoord;
		List<Integer> result;
		CommercialProfile prof = (CommercialProfile) profile;
		// ottengo i valori normalizzati per il filtraggio dei poi
		double maxAveragePrice = getMaxAveragePrice(prof.getIsMiserPercentage());
		double minRatingValue = getMinRatingValue(prof
				.getIsDemandingPercentage());
		// eseguo la query al DB attraverso il service
		restaurantsIdAndCoord = service
				.findRestaurantIdAndCoordinatesByProfileProperties(
						prof.getFavouriteCuisine(), maxAveragePrice,
						minRatingValue);
		// setto il profilo negli oggetti di utilità
		setUserProfile(restaurantsIdAndCoord, prof);
		// ordino gli oggetti dal più vicino al più lontano
		Collections.sort(restaurantsIdAndCoord);
		// prendo solo gli id dei Poi.
		result = getIds(restaurantsIdAndCoord);
		return result;

	}

	/*
	 * Setta il profilo negli oggetti di utilità {@link PoiIdAndCoordinates}.
	 */
	private void setUserProfile(List<PoiIdAndCoordinates> poisIdAndCoord,
			CommercialProfile profile) {
		for (PoiIdAndCoordinates poiIdAndCoord : poisIdAndCoord) {
			poiIdAndCoord.setUserProfile(profile);
		}
	}

	private List<Integer> getIds(List<PoiIdAndCoordinates> restaurantsIdAndCoord) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (PoiIdAndCoordinates elem : restaurantsIdAndCoord) {
			ids.add(elem.getId());
		}
		return ids;
	}

	/*
	 * Consente di trovare il minimo valore di rating accettabile per l'utente
	 * profilato.
	 */
	private double getMinRatingValue(double percentage) {
		double minStoredRatingValue = service.getRestaurantMinRatingValue();
		double maxStoredRatingValue = service.getRestaurantMaxRatingValue();
		double gap = maxStoredRatingValue - minStoredRatingValue;
		double addToMinStoredRatingValue = (gap / 100) * percentage;
		return minStoredRatingValue + addToMinStoredRatingValue;
	}

	/*
	 * Consente di trovare il massimo prezzo medio che l'utente profilato è
	 * disposto a spendere. (Più è alta la percentuale in input meno l'utente è
	 * dsposto a spendere).
	 */
	private double getMaxAveragePrice(double percentage) {
		double minStoredAveragePrice = service.getRestaurantMinAveragePrice();
		double maxStoredAveragePrice = service.getRestaurantMaxAveragePrice();
		double gap = maxStoredAveragePrice - minStoredAveragePrice;
		double addToMinStoredAveragePrice = (gap / 100) * (100 - percentage);
		return minStoredAveragePrice + addToMinStoredAveragePrice;
	}
}
