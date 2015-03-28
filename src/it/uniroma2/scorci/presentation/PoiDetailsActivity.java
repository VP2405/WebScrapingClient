/**
 * 
 */
package it.uniroma2.scorci.presentation;

import it.uniroma2.scorci.json.map.listprofiles.ListProfilesMap;
import it.uniroma2.scorci.json.map.orderedlist.OrderedListMap;
import it.uniroma2.scorci.json.map.poi.CookingType;
import it.uniroma2.scorci.json.map.poi.Hotel;
import it.uniroma2.scorci.json.map.poi.PoiContainer;
import it.uniroma2.scorci.json.map.poi.Policy;
import it.uniroma2.scorci.json.map.poi.Service;
import it.uniroma2.scorci.restservice.CallRestService;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.webscrapingclientandroid.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Activity per la visualizzazione dei dettagli del poi corrente. La vista viene
 * modificata a runtime, a partire dal layout definito in details_view, a
 * seconda della tipologia di poi da mostrare
 * 
 * @author Vanessa
 * 
 */
public class PoiDetailsActivity extends Activity
{
	
	private ImageView		logo, policiesImageView;
	private Dialog			dialog;
	private boolean			isHotel	= false;
	private Button			btn_detailsRating, btn_next, btn_previous, btn_home;
	private RatingBar		hotelRatingBar;
	private TextView		name, address, contacts, website, email, rating, reviews, avgPrice, cuisineDetails, stars, policies, services,
			policiesTitle, cuisinsTitle;
	private int				indexList;
	private ListProfilesMap	profilesList;
	private CallRestService	poiRestService;
	private OrderedListMap	poiList;
	private ProgressDialog	progress;

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

		profilesList = intent.getExtras().getParcelable("profilesList");
		System.out.println("Poidetails, profilelist: " + profilesList);
		// recupero viste e settaggio informazioni
		logo = (ImageView) findViewById(R.id.imageView1);

		// setta l'icona del PoI in base alla tipologia
		if (isHotel)
			logo.setBackgroundResource(R.drawable.hotel);
		else
			logo.setBackgroundResource(R.drawable.restaurant);

		// view relative ai dettagli del poi
		name = (TextView) findViewById(R.id.textView1);
		address = (TextView) findViewById(R.id.textView4);
		contacts = (TextView) findViewById(R.id.textView3);
		website = (TextView) findViewById(R.id.textView14);
		email = (TextView) findViewById(R.id.textView15);
		rating = (TextView) findViewById(R.id.textView5);
		reviews = (TextView) findViewById(R.id.textView6);
		avgPrice = (TextView) findViewById(R.id.textView2);
		cuisinsTitle =(TextView) findViewById(R.id.textView7);
		cuisineDetails = (TextView) findViewById(R.id.textView8);
		stars = (TextView) findViewById(R.id.textView9);
		hotelRatingBar = (RatingBar) findViewById(R.id.ratingBar1);
		services = (TextView) findViewById(R.id.textView11);
		policiesTitle = (TextView) findViewById(R.id.textView12);
		policiesImageView = (ImageView) findViewById(R.id.imageViewPolicies);
		policies = (TextView) findViewById(R.id.textView13);

		// bottoni
		btn_detailsRating = (Button) findViewById(R.id.button3);
		btn_next = (Button) findViewById(R.id.button4);
		btn_previous = (Button) findViewById(R.id.buttonPrev);
		btn_home = (Button) findViewById(R.id.buttonHome);

		System.out.println("indexlist vale " + indexList);
		if (indexList == 0)
			btn_previous.setVisibility(View.INVISIBLE);

		int sizePoiList;
		if(!isHotel)
			sizePoiList = poiList.getOrdered_restaurants_ids_list().size();
		else
			sizePoiList = poiList.getOrdered_hotels_ids_list().size();
		
		System.out.println("IndexList = " + indexList + "sizePoiList = " + sizePoiList);
		if (sizePoiList - 1 == indexList)
		{
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
			policiesImageView.setVisibility(View.INVISIBLE);
			policies.setVisibility(View.INVISIBLE);
		}
		else{
			avgPrice.setVisibility(View.INVISIBLE);
		}
			

		// riempimento dei vari campi con i dati del primo poi della lista
		try
		{
			if(!isHotel){
				Log.v("PoiDetailsActivity",""+ poiList.getOrdered_restaurants_ids_list());
				
				poiRestService = new CallRestService();
				poiRestService.callRestServiceForPoi(poiList.getOrdered_restaurants_ids_list().get(indexList));
			}	
			else{
				Log.v("PoiDetailsActivity",""+ poiList.getOrdered_hotels_ids_list());
				
				poiRestService = new CallRestService();
				poiRestService.callRestServiceForPoi(poiList.getOrdered_hotels_ids_list().get(indexList));
			}
		} catch (IllegalStateException e)
		{

			e.printStackTrace();
		} catch (IOException e)
		{

			e.printStackTrace();
		}

