/**
 * 
 */
package com.webscrapingclient.presentation;

import java.io.IOException;
import java.util.List;

import com.example.webscrapingclientandroid.R;
import com.webscrapingclient.json.map.orderedlist.OrderedListMap;
import com.webscrapingclient.json.map.poi.PoiRestaurants;
import com.webscrapingclient.restservice.CallRestService;

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
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * @author Vanessa Activity per la visualizzazione dei dettagli del poi
 *         corrente. La vista viene modificata a runtime, a partire dal layout
 *         definito in details_view, a seconda della tipologia di poi da
 *         mostrare
 *
 */
public class PoiDetailsActivity extends Activity
{
	private ImageView logo;
	private Dialog dialog;
	private boolean isHotel = false;
	private Button btn_detailsRating, btn_next;
	private RatingBar hotelRatingBar;
	private TextView name, address, contacts, rating, reviews, avgPrice, cuisineDetails, stars, policies, services, policiesTitle;
	private int indexList;
	private CallRestService poiRestService;
	private OrderedListMap poiList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_activity);

		/*
		 * controlla se la ricerca è basata su hotel o ristoranti, recuperando
		 * il flag booleano inviato insieme all'intent dall'activity precedente
		 */
		Intent intent = getIntent();
		isHotel = intent.getExtras().getBoolean("typeChoice");
		indexList = intent.getExtras().getInt("indexList");
		poiList = intent.getExtras().getParcelable("poiList");

		// recupero viste e settaggio informazioni
		logo = (ImageView) findViewById(R.id.imageView1);

		// setta l'icona del PoI in base alla tipologia
		if (isHotel)
			logo.setBackgroundResource(R.drawable.hotel);
		else
			logo.setBackgroundResource(R.drawable.rest);

		// view relative ai dettagli del poi
		name = (TextView) findViewById(R.id.textView1);
		address = (TextView) findViewById(R.id.textView4);
		contacts = (TextView) findViewById(R.id.textView3);
		rating = (TextView) findViewById(R.id.textView5);
		reviews = (TextView) findViewById(R.id.textView6);
		avgPrice = (TextView) findViewById(R.id.textView2);
		cuisineDetails = (TextView) findViewById(R.id.textView8);
		stars = (TextView) findViewById(R.id.textView9);
		hotelRatingBar = (RatingBar) findViewById(R.id.ratingBar1);
		services = (TextView) findViewById(R.id.textView11);
		policiesTitle = (TextView) findViewById(R.id.textView12);
		policies = (TextView) findViewById(R.id.textView13);

		// bottoni
		btn_detailsRating = (Button) findViewById(R.id.button3);
		btn_next = (Button) findViewById(R.id.button4);
		int sizePoiList = poiList.getOrdered_restaurants_ids_list().size();
		System.out.println("IndexList = " + indexList + "sizePoiList = "+sizePoiList);
		if(sizePoiList -1 == indexList){
			System.out.println(sizePoiList + "=" + indexList);
			btn_next.setVisibility(View.INVISIBLE);
		}
		/*
		 * nel caso in cui venga effettuata una ricerca sui ristoranti, occorre
		 * nascondere le view relative a: - bottone per i dettagli del rating -
		 * numero di stelle - policies
		 */
		if (!isHotel)
		{
			btn_detailsRating.setVisibility(View.INVISIBLE);
			stars.setVisibility(View.INVISIBLE);
			hotelRatingBar.setVisibility(View.INVISIBLE);
			policiesTitle.setVisibility(View.INVISIBLE);
			policies.setVisibility(View.INVISIBLE);
		}

		// riempimento dei vari campi con i dati del primo poi della lista
		try
		{
			System.out.println(poiList.getOrdered_restaurants_ids_list());
			System.out.println("");
			poiRestService = new CallRestService();
			poiRestService.callRestServiceForPoi(poiList.getOrdered_restaurants_ids_list().get(indexList));
		} catch (IllegalStateException e)
		{

			e.printStackTrace();
		} catch (IOException e)
		{

			e.printStackTrace();
		}

		PoiRestaurants restaurant = poiRestService.getRestaurant();

		fillviews(restaurant);

		// listener bottone dettagli del rating
		btn_detailsRating.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// apre una custom dialog con la lista dei rating per categoria
				createDialog("Rating Details", null);

			}
		});

		btn_next.setOnClickListener(new OnClickListener()
		{


			@Override
			public void onClick(View v)
			{
				/*
				 */
				Intent intent = new Intent(PoiDetailsActivity.this,PoiDetailsActivity.class);
				intent.putExtra("indexList", indexList+1);
				intent.putExtra("poiList", poiList);
				startActivity(intent);

			}
		});

	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		Intent intent = new Intent(PoiDetailsActivity.this, NewMainActivity.class);
		startActivity(intent);

	}

	private void fillviews(PoiRestaurants restaurant)
	{
		
		name.setText(restaurant.getMap().getRestaurant().getName());
		address.setText(restaurant.getMap().getRestaurant().getPosition().getAddress() + " "
				+ restaurant.getMap().getRestaurant().getPosition().getZipCode() + ", " + restaurant.getMap().getRestaurant().getPosition().getCity()
				+ " " + "\nGeographical Coordinates :[" + restaurant.getMap().getRestaurant().getPosition().getLatitude() + " , "
				+ restaurant.getMap().getRestaurant().getPosition().getLongitude() + "]");

		contacts.setText("Website: " + restaurant.getMap().getRestaurant().getContact().getWebsite() + "\n" + "Email: "
				+ restaurant.getMap().getRestaurant().getContact().getEmail() + "\n"
				+ restaurant.getMap().getRestaurant().getContact().getTelephoneNumber());

		rating.setText("Rating: " + restaurant.getMap().getRestaurant().getRating().getValue());
		reviews.setText("Reviews: " + restaurant.getMap().getRestaurant().getRating().getReview());
		if(!restaurant.getMap().getRestaurant().getAveragePrice().equals("0")){
			avgPrice.setText("Average Price: " + restaurant.getMap().getRestaurant().getAveragePrice());
		}
		else
			avgPrice.setText("Average Price: N/A");
		
		cuisineDetails.setText(restaurant.getMap().getRestaurant().getCookingType().toString());
		List<com.webscrapingclient.json.map.poi.Service> listServices = restaurant.getMap().getRestaurant().getServices();
		if (!listServices.isEmpty())
			services.setText(listServices.toString());
		else
			services.setText("");

		policies.setText(restaurant.getMap().getRestaurant().getPolicies().toString());

	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	private void createDialog(String title, String msgString)
	{
		dialog = new Dialog(PoiDetailsActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.custom_dialog);

		// recupera il layout e setta titolo e testo del bottone
		TextView title_dialog = (TextView) dialog.findViewById(R.id.dialog_title);
		title_dialog.setText(title);
		TextView text = (TextView) dialog.findViewById(R.id.message);
		Button positiveButton = (Button) dialog.findViewById(R.id.positive_button);
		positiveButton.setText("Ok");

		// setta informazioni del profilo sulla dialog
		text.setText(msgString);

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

}
