/**
 * 
 */
package com.webscrapingclient.presentation;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.webscrapingclientandroid.R;
import com.webscrapingclient.json.map.listprofiles.MapListProfiles;
import com.webscrapingclient.json.map.orderedlist.OrderedListMap;
import com.webscrapingclient.json.map.profile.CommercialProfile;
import com.webscrapingclient.json.map.profile.Position;
import com.webscrapingclient.restservice.CallRestService;
import com.webscrapingclient.utils.SpinnerItemAdapter;

/**
 * @author Vanessa Activity relativa alla scelta del profilo utente di test e
 *         della tipologia di poi da recuperare per la visualizzazione.
 *
 */
public class NewMainActivity extends Activity
{

	private RadioGroup rGroupProfiles, rGroupTypes;
	private RadioButton rbHotels, rbRestaurants;
	private Button btn_profile1, startButton;
	private TextView itemNumber;
	private boolean hotel_chosen = false;
	private int flagPoi; // vale 0 se viene scelto hotel, 1 se viene scelto
							// ristoranti
	private Spinner spinner;
	private Dialog dialog;
	private MapListProfiles profilesList;
	private OrderedListMap poiList;
	private Integer choiceProfile;
	private CommercialProfile testProfile;
	private int indexList = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		profilesList = intent.getExtras().getParcelable("profilesList");
		System.out.println(profilesList.getAllProfilesIds());

	//	testProfile = new CommercialProfile(12, 3, "Cinese", 3, 20.0, 20.0, new Position(41.855805, 12.6253663));
		// recupero vista spinner per la scelta del profilo di test
	//	profilesList.addProfileId(testProfile);
		System.out.println(profilesList.getAllProfilesIds());

		spinner = new Spinner(this);
		// itemNumber = (TextView)findViewById(R.id.textItemNUmber);

		spinner = (Spinner) findViewById(R.id.spinner1);
		// ArrayAdapter<Integer> spinnerAdapter = new
		// ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,
		// profilesList.getAllProfilesIds());
		// spinner.setAdapter(spinnerAdapter);

		SpinnerItemAdapter spinnerAdapter = new SpinnerItemAdapter(NewMainActivity.this, R.layout.spinner_item, profilesList.getAllProfilesIds());
		spinner.setAdapter(spinnerAdapter);

		// bottone visualizzazione dettagli profilo scelto nello spinner
		btn_profile1 = (Button) findViewById(R.id.buttonProfile);

		// radiobuttons tipologia poi
		rGroupTypes = (RadioGroup) findViewById(R.id.radioGroup2);
		rbHotels = (RadioButton) findViewById(R.id.radioTypeHotel);
		rbRestaurants = (RadioButton) findViewById(R.id.radioTypeRestaurant);

		// bottone submit
		startButton = (Button) findViewById(R.id.buttonSearch);

		// listener bottone dettagli profilo scelto
		btn_profile1.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// recupera l'id del profilo scelto
				choiceProfile = (Integer) spinner.getSelectedItem();
				System.out.println(choiceProfile);

				// mostra dettagli all'interno di una dialog
				createDialogForProfile(choiceProfile);
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
					// flagPoi = 0;
					hotel_chosen = true;
					createAlertDialog();
				} else
				{
					hotel_chosen = false;
				}
				Log.v("scelto hotel", " " + hotel_chosen);

				// chiama l'API /scorci/poi/profile/id per ottenere la lista dei
				// poi relativi
				final ProgressDialog progress = new ProgressDialog(NewMainActivity.this);
				choiceProfile = (Integer) spinner.getSelectedItem();
				try
				{
					progress.requestWindowFeature(Window.FEATURE_PROGRESS);
					progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
					progress.show();
					progress.setContentView(R.layout.custom_pd);
					progress.setTitle(null);
					TextView text = (TextView) progress.findViewById(R.id.progress_msg);
					text.setText("Recupero profili in corso..");
					progress.setIndeterminate(true);
					progress.setCancelable(false);
					
					
					CallRestService callRestService = new CallRestService();
					poiList = callRestService.callRestServiceForList(choiceProfile);
				} catch (ClientProtocolException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{

					e.printStackTrace();
				}

				/*
				 * passa all'activity contenente la lista dei poi filtrati solo
				 * se si vogliono visualizzare i ristoranti, dato che gli hotel
				 * non sono recuperabili
				 */
				if (!hotel_chosen)
				{
					Intent intent = new Intent(NewMainActivity.this, PoiDetailsActivity.class);
					intent.putExtra("typeChoice", hotel_chosen);
					intent.putExtra("indexList", indexList);
					intent.putExtra("poiList", poiList);
					startActivity(intent);
					finish();
					progress.cancel();
				}

				// }

			}

			private void createAlertDialog()
			{
				// TODO Auto-generated method stub
				dialog = new Dialog(NewMainActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				dialog.setContentView(R.layout.custom_dialog);

				// recupera il layout e setta titolo e testo del bottone
				TextView title_dialog = (TextView) dialog.findViewById(R.id.dialog_title);
				title_dialog.setText("Warning");
				TextView text = (TextView) dialog.findViewById(R.id.message);
				text.setText("Cannot retrieve data for Hotels.\nPlease search only for Restaurants.");
				Button positiveButton = (Button) dialog.findViewById(R.id.positive_button);
				positiveButton.setText("Ok");
				
				//listener per il bottone 
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

		});

	}

	@Override
	protected void onResume()
	{
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
		if (idProfile != 12)
		{
			text.setText(setProfileInformation(idProfile));
		} else
			text.setText(testProfile.toString());
		// listener per la chiusura della dialog
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
	 * @return un oggetto di tipo String contenente i dettagli del profilo, in
	 *         modo che essi possano essere visualizzati nella dialog relativa
	 */
	private String setProfileInformation(int id)
	{
		String responseJsonString = null;
		try
		{
			// chiamata all'api REST per l'ottenimento del Json relativo al
			// profilo
			CallRestService callRestService = new CallRestService();
			responseJsonString = callRestService.callRestServiceForProfile(id);

		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return responseJsonString;

	}

}
