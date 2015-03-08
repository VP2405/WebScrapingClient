package com.webscrapingclient.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.webscrapingclientandroid.R;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.webscrapingclient.controller.*;
import com.webscrapingclient.poi.jsonmanager.PoiJsonParser;
import com.webscrapingclient.utils.CommercialProfile;
import com.webscrapingclient.utils.Profile;

public class MainActivity extends Activity
{

	private RadioButton rbProfile1, rbProfile2, rbProfile3, rbHotels, rbRestaurants;
	private Button btn_profile1, btn_profile2, btn_profile3, startButton;
	private boolean hotel_chosen = false;
	private int flagPoi; // vale 0 se viene scelto hotel, 1 se viene scelto
							// ristoranti
	private Dialog dialog;
	private List<CommercialProfile> profileList;
	private CommercialProfile profile;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// radiobuttons profili
		rbProfile1 = (RadioButton) findViewById(R.id.radioButton1);
		rbProfile2 = (RadioButton) findViewById(R.id.radioButton2);
		rbProfile3 = (RadioButton) findViewById(R.id.radioButton3);

		// bottoni dettagli profili
		btn_profile1 = (Button) findViewById(R.id.button2);
		btn_profile2 = (Button) findViewById(R.id.button3);
		btn_profile3 = (Button) findViewById(R.id.button4);

		// radiobuttons tipologia poi
		rbHotels = (RadioButton) findViewById(R.id.radioButton4);
		rbRestaurants = (RadioButton) findViewById(R.id.radioButton5);

		// bottone submit
		startButton = (Button) findViewById(R.id.button1);

		
		
		/*
		 * Implementazione dei listener dei bottoni relativi ai profili
		 */

		// listener bottoni dettagli profilo 1
		btn_profile1.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// mostra dettagli all'interno di una dialog
				createDialogForProfile(1);
			}
		});

		// listener bottoni dettagli profilo 2
		btn_profile2.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO mostra dettagli profilo 2
				createDialogForProfile(2);
			}
		});

		// listener bottoni dettagli profilo 3
		btn_profile3.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Mostra dettagli profilo 3
				createDialogForProfile(3);
			}
		});

		
		// listener per il bottone di ricerca
		startButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				ControllerStartButton controllerStartButton = new ControllerStartButton();
				// controlla il profilo scelto
				if (rbProfile1.isSelected())
				{
					controllerStartButton.fillProfile();
				} else if (rbProfile2.isSelected())
				{
					controllerStartButton.fillProfile();
				} else if (rbProfile3.isSelected())
				{
					controllerStartButton.fillProfile();
				}
				


				// controllo del tipo di Poi scelto
				if (rbHotels.isSelected())
				{
					flagPoi = 0;
					hotel_chosen = true;
				} else if (rbRestaurants.isSelected())
				{
					flagPoi = 1;
				}

				
//				if(!rbProfile1.isSelected() && !rbProfile2.isSelected() && !rbProfile3.isSelected())
//				{
//				    Toast toast = Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT);
//				    toast.setText("Nessun profilo selezionato");
//				    toast.setDuration(Toast.LENGTH_LONG);
//				    toast.show();
//				}
//				else if(!rbHotels.isSelected() && !rbRestaurants.isSelected())
//				{
//				    Toast toast = Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT);
//				    toast.setText("Selezionare tipologia di PoI");
//				    toast.setDuration(Toast.LENGTH_LONG);
//				   
//				    toast.show();
//				}
//				else {
					// passa all'activity contenente la lista dei poi filtrati					
					
					Intent intent = new Intent(MainActivity.this, PoiDetailsActivity.class);
					intent.putExtra("typeChoice", hotel_chosen);
					startActivity(intent);
					finish();
					
				//}


			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Crea la dialog atta a contenere le informazioni del profilo selezionato
	 * 
	 * @param idProfile
	 *            id del profilo
	 */
	private void createDialogForProfile(int idProfile)
	{
		dialog = new Dialog(MainActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.custom_dialog);

		// recupera il layout e setta titolo e testo del bottone
		TextView title_dialog = (TextView) dialog.findViewById(R.id.dialog_title);
		title_dialog.setText("Profile " + idProfile);
		TextView text = (TextView) dialog.findViewById(R.id.message);
		Button positiveButton = (Button) dialog.findViewById(R.id.positive_button);
		positiveButton.setText("Ok");

		// setta informazioni del profilo sulla dialog
		text.setText(setProfileInformation(idProfile));

		positiveButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{

				dialog.dismiss();
			}
		});

		dialog.show();
	}

	
	
	/**
	 * Recupera le informazioni del profilo utente corrispondente all'id in
	 * input. 
	 * 
	 * @param id
	 *            id del profilo da visualizzare, corrisponde alla posizione in
	 *            lista
	 * @return
	 */
	private String setProfileInformation(int id)
	{
		String responseJsonString = null;
		try
		{
			responseJsonString = callRestServiceForProfile(id);
		} catch (ClientProtocolException e)
		{
			
			e.printStackTrace();
		} catch (IOException e)
		{
			
			e.printStackTrace();
		}

		return responseJsonString;

	}
	
	
	

	/**
	 * Richiama il servizio rest utilizzando un client Apache Http per ottenere
	 * i profili in formato JSON, e parsarli tramite Gson in un oggetto di tipo Profile
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String callRestServiceForProfile(int id) throws ClientProtocolException, IOException
	{
		
		String urlString = "http://192.168.1.102:5555/scorci/profile/" + id;

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
		

		String line = "";
		while ((line = rd.readLine()) != null)
		{
			//System.out.println("JSON:"+line);
			sbBuilder.append(line);
		}

		// deserializzazione del Json ricevuto in un oggetto di tipo Profile
		Gson gson = new Gson();
		Profile profile = gson.fromJson(sbBuilder.toString(), Profile.class);

		return profile.getMap().getCommercialProfile().toString();

	}

}
