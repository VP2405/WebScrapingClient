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

import com.google.gson.Gson;
import com.webscrapingclient.json.map.listprofiles.ListProfilesJson;
import com.webscrapingclient.json.map.listprofiles.MapListProfiles;
import com.webscrapingclient.json.map.orderedlist.OrderedListJson;
import com.webscrapingclient.json.map.orderedlist.OrderedListMap;
import com.webscrapingclient.json.map.poi.PoiRestaurants;
import com.webscrapingclient.json.map.profile.Profile;

public class CallRestService {

	private static final String SERVER_ADDRESS = "http://10.200.253.81:8080/scorci/";
	private static final String PROFILE = "profile/";
	private static final String POI ="poi/";
	
	private PoiRestaurants restaurant;
	
	public CallRestService(){
		
	}
	
	/**
	 * Richiama il servizio rest utilizzando un client Apache Http per ottenere
	 * i profili in formato JSON, e parsarli tramite Gson in modo da creare un
	 * oggetto di tipo Profile, da cui poter successivamente recuperare le
	 * informazioni per la loro visualizzazione
	 * 
	 * @param id
	 *            id del profilo scelto
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String callRestServiceForProfile(int id) throws ClientProtocolException, IOException
	{

		String urlString = SERVER_ADDRESS + PROFILE + id;
		System.out.println("chiamata a: " + urlString);

		// necessario per la connessione da API 9 Android
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// crea richiesta http per accedere al servizio REST
		HttpClient client = new DefaultHttpClient();

		client.getParams().getParameter(ConnRoutePNames.DEFAULT_PROXY);

		HttpGet request = new HttpGet(urlString.trim());
		HttpResponse response = client.execute(request);

		// recupera il json
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder sbBuilder = new StringBuilder();

		String line = "";
		while ((line = rd.readLine()) != null)
		{
			// System.out.println("JSON:"+line);
			sbBuilder.append(line);
		}

		// deserializzazione del Json ricevuto in un oggetto di tipo Profile
		Gson gson = new Gson();
		Profile profile = gson.fromJson(sbBuilder.toString(), Profile.class);

		return profile.getMap().getCommercialProfile().toString();

	}
	
	public OrderedListMap callRestServiceForList(Integer id) throws ClientProtocolException, IOException
	{
		String urlString = SERVER_ADDRESS + POI + PROFILE+ id;
		System.out.println("chiamata a: " + urlString);

		// necessario per la connessione da API 9 Android
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// crea richiesta http per accedere al servizio REST
		HttpClient client = new DefaultHttpClient();

		client.getParams().getParameter(ConnRoutePNames.DEFAULT_PROXY);

		HttpGet request = new HttpGet(urlString.trim());
		HttpResponse response = client.execute(request);

		// recupera il json
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder sbBuilder = new StringBuilder();

		String line = "";
		while ((line = rd.readLine()) != null)
		{
			// System.out.println("JSON:"+line);
			sbBuilder.append(line);
		}

		// deserializzazione del Json ricevuto in un oggetto di tipo Profile
		Gson gson = new Gson();
		OrderedListJson orderedList = gson.fromJson(sbBuilder.toString(), OrderedListJson.class);
		
		return orderedList.getMap();
	}
	
	public MapListProfiles callRestServiceForListProfiles() throws ClientProtocolException, IOException
	{
		
		String urlString = SERVER_ADDRESS + PROFILE +"all";
		System.out.println("chiamata a: "+urlString);
		//necessario per la connessione
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		//crea richiesta http per accedere al servizio REST
		HttpClient client = new DefaultHttpClient();

		client.getParams().getParameter(ConnRoutePNames.DEFAULT_PROXY);

		HttpGet request = new HttpGet(urlString.trim());
		HttpResponse response = client.execute(request);

		//recupera il json
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder sbBuilder = new StringBuilder();
		//System.out.println("I'm Here!!!");

		String line = "";
		while ((line = rd.readLine()) != null)
		{
			System.out.println("JSON:"+line);
			sbBuilder.append(line);
		}

		// deserializzazione del Json ricevuto in un oggetto di tipo Profile
		Gson gson = new Gson();
		ListProfilesJson list = gson.fromJson(sbBuilder.toString(), ListProfilesJson.class);

		System.out.println(list.getMap().getAllProfilesIds().size());
		return list.getMap() ;

	}
	
	public void callRestServiceForPoi (int id) throws IllegalStateException, IOException{
		
		String urlString = SERVER_ADDRESS + POI + id;
		System.out.println("chiamata a:"+urlString);

		//necessario per la connessione
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		//crea richiesta http per accedere al servizio REST
		HttpClient client = new DefaultHttpClient();

		client.getParams().getParameter(ConnRoutePNames.DEFAULT_PROXY);

		HttpGet request = new HttpGet(urlString.trim());
		HttpResponse response = null;
		try
		{
			response = client.execute(request);
		} catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//recupera il json
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder sbBuilder = new StringBuilder();
		

		String line = "";
		while ((line = rd.readLine()) != null)
		{
			System.out.println("JSON:"+line);
			sbBuilder.append(line);
		}

		// deserializzazione del Json ricevuto in un oggetto di tipo Restaurant
		Gson gson = new Gson();
		setRestaurant(gson.fromJson(sbBuilder.toString(), PoiRestaurants.class));
		
		//System.out.println(pr.getMap().getRestaurant().getName());

	}

	public PoiRestaurants getRestaurant()
	{
		return restaurant;
	}

	public void setRestaurant(PoiRestaurants restaurant)
	{
		this.restaurant = restaurant;
	}



}
