package com.webscrapingclient.restservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.webscrapingclient.json.map.listprofiles.ListProfilesJson;
import com.webscrapingclient.json.map.listprofiles.MapListProfiles;
import com.webscrapingclient.json.map.orderedlist.OrderedListJson;
import com.webscrapingclient.json.map.orderedlist.OrderedListMap;
import com.webscrapingclient.json.map.poi.PoiRestaurants;
import com.webscrapingclient.json.map.profile.Profile;

/**
 * Classe che utilizza i servizi REST forniti dal server scorci,utilizzando una connessione HTTP
 * @author Andrea
 *
 */
public class CallRestService {

	//Indirizzo del server
	private static final String SERVER_ADDRESS = "http://192.168.1.3:8080/scorci/";
	
	private static final String PROFILE = "profile/";
	private static final String POI = "poi/";

	private PoiRestaurants restaurant;
	private Gson gson;
	private HttpClient client;

	/**
	 *Costruttore di default per la classe CallRestService 
	 */
	public CallRestService() {
		this.gson = new Gson();
		this.client = new DefaultHttpClient();

	}

	/**
	 * Metodo che serve ad inizializzare la connessione Http
	 */
	public void initializeConnection() {
		// necessario per la connessione da API 9 Android
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// crea richiesta http per accedere al servizio REST
		client.getParams().getParameter(ConnRoutePNames.DEFAULT_PROXY);
	}

	/**
	 * Metodo che prende l'url del servizio REST specifico e restituisce il JSON richiesto
	 * @param urlString
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public StringBuilder retrieveJson(String urlString)
			throws ClientProtocolException, IOException {
		
		//invia la richiesta HTTP utilizzando un url
		HttpGet request = new HttpGet(urlString.trim());

		//recupera la risposta fornita dal server
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// recupera il json salvandolo sotto forma di StringBuilder
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		StringBuilder sbBuilder = new StringBuilder();

		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println("JSON:" + line);
			sbBuilder.append(line);
		}

		return sbBuilder;
	}

	/**
	 *Richiama il servizio REST fornito da scorci per recuperare il profilo identificato da id
	 * @param id
	 *            id del profilo scelto
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String callRestServiceForProfile(int id)
			throws ClientProtocolException, IOException {

		String urlString = SERVER_ADDRESS + PROFILE + id;
		Log.v("CallRestService", "Call to: " + urlString);

		this.initializeConnection();

		StringBuilder sbBuilder = this.retrieveJson(urlString);

		// deserializzazione del Json ricevuto in un oggetto di tipo Profile
		Profile profile = gson.fromJson(sbBuilder.toString(), Profile.class);

		return profile.getMap().getCommercialProfile().toString();

	}

	/**
	 * Richiama il servizio REST fornito da scorci per recuperare la lista degli id
	 * dei poi che rispecchiano il profilo
	 * @param id
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public OrderedListMap callRestServiceForList(Integer id)
			throws ClientProtocolException, IOException {
		String urlString = SERVER_ADDRESS + POI + PROFILE + id;
		Log.v("CallRestService", "Call to: " + urlString);

		this.initializeConnection();

		StringBuilder sbBuilder = this.retrieveJson(urlString);

		// deserializzazione del Json ricevuto in una lista di id ordinata
		OrderedListJson orderedList = gson.fromJson(sbBuilder.toString(),
				OrderedListJson.class);

		return orderedList.getMap();
	}
	
	/**
	 * Richiama il servizio REST fornito da scorci per recuperare la lista degli id
	 * che identificano i profili
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public MapListProfiles callRestServiceForListProfiles()
			throws ClientProtocolException, IOException {

		String urlString = SERVER_ADDRESS + PROFILE + "all";
		Log.v("CallRestService", "Call to: " + urlString);

		this.initializeConnection();

		StringBuilder sbBuilder = this.retrieveJson(urlString);

		// deserializzazione del Json ricevuto in una lista di id dei profili
		ListProfilesJson list = gson.fromJson(sbBuilder.toString(),
				ListProfilesJson.class);

		return list.getMap();

	}

	/**
	 * Richiama il servizio REST fornito da scorci per recuperare un singolo poi
	 * dato il suo id
	 * @param id
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void callRestServiceForPoi(int id) throws IllegalStateException,
			IOException {

		String urlString = SERVER_ADDRESS + POI + id;
		Log.v("CallRestService", "Call to: " + urlString);

		this.initializeConnection();

		StringBuilder sbBuilder = this.retrieveJson(urlString);

		// deserializzazione del Json ricevuto in un oggetto di tipo Restaurant
		setRestaurant(gson.fromJson(sbBuilder.toString(), PoiRestaurants.class));

	}

	/**
	 * Restituisce il ristorante
	 * @return
	 */
	public PoiRestaurants getRestaurant() {
		return restaurant;
	}

	/**
	 * Imposta il ristorante
	 * @param restaurant
	 */
	public void setRestaurant(PoiRestaurants restaurant) {
		this.restaurant = restaurant;
	}

}
