/**
 * 
 */
package it.uniroma2.scorci.presentation;

import it.uniroma2.scorci.json.map.commercialprofile.CommercialProfile;
import it.uniroma2.scorci.json.map.commercialprofile.Position;
import it.uniroma2.scorci.json.map.listprofiles.ListProfilesMap;
import it.uniroma2.scorci.json.map.orderedlist.OrderedListMap;
import it.uniroma2.scorci.restservice.CallRestService;
import it.uniroma2.scorci.utils.SpinnerItemAdapter;

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

/**
 * Activity relativa alla scelta del profilo utente di test e della tipologia di
 * poi da recuperare per la visualizzazione.
 * 
 * @author Vanessa
 * 
 */
public class MainActivity extends Activity {

	private RadioGroup rGroupProfiles, rGroupTypes;
	private RadioButton rbHotels, rbRestaurants;
	private Button btn_profile1, startButton;
	private boolean flagPoi = false;//vale false se viene scelto un ristorante,true se viene scelto un hotel
	
	private Spinner spinner;
	private Dialog dialog;
	private ListProfilesMap profilesList;
	private OrderedListMap poiList;
	private Integer choiceProfile;
	private CommercialProfile testProfile;
	private int indexList = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		profilesList = intent.getExtras().getParcelable("profilesList");
		System.out.println(profilesList.getAllProfilesIds());

		System.out.println(profilesList.getAllProfilesIds());

		spinner = new Spinner(this);
		spinner = (Spinner) findViewById(R.id.spinner1);
		SpinnerItemAdapter spinnerAdapter = new SpinnerItemAdapter(
				MainActivity.this, R.layout.spinner_item,
				profilesList.getAllProfilesIds());
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
		btn_profile1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// recupera l'id del profilo scelto
				choiceProfile = (Integer) spinner.getSelectedItem();
				System.out.println(choiceProfile);

				// mostra dettagli all'interno di una dialog
				createDialogForProfile(choiceProfile);
			}
		});

		// listener per il bottone di ricerca
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// controllo del tipo di Poi scelto
				if (rbHotels.isChecked()) {
					flagPoi = true;
					createAlertDialog("Cannot retrieve data for Hotels.\nPlease search only for Restaurants.");
				} else {
					flagPoi = false;
				}
				Log.v("scelto hotel", " " + flagPoi);

				/*
				 * passa all'activity contenente la lista dei poi filtrati solo
				 * se si vogliono visualizzare i ristoranti, dato che gli hotel
				 * non sono recuperabili
				 */
				if (!flagPoi) {

					// chiama l'API /scorci/poi/profile/id per ottenere la lista
					// dei poi relativi al profilo scelto

					final ProgressDialog progress = new ProgressDialog(
							MainActivity.this);
					progress.requestWindowFeature(Window.FEATURE_PROGRESS);
					progress.getWindow().setBackgroundDrawable(
							new ColorDrawable(Color.TRANSPARENT));
					progress.show();
					progress.setContentView(R.layout.custom_pd);
					progress.setTitle(null);
					TextView text = (TextView) progress
							.findViewById(R.id.progress_msg);
					text.setText("Retrieving data..");
					progress.setIndeterminate(true);
					progress.setCancelable(false);

					choiceProfile = (Integer) spinner.getSelectedItem();

					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								CallRestService callRestService = new CallRestService();
								poiList = callRestService
										.callRestServiceForList(choiceProfile);

								System.out.println("Dopo il parsing, la lista dei poi è: "
										+ poiList
												.getOrdered_restaurants_ids_list());

								// se riesco a recuperare i dati dal server
								// allora passo all'activity successiva
								Intent intent = new Intent(
										MainActivity.this,
										PoiDetailsActivity.class);
								intent.putExtra("typeChoice", flagPoi);
								intent.putExtra("indexList", indexList);
								intent.putExtra("profilesList", profilesList);
								intent.putExtra("poiList", poiList);
								startActivity(intent);
								finish();
								progress.cancel();

							} catch (ClientProtocolException e) {
								e.printStackTrace();
								Log.v("NewMainActivity",
										"ClientProtocolException");
								createAlertDialog("Cannot conclude the operation.\nProblems in protocol.");
							} catch (IOException e) {
								Log.v("NewMainActivity", "IOexception");
								createAlertDialog("Cannot conclude the operation.");
								e.printStackTrace();
							}
						}
					}).start();

				}

			}

			private void createAlertDialog(String msg) {

				dialog = new Dialog(MainActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.getWindow().setBackgroundDrawable(
						new ColorDrawable(Color.TRANSPARENT));
				dialog.setContentView(R.layout.custom_dialog);

				// recupera il layout e setta titolo e testo del bottone
				TextView title_dialog = (TextView) dialog
						.findViewById(R.id.dialog_title);
				title_dialog.setText("Warning");
				TextView text = (TextView) dialog.findViewById(R.id.message);
				text.setText(msg);
				Button positiveButton = (Button) dialog
						.findViewById(R.id.positive_button);
				positiveButton.setText("Ok");

				// listener per il bottone
				positiveButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						dialog.dismiss();
					}
				});
				dialog.show();

			}

		});

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * Crea la dialog atta a contenere le informazioni del profilo selezionato
	 * 
	 * @param idProfile
	 *            id del profilo
	 */
	private void createDialogForProfile(int idProfile) {
		dialog = new Dialog(MainActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.custom_dialog);

		// recupera il layout e setta titolo e testo del bottone
		TextView title_dialog = (TextView) dialog
				.findViewById(R.id.dialog_title);
		title_dialog.setText("Profile " + idProfile);
		TextView text = (TextView) dialog.findViewById(R.id.message);
		Button positiveButton = (Button) dialog
				.findViewById(R.id.positive_button);
		positiveButton.setText("Ok");

		// setta informazioni del profilo sulla dialog
		text.setText(setProfileInformation(idProfile));
		
		// listener per la chiusura della dialog
		positiveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
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
	private String setProfileInformation(int id) {
		String responseJsonString = null;
		try {
			// chiamata all'api REST per l'ottenimento del Json relativo al
			// profilo
			CallRestService callRestService = new CallRestService();
			responseJsonString = callRestService.callRestServiceForProfile(id);

		} catch (ClientProtocolException e) {
			// TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
		return responseJsonString;

	}

}
