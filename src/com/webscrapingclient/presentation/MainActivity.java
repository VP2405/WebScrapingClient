package com.webscrapingclient.presentation;

import java.util.List;

import com.example.webscrapingclientandroid.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.webscrapingclient.controller.*;
import com.webscrapingclient.utils.Profile;

public class MainActivity extends Activity {
	
	private RadioButton rbProfile1, rbProfile2, rbProfile3, rbHotels, rbRestaurants;
	private Button btn_profile1, btn_profile2, btn_profile3, startButton;
	private boolean hotel_chosen = false;
	private int flagPoi;   //vale 0 se viene scelto hotel, 1 se viene scelto ristoranti  
	private Dialog dialog;
	private List<Profile> profileList;
	private Profile profile;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //radiobuttons profili
        rbProfile1 = (RadioButton)findViewById(R.id.radioButton1);
        rbProfile2 = (RadioButton)findViewById(R.id.radioButton2);
        rbProfile3 = (RadioButton)findViewById(R.id.radioButton3);
        
        //bottoni dettagli profili
        btn_profile1 = (Button)findViewById(R.id.button2);
        btn_profile2 = (Button)findViewById(R.id.button3);
        btn_profile3 = (Button)findViewById(R.id.button4);
        
        //radiobuttons tipologia poi
        rbHotels 		= (RadioButton)findViewById(R.id.radioButton4);
        rbRestaurants 	= (RadioButton)findViewById(R.id.radioButton5);
        
        //bottone submit
        startButton = (Button)findViewById(R.id.button1);
        
        /*
         * Implementazione dei listener dei bottoni
         */
        
        //listener bottoni dettagli profilo 1
        btn_profile1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//mostra dettagli all'interno di una dialog 
				createDialog(1);
			}
		});
        
        
        //listener bottoni dettagli profilo 2
        btn_profile2.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO mostra dettagli profilo 2
				createDialog(2);
			}
		});
        
        //listener bottoni dettagli profilo 3
        btn_profile3.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Mostra dettagli profilo 3
				createDialog(3);
			}
		});
        
        
        
        
        //listener per il bottone di ricerca
        startButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				ControllerStartButton controllerStartButton = new ControllerStartButton();
				// controlla il profilo scelto
				if (rbProfile1.isSelected())
				{
					controllerStartButton.fillProfile();
				}
				else if (rbProfile2.isSelected()) 
				{
					controllerStartButton.fillProfile();
				}
				else if (rbProfile3.isSelected())
				{
					controllerStartButton.fillProfile();
				}
				else //nessun profilo selezionato
				{
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Nessun profilo selezionato");
					toast.setDuration(Toast.LENGTH_LONG);
				}
				
				// controllo del tipo di Poi scelto
				if(rbHotels.isSelected())
				{
					flagPoi = 0;
					hotel_chosen = true;
				}
				else if(rbRestaurants.isSelected())
				{
					flagPoi = 1;
				}
				else {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Selezionare tipologia di PoI");
					toast.setDuration(Toast.LENGTH_LONG);
				}
				
				//TODO query al db
				controllerStartButton.manageQuery(flagPoi);
				
				//passa all'activity contenente la lista dei poi filtrati
				Intent intent = new Intent(MainActivity.this, PoiDetailsActivity.class);
				intent.putExtra("typeChoice", hotel_chosen);
				startActivity(intent);
				finish();
				
			}
		});
        
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    
    /**
     * Crea la dialog atta a contenere 
     * le informazioni del profilo selezionato
     * 
     * @param idProfile	id del profilo
     */
    private void createDialog(int idProfile)
	{
		dialog = new Dialog(getBaseContext());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.setContentView(R.layout.custom_dialog);
		
		//recupera il layout e setta titolo e testo del bottone
		TextView title_dialog = (TextView) dialog.findViewById (R.id.dialog_title);
		title_dialog.setText("Profile "+idProfile);
		TextView text = (TextView) dialog.findViewById(R.id.message);
		Button positiveButton = (Button) dialog.findViewById(R.id.positive_button);
		positiveButton.setText("Ok");
		
		//setta informazioni del profilo sulla dialog
		String profileDetails = setProfileInformation(idProfile);
		text.setText(profileDetails);
		
		positiveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {

				dialog.dismiss();	
			}
		});

		dialog.show();	
	}


	/**
	 * Recupera le informazioni
	 * del profilo utente corrispondente all'id in input
	 * 
	 * @param id	id del profilo da visualizzare, corrisponde alla posizione in lista
	 * @return 
	 */
	private String setProfileInformation(int id)
	{
		String profileString = profileList.get(id).toString();
		return profileString;
		
		
	}

}
