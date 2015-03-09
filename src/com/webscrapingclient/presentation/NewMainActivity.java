/**
 * 
 */
package com.webscrapingclient.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.webscrapingclientandroid.R;
import com.google.gson.Gson;
import com.webscrapingclient.controller.ControllerStartButton;
import com.webscrapingclient.utils.CommercialProfile;
import com.webscrapingclient.utils.Profile;

/**
 * @author Vanessa
 *
 */
public class NewMainActivity extends Activity
{

	private RadioGroup rGroupProfiles, rGroupTypes;
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
		setContentView(R.layout.activity_main2);

		//radiogroup 
		rGroupProfiles 	= (RadioGroup)findViewById(R.id.radioGroup1);
		rGroupTypes		= (RadioGroup)findViewById(R.id.radioGroup2);
		
		// radiobuttons profili
		rbProfile1 = (RadioButton) findViewById(R.id.radioProfile1);
		rbProfile2 = (RadioButton) findViewById(R.id.radioProfile2);
		rbProfile3 = (RadioButton) findViewById(R.id.radioProfile3);

		// bottoni dettagli profili
		btn_profile1 = (Button) findViewById(R.id.buttonProfile1);
		btn_profile2 = (Button) findViewById(R.id.buttonProfile2);
		btn_profile3 = (Button) findViewById(R.id.buttonProfile3);

		// radiobuttons tipologia poi
		rbHotels 		= (RadioButton) findViewById(R.id.radioTypeHotel);
		rbRestaurants 	= (RadioButton) findViewById(R.id.radioTypeRestaurant);

		// bottone submit
		startButton = (Button) findViewById(R.id.buttonSearch);

		
		
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
				
				// controllo del tipo di Poi scelto
				if (rbHotels.isChecked())
				{
					//flagPoi = 0;
					hotel_chosen = true;
				}
				Log.v("scelto hotel"," "+hotel_chosen);
				

					// passa all'activity contenente la lista dei poi filtrati					
					
					Intent intent = new Intent(NewMainActivity.this, PoiDetailsActivity.class);
					intent.putExtra("typeChoice", hotel_chosen);
					startActivity(intent);
					finish();
					
				//}


			}
		});
		
		
		
		//TODO controllo sui radiogroups

	}
	
	
	

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
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
		dialog = new Dialog(NewMainActivity.this);
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
		
		String urlString = "http://10.220.176.242:5555/scorci/profile/" + id;
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