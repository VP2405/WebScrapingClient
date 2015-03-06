/**
 * Activity di partenza dell'applicazione.
 * Prevede uno splash screen per la presentazione del client,
 * e il recupero dei dati relativi ai profili utente dal db.
 * Una volta recuperati i profili, richiama la MainActivity
 */
package com.webscrapingclient.presentation;


import com.example.webscrapingclientandroid.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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

   		 

   		 new Thread(new Runnable() {
   			 public void run() {
   				 
   				 //TODO richiama il servizio

   				 
   				 progress.cancel();
   			 }
   		 }).start();
	        
	        
	    }

	
	
	
}
