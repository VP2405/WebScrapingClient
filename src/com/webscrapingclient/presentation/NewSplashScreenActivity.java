/**
 * 
 */
package com.webscrapingclient.presentation;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.example.webscrapingclientandroid.R;
import com.webscrapingclient.json.map.listprofiles.MapListProfiles;
import com.webscrapingclient.restservice.CallRestService;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Vanessa
 *
 */
public class NewSplashScreenActivity extends Activity
{

	private Button startButton;
	private static int TIME_OUT = 4000;
	private MapListProfiles profilesList;
	private Dialog dialog;
	private String tag = "SplashScreenActivity";
	private boolean isConnected;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen2);

		startButton = (Button) findViewById(R.id.buttonStart);
		startButton.setVisibility(View.INVISIBLE);


		//aspetta 3 secondi e verifica che sia disponibile la connessione a Internet
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{

				// TODO controlla che la connessione di rete sia attiva
				isConnected = checkConnection();
				if(isConnected)
				startButton.performClick();

			}
		}, 3000);

	}


	public void startApplication(View view)
	{
		final ProgressDialog progress = new ProgressDialog(NewSplashScreenActivity.this);

		progress.requestWindowFeature(Window.FEATURE_PROGRESS);
		progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		progress.getWindow().getAttributes().gravity = Gravity.BOTTOM;
		progress.show();
		progress.setContentView(R.layout.custom_pd);
		progress.setTitle(null);
		TextView text = (TextView) progress.findViewById(R.id.progress_msg);
		text.setText("Recupero dati in corso..");
		progress.setIndeterminate(true);
		progress.setCancelable(false);

		// TODO Auto-generated method stub
		new Thread(new Runnable()
		{
			public void run()
			{

				// TODO richiama il servizio
				try
				{

					CallRestService callRestService = new CallRestService();
					
					profilesList = callRestService.callRestServiceForListProfiles();

				} catch (ClientProtocolException e)
				{
					Log.v(tag, "ClientProtocolException");
					createWarningDialog("Cannot retrieve data.\nThe application will be closed.");
					e.printStackTrace();
				} catch (IOException e)
				{
					Log.v(tag, "IOException");
					createWarningDialog("Cannot retrieve data.\nNo Server Response.\nThe application will be closed.");
					e.printStackTrace();
				}

				//passa all'activity successiva solo se si è ottenuta risposta da parte del server
				if(profilesList != null){
				Intent i = new Intent(NewSplashScreenActivity.this, NewMainActivity.class);
				i.putExtra("profilesList", profilesList);
				progress.cancel();
				startActivity(i);
				
				finish();
				}
				
			}

		}).start();
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		Process.killProcess(Process.myPid());

	}

	private boolean checkConnection()
	{
		boolean connected;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		
		//mostra un alert dialog all'utente e chiude l'applicazione
		if(activeNetworkInfo == null && activeNetworkInfo == null)
		{
			createWarningDialog("No Internet Connection Available.\nThe Application will be closed.");
			connected = false;
		}
		else {
			connected = true;
		}
		return connected;
		

	}
	
	
	
	private void createWarningDialog(String msg)
	{
		dialog = new Dialog(NewSplashScreenActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.custom_dialog);

		// recupera il layout e setta titolo e testo del bottone
		TextView title_dialog = (TextView) dialog.findViewById(R.id.dialog_title);
		title_dialog.setCompoundDrawablesWithIntrinsicBounds(R.drawable.warning, 0, 0, 0);
		title_dialog.setText("Warning");
		TextView text = (TextView) dialog.findViewById(R.id.message);
		text.setText(msg);
		Button positiveButton = (Button) dialog.findViewById(R.id.positive_button);
		positiveButton.setText("Ok");

		
		// listener per la chiusura della dialog
		positiveButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				dialog.dismiss();
				finish();
				Process.killProcess(Process.myPid());
			}
		});
		dialog.show();
	}
}
