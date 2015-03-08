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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.webscrapingclientandroid.R;
import com.google.gson.Gson;
import com.webscrapingclient.utils.CommercialProfile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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
    private String tag = "SplashScreenActivity";
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.splash_screen);
	        
	    	
	    	//mostra una schermata di presentazione dell'applicazione per 3 secondi
	        new Handler().postDelayed(new Runnable() {   
	        	
	            @Override
	            public void run() {
	               
	            	//passaggio all'activity successiva dopo la scadenza del timer
	                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
	                startActivity(i);
	 
	                finish();
	            }
	        }, TIME_OUT);
	        
	        
	        /*
	         * terminati i 3 secondi richiama i servizi REST per ottenere la lista dei profili
	         * mostrando per tutto il tempo di recupero dei dati una progress dialog
	         */
//	     final ProgressDialog progress = new ProgressDialog(SplashScreenActivity.this);
//   		 progress.requestWindowFeature(Window.FEATURE_PROGRESS);
//   		 progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//   		 progress.show();
//   		 progress.setContentView(R.layout.custom_pd);
//   		 progress.setTitle(null);
//   		 TextView text = (TextView) progress.findViewById(R.id.progress_msg);
//   		 text.setText("Recupero profili in corso..");
//   		 progress.setIndeterminate(true);
//   		 progress.setCancelable(false);

   		 

//   		 new Thread(new Runnable() {
//   			 public void run() {
//   				 
//   				 //TODO richiama il servizio
//   				 try
//				{
//					callRestService();
//				} catch (ClientProtocolException e)
//				{
//					Log.v(tag, "ClientProtocolException");
//					e.printStackTrace();
//				} catch (IOException e)
//				{
//					Log.v(tag, "IOException");
//					e.printStackTrace();
//				}
//			 
//   				 progress.cancel();
//   			 }
//
//
//   		 }).start();
//	        
	        
	    }

//		/**
//		 * Richiama il servizio rest utilizzando un client Apache Http
//		 * per ottenere la lista dei profili in formato JSON
//		 * 
//		 * @throws ClientProtocolException
//		 * @throws IOException
//		 */
//		private void callRestService() throws ClientProtocolException, IOException
//		{
//			// TODO Auto-generated method stub
//			String urlString = "http://"+"";
//			
//			HttpClient client = new DefaultHttpClient();
//	        HttpGet request = new HttpGet(urlString);
//	        HttpResponse response = client.execute(request);
//	        
//	        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
//	        
//	        //stampa a video
//	        String line = "";
//	        while ((line = rd.readLine()) != null) {
//	          System.out.println(line);
//	        }
//	        
//	        //conversione in oggetto di tipo Profile
//	        Gson gson = new Gson();
//	        gson.fromJson(rd, Profile.class);
//	        
//		}
	
	
}
