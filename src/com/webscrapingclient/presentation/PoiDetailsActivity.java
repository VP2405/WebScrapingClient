/**
 * 
 */
package com.webscrapingclient.presentation;

import com.example.webscrapingclientandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * @author Vanessa
 *
 */
public class PoiDetailsActivity extends Activity
{
	private ImageView logo;
	private boolean isHotel = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_view);
		
		// controlla se la ricerca è basata su hotel o ristoranti
		Intent intent = getIntent();
		isHotel = intent.getExtras().getBoolean("typeChoice");
		
		//recupero viste e settaggio informazioni
		logo = (ImageView)findViewById(R.id.imageView1);
		
		//setta l'icona del PoI in base alla tipologia
		if(isHotel)
			logo.setBackgroundResource(R.drawable.hotel);
		else 
			logo.setBackgroundResource(R.drawable.rest);
		
		
		
		
		
		
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

}