		final PoiContainer pc = poiRestService.getPoiContainer();
		if(!isHotel)
			fillRestaurantsViews(pc);
		else
			fillHotelsView(pc);
		
		// listener bottone dettagli del rating
		btn_detailsRating.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// apre una custom dialog con la lista dei rating per categoria
				String detailsString= pc.getMap().getHotel().getRating().toString();
				createDialog("Rating Details", detailsString);

			}
		});

		// bottone per il passaggio al poi successivo
		btn_next.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				/*
				 */
				progress = createProgressDialog("Retrieving data...");

				new Thread(new Runnable()
				{

					@Override
					public void run()
					{
						// crea una nuova activity per il nuovo PoI e toglie la
						// vecchia dallo stack
						Intent intent = new Intent(PoiDetailsActivity.this, PoiDetailsActivity.class);

						// passa alla visualizzazione del PoI successivo nella
						// lista
						intent.putExtra("indexList", indexList + 1);
						intent.putExtra("poiList", poiList);
						intent.putExtra("profilesList", profilesList);
						intent.putExtra("typeChoice", isHotel);
						progress.cancel();
						startActivity(intent);
						finish();
					}
				}).start();
				;

			}
		});

		// bottone per il passaggio al poi precedente
		btn_previous.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				progress = createProgressDialog("Retrieving data...");

				new Thread(new Runnable()
				{

					@Override
					public void run()
					{
						Intent intent = new Intent(PoiDetailsActivity.this, PoiDetailsActivity.class);
						intent.putExtra("indexList", indexList - 1);
						intent.putExtra("poiList", poiList);
						intent.putExtra("profilesList", profilesList);
						intent.putExtra("typeChoice", isHotel);
						startActivity(intent);
						finish();
						progress.cancel();
					}
				}).start();
			}
		});

		btn_home.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				progress = createProgressDialog("Retrieving data...");

				new Thread(new Runnable()
				{

					@Override
					public void run()
					{
						// passa alla MainActivity
						Intent intent = new Intent(PoiDetailsActivity.this, MainActivity.class);
						intent.putExtra("profilesList", profilesList);
						startActivity(intent);
						finish();

						progress.cancel();
					}
				}).start();
			}
		});

	}


	@Override
	public void onBackPressed()
	{
		super.onBackPressed();

		// chiude l'applicazione
		Process.killProcess(Process.myPid());
	}

	/**
	 * Riempie le varie view dell'activity con i dettagli del poi corrente
	 * 
	 * @param restaurant oggetto di tipo PoiRestaurant corrente
	 */
	private void fillRestaurantsViews(PoiContainer restaurant)
	{

		NumberFormat formatter = new DecimalFormat("#0.00000");     
		
		
		name.setText(restaurant.getMap().getRestaurant().getName());
		address.setText(restaurant.getMap().getRestaurant().getPosition().getAddress() + " "
				+ restaurant.getMap().getRestaurant().getPosition().getZipCode() + ", " + restaurant.getMap().getRestaurant().getPosition().getCity()
				+ " " + "\nGeographical Coordinates: (" + formatter.format(restaurant.getMap().getRestaurant().getPosition().getLatitude()) + " , "
				+ formatter.format(restaurant.getMap().getRestaurant().getPosition().getLongitude()) + ")");

		List<String> telephoneNumberString = trimTelephoneNumber(restaurant.getMap().getRestaurant().getContact().getTelephoneNumber());

		if (telephoneNumberString.isEmpty())
			contacts.setVisibility(View.INVISIBLE);
		else
			contacts.setText("Telephone Number: " + TextUtils.join("", telephoneNumberString));

		website.setText(restaurant.getMap().getRestaurant().getContact().getWebsite());
		email.setText(restaurant.getMap().getRestaurant().getContact().getEmail());
		rating.setText("Rating: " + restaurant.getMap().getRestaurant().getRating().getValue());
		reviews.setText("Reviews: " + restaurant.getMap().getRestaurant().getRating().getReview());

		if (!restaurant.getMap().getRestaurant().getAveragePrice().equals("0"))
		{
			avgPrice.setText("Average Price: " + restaurant.getMap().getRestaurant().getAveragePrice());
		} else
			avgPrice.setText("Average Price: Not Available");

		List<CookingType> listCuisines = restaurant.getMap().getRestaurant().getCookingType();
		if (!listCuisines.isEmpty())
			cuisineDetails.setText(TextUtils.join("", listCuisines));
		else
			cuisineDetails.setText("Not Available");
		List<it.uniroma2.scorci.json.map.poi.Service> listServices = restaurant.getMap().getRestaurant().getServices();
		if (!listServices.isEmpty())
			services.setText(TextUtils.join("", listServices));
		else
			services.setText("Not Available");
		
		//policies.setText(restaurant.getMap().getRestaurant().getPolicies().toString());

	}
	
	private void fillHotelsView(PoiContainer hotel) {
		
		NumberFormat formatter = new DecimalFormat("#0.00000"); 
		Hotel h = hotel.getMap().getHotel();
		
		name.setText(h.getName());
		address.setText(h.getPosition().getAddress() + " "
				+ h.getPosition().getZipCode() + ", " + h.getPosition().getCity()
				+ " " + "\nGeographical Coordinates: (" + formatter.format(h.getPosition().getLatitude()) + " , "
				+ formatter.format(h.getPosition().getLongitude()) + ")");

		List<String> telephoneNumberString = trimTelephoneNumber(h.getContact().getTelephoneNumber());

		if (telephoneNumberString.isEmpty())
			contacts.setVisibility(View.INVISIBLE);
		else
			contacts.setText("Telephone Number: " + TextUtils.join("", telephoneNumberString));

		website.setText(h.getContact().getWebsite());
		email.setText(h.getContact().getEmail());
		rating.setText("Rating: " + h.getRating().getValue());
		reviews.setText("Reviews: " + h.getRating().getReview());
		cuisineDetails.setText("Not Available");
		
		hotelRatingBar.setRating((float) h.getStars());
		
		List<Service> listServices = h.getServices();
		if (!listServices.isEmpty())
			services.setText(TextUtils.join("", listServices));
		else
			services.setText("Not Available");
		
		List<Policy> listPolicies= h.getPolicies();
		if (!listPolicies.isEmpty())
			policies.setText(TextUtils.join("", listPolicies));
		else
			policies.setText("Not Available\n");
		//policies.setText(h.getPolicies().toString());
	}

	/**
	 * Effettua il trim del numero di telefono, dato che per i contatti viene
	 * recuperata una lista di stringhe i cui item sono del tipo
	 * "Numero di telefono: 8980908".
	 * 
	 * @param telephoneNumber
	 * @return lista dei numeri di telefono trimmati
	 */
	private List<String> trimTelephoneNumber(List<String> telephoneNumber)
	{
		List<String> newList = new ArrayList<String>();

		/**
		 * se la lista dei contatti telefonici non è vuota allora splitta le
		 * stringhe per recuperare i soli numeri di telefono
		 */
		if (!telephoneNumber.isEmpty())
		{

			for (String item : telephoneNumber)
			{
				String[] tokens = item.split(":");
				newList.add(tokens[1].trim().toString());
			}
		}

		return newList;
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	/**
	 * Crea una dialog che mostra a schermo la stringa msgString passata come
	 * argomento
	 * 
	 * @param title titolo della Dialog
	 * @param msgString messaggio da visualizzare all'interno della Dialog
	 */
	private void createDialog(String title, String msgString)
	{
		dialog = new Dialog(PoiDetailsActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.custom_dialog);

		// recupera il layout e setta titolo e testo della dialog
		TextView title_dialog = (TextView) dialog.findViewById(R.id.dialog_title);
		title_dialog.setText(title);
		TextView text = (TextView) dialog.findViewById(R.id.message);
		Button positiveButton = (Button) dialog.findViewById(R.id.positive_button);
		positiveButton.setText("Ok");

		// setta informazioni da visualizzare nel messaggio
		text.setText(msgString);

		// listener per il bottone della dialog
		positiveButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				// rimuove la dialog dallo schermo
				dialog.dismiss();
			}
		});

		// mostra la dialog
		dialog.show();
	}

	private ProgressDialog createProgressDialog(String msg)
	{

		ProgressDialog pd = new ProgressDialog(PoiDetailsActivity.this);

		pd.requestWindowFeature(Window.FEATURE_PROGRESS);
		pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		pd.show();
		pd.setContentView(R.layout.custom_pd);
		pd.setTitle(null);
		TextView text = (TextView) pd.findViewById(R.id.progress_msg);
		text.setText(msg);
		pd.setIndeterminate(true);
		pd.setCancelable(false);

		return pd;
	}

}
