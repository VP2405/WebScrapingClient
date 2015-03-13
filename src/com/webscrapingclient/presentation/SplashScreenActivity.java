/**
 * Activity di partenza dell'applicazione.
 * Prevede uno splash screen per la presentazione del client,
 * e il recupero dei dati relativi ai profili utente dal db.
 * Una volta recuperati i profili, richiama la MainActivity
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

import com.example.webscrapingclientandroid.R;
import com.google.gson.Gson;
import com.webscrapingclient.json.map.listprofiles.ListProfilesJson;
import com.webscrapingclient.json.map.listprofiles.MapListProfiles;
import com.webscrapingclient.json.map.profile.CommercialProfile;
import com.webscrapingclient.json.map.profile.Profile;
import com.webscrapingclient.restservice.CallRestService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

/**
 * @author Vanessa
 *
 */
public class SplashScreenActivity extends Activity
{
	// Splash screen timer
	private static int TIME_OUT = 3000;
	private MapListProfiles profilesList;
	private String tag = "SplashScreenActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		System.out.println("onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{

				startApplication();

			}

		}, 3000);

	}

	private void startApplication()
	{
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable()
		{
			public void run()
			{

				final ProgressDialog progress = new ProgressDialog(SplashScreenActivity.this);

				progress.requestWindowFeature(Window.FEATURE_PROGRESS);
				progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				progress.show();
				progress.setContentView(R.layout.custom_pd);
				progress.setTitle(null);
				TextView text = (TextView) progress.findViewById(R.id.progress_msg);
				text.setText("Recupero profili in corso..");
				progress.setIndeterminate(true);
				progress.setCancelable(false);

				// TODO richiama il servizio
				try
				{

					CallRestService callRestService = new CallRestService();
					profilesList = callRestService.callRestServiceForListProfiles();

				} catch (ClientProtocolException e)
				{
					Log.v(tag, "ClientProtocolException");
					e.printStackTrace();
				} catch (IOException e)
				{
					Log.v(tag, "IOException");
					e.printStackTrace();
				}

				// passaggio all'activity successiva dopo la scadenza del timer
				Intent i = new Intent(SplashScreenActivity.this, NewMainActivity.class);
				i.putExtra("profilesList", profilesList);
				progress.cancel();
				startActivity(i);

				finish();
			}

		}, TIME_OUT);
	}

}
