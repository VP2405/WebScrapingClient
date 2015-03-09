package com.webscrapingclient.poi.jsonmanager;

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
import com.webscrapingclient.json.map.poi.PoiRestaurants;

public class PoiJsonParser
{
	
	private PoiRestaurants restaurant;

	public PoiJsonParser (int id) throws IllegalStateException, IOException{
		
		String urlString = "http://10.220.176.242:5555/scorci/poi/"+id;

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
