/**
 * 
 */
package com.webscrapingclient.presentation;

import com.example.webscrapingclientandroid.R;
import com.webscrapingclient.utils.ListViewAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * @author Vanessa
 *
 */
public class PoiDetailsActivity extends Activity
{
	private ImageView logo;
	private Dialog list_dialog;
	private boolean isHotel = false;
	private Button btn_detailsRating, btn_services, btn_policies;
	private ListViewAdapter adapter;

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
		
		
		
		
		
		/*
		 * aggiunta dei listener per i bottoni
		 */
		
		//listener bottone dettagli del rating
		btn_detailsRating.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//apre una custom dialog con la lista dei rating per categoria
				createDialog();
    			

				
			}
		});
		
		
		//listener bottone servizi
		btn_services.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//apre una custom dialog con la lista di tutti i servizi del PoI
				
			}
		});
		
		
		//listener bottone policies
		btn_policies.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//apre una custom dialog con la lista di tutte le policies del PoI
				
			}
		});
		
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	
	private void createDialog()
	{
		list_dialog = new Dialog(getBaseContext());
		list_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		list_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		list_dialog.setContentView(R.layout.custom_dialog);
		
		ListView list = (ListView)list_dialog.findViewById(R.layout.custom_dialog);
		list.setAdapter(adapter);

		Button positiveButton = (Button) list_dialog.findViewById(R.id.positive_button);
		
		
		positiveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {

				list_dialog.dismiss();	
			}
		});

		list_dialog.show();	
	}

}
