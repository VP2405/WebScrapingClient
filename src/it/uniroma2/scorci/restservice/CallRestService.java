package it.uniroma2.scorci.restservice;

import it.uniroma2.scorci.json.map.commercialprofile.CommercialProfileContainer;
import it.uniroma2.scorci.json.map.listprofiles.ListProfilesContainer;
import it.uniroma2.scorci.json.map.listprofiles.ListProfilesMap;
import it.uniroma2.scorci.json.map.orderedlist.OrderedListContainer;
import it.uniroma2.scorci.json.map.orderedlist.OrderedListMap;
import it.uniroma2.scorci.json.map.poi.PoiContainer;

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

/**
 * Classe per l'interfacciamento con i servizi RESTful forniti dal sistema. Per
 * l'invio della richiesta al server viene utilizzato l'Apache Framework per
 * Android per la gestione delle connessioni HTTP. In particolare si fa utilizzo
 * del metodo GET del protocollo. Per il parsing della risposta, fornita in
 * formato JSON, si ricorre all'utilizzo della libreria open-source di Google
 * Gson.
 * 
 * @author Scorci d'Italia - Hotels and Restaurants Group
 *
 */
public class CallRestService
{

	// Indirizzo del server
	private static final String SERVER_ADDRESS = "http://192.168.1.102:5555/scorci/";

	// specifica delle API
	private static final String PROFILE = "profile/";
	private static final String POI = "poi/";
	
	private static final String TAG = "CallRestService";

	private PoiContainer poiContainer;
	private Gson gson;
	private HttpClient client;

	/**
	 * Costruttore di default per la classe CallRestService
	 */
	public CallRestService()
	{
		/**
		 * istanzia un nuovo oggetto Gson per il parsing e prepara il client per
		 * la connessione
		 */
		this.gson = new Gson();
		this.client = new DefaultHttpClient();

	}

	/**
	 * Metodo che serve ad inizializzare la connessione Http
	 */
	public void initializeConnection()
	{
		// necessario per la connessione da API 9 Android
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// crea richiesta http per accedere al servizio REST
		client.getParams().getParameter(ConnRoutePNames.DEFAULT_PROXY);
	}

	/**
	 * Crea un oggetto di tipo StringBuilder per l'incapsulamento del JSON
	 * ricevuto in risposta alla richiesta di connessione all'indirizzo
	 * specificato dal parametro urlString
	 * 
	 * @param urlString
	 *            contiene l'url da cui è possibile interagire con il servizio
	 *            RESTful opportuno
	 * @return un oggetto di tipo StringBuilder contenente il JSON inviato in
	 *         risposta dal server
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public StringBuilder retrieveJson(String urlString) throws ClientProtocolException, IOException
	{

		// instanzia la richiesta HTTP-GET specificando l'url da contattare
		HttpGet request = new HttpGet(urlString.trim());

		// invio richiesta
		HttpResponse response = null;
		try
		{
			response = client.execute(request);
		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		// recupera il json in risposta, salvandolo in un oggetto di tipo
		// StringBuilder
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder sbBuilder = new StringBuilder();

		String line = "";
		while ((line = rd.readLine()) != null)
		{
			System.out.println("JSON:" + line);
			sbBuilder.append(line);
		}

		return sbBuilder;
	}

	/**
	 * Richiama il servizio REST fornito da scorci per recuperare il profilo
	 * identificato da id
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
		Log.v(TAG, "Call to: " + urlString);

		this.initializeConnection();

		StringBuilder sbBuilder = this.retrieveJson(urlString);

		// deserializzazione del Json ricevuto in un oggetto di tipo Profile
		CommercialProfileContainer profile = gson.fromJson(sbBuilder.toString(), CommercialProfileContainer.class);

		//TODO forse è meglio ritornare un oggetto CommercialProfile più che una stringa
		return profile.getMap().getCommercialProfile().toString();

	}

	/**
	 * Richiama il servizio REST fornito da scorci per recuperare la lista degli
	 * id dei poi che rispecchiano il profilo
	 * 
	 * @param id	id del profilo per cui si intende recuperare la lista ordinata
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public OrderedListMap callRestServiceForList(Integer id) throws ClientProtocolException, IOException
	{
		String urlString = SERVER_ADDRESS + POI + PROFILE + id;
		Log.v(TAG, "Call to: " + urlString);

		this.initializeConnection();

		StringBuilder sbBuilder = this.retrieveJson(urlString);

		// deserializzazione del Json ricevuto in una lista di id ordinata
		OrderedListContainer orderedList = gson.fromJson(sbBuilder.toString(), OrderedListContainer.class);

		return orderedList.getMap();
	}

	/**
	 * Richiama il servizio REST fornito da scorci per recuperare la lista degli
	 * id che identificano i profili
	 * 
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public ListProfilesMap callRestServiceForListProfiles() throws ClientProtocolException, IOException
	{

		String urlString = SERVER_ADDRESS + PROFILE + "all";
		Log.v(TAG, "Call to: " + urlString);

		this.initializeConnection();

		StringBuilder sbBuilder = this.retrieveJson(urlString);

		// deserializzazione del Json ricevuto in una lista di id dei profili
		ListProfilesContainer list = gson.fromJson(sbBuilder.toString(), ListProfilesContainer.class);

		return list.getMap();

	}

	/**
	 * Richiama il servizio REST fornito da scorci per recuperare un singolo poi
	 * dato il suo id
	 * 
	 * @param id
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void callRestServiceForPoi(int id) throws IllegalStateException, IOException
	{

		String urlString = SERVER_ADDRESS + POI + id;
		Log.v(TAG, "Call to: " + urlString);

		this.initializeConnection();

		StringBuilder sbBuilder = this.retrieveJson(urlString);

		// deserializzazione del Json ricevuto in un oggetto di tipo Restaurant
		setPoiContainer(gson.fromJson(sbBuilder.toString(), PoiContainer.class));

	}

	/**
	 * Restituisce il ristorante
	 * 
	 * @return
	 */
	public PoiContainer getPoiContainer()
	{
		return poiContainer;
	}

	/**
	 * Imposta il ristorante
	 * 
	 * @param restaurant
	 */
	public void setPoiContainer(PoiContainer pc)
	{
		this.poiContainer = pc;
	}

}
