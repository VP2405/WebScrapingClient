/**
 * 
 */
package com.webscrapingclient.presentation;

import java.io.IOException;

import com.example.webscrapingclientandroid.R;
import com.webscrapingclient.poi.jsonmanager.PoiJsonParser;
import com.webscrapingclient.poi.jsonmanager.PoiRestaurants;
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
import android.widget.TextView;

/**
 * @author Vanessa
 *
 */
public class PoiDetailsActivity extends Activity
{
	private ImageView logo;
	private Dialog list_dialog;
	private boolean isHotel = false;
	private Button btn_detailsRating, btn_services, btn_policies, btn_next;
	private ListViewAdapter adapter;
	private TextView name, address, contacts, rating, reviews, avgPrice, cuisineDetails;
	private int id = 4;
	private PoiJsonParser pjp;
	
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
		
		
		//view relative ai dettagli del poi
		name 	= (TextView)findViewById(R.id.textView1);
		address	= (TextView)findViewById(R.id.textView4);
		contacts= (TextView)findViewById(R.id.textView3);
		rating	= (TextView)findViewById(R.id.textView5);
		reviews	= (TextView)findViewById(R.id.textView6);
		avgPrice= (TextView)findViewById(R.id.textView2);
		cuisineDetails= (TextView)findViewById(R.id.textView8);
		
		//bottoni
		btn_detailsRating 	= (Button)findViewById(R.id.button3);
		btn_policies		= (Button)findViewById(R.id.button1);
		btn_services		= (Button)findViewById(R.id.button2);
		btn_next			= (Button)findViewById(R.id.button4);
		
		
		
		//riempimento dei vari campi con i dati del primo poi della lista
		try
		{
			pjp = new PoiJsonParser(id);
		} catch (IllegalStateException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PoiRestaurants restaurant = pjp.getRestaurant();
		
		
		fillviews(restaurant);
		
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
    			list_dialog.setTitle("Dettagli Rating");
    			

				
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
		
		
		
		btn_next.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				/*
				 * nuova chiamata al servizio rest con id incrementato
				 * in modo da ottere e parsare il poi successivo in
				 * base all'ordine di pertinenza con il profilo utente scelto
				 */
//				try
//				{
//					PoiJsonParser pjp = new PoiJsonParser(id+1);
//				} catch (IllegalStateException e)
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e)
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			}
		});
		
	}

	private void fillviews(PoiRestaurants restaurant)
	{
		// TODO Auto-generated method stub
		name.setText(restaurant.getMap().getRestaurant().getName());
		address.setText(restaurant.getMap().getRestaurant().getPosition().getAddress() + " "
		+ restaurant.getMap().getRestaurant().getPosition().getZipCode() +", "
		+ restaurant.getMap().getRestaurant().getPosition().getCity()+" "
		+ "\nGeographical Coordinates :["+restaurant.getMap().getRestaurant().getPosition().getLatitude()+" , "
		+ restaurant.getMap().getRestaurant().getPosition().getLongitude()+"]");
		
		contacts.setText("Website: "+restaurant.getMap().getRestaurant().getContact().getWebsite()+"\n"
		+ "Email: "+restaurant.getMap().getRestaurant().getContact().getEmail()+"\n"
		+ restaurant.getMap().getRestaurant().getContact().getTelephoneNumber());
		
		rating.setText("Rating: "+ restaurant.getMap().getRestaurant().getRating().getValue());
		reviews.setText("Reviews: "+restaurant.getMap().getRestaurant().getRating().getReview());
		avgPrice.setText("Average Price: "+restaurant.getMap().getRestaurant().getAveragePrice());
		
		cuisineDetails.setText(restaurant.getMap().getRestaurant().getCookingType().toString());
		
		
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	
	private void createDialog()
	{
		list_dialog = new Dialog(PoiDetailsActivity.this);
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
