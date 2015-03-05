package com.example.webscrapingclientandroid;

import java.text.ChoiceFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;


public class MainActivity extends Activity {
	
	private RadioButton rbProfile1, rbProfile2, rbProfile3, rbHotels, rbRestaurants;
	private Button startButton;
	public boolean hotel_chosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //radiobuttons profili
        rbProfile1 = (RadioButton)findViewById(R.id.radioButton1);
        rbProfile2 = (RadioButton)findViewById(R.id.radioButton2);
        rbProfile3 = (RadioButton)findViewById(R.id.radioButton3);
        
        //radiobuttons tipologia poi
        rbHotels = (RadioButton)findViewById(R.id.radioButton4);
        rbRestaurants = (RadioButton)findViewById(R.id.radioButton4);
        
        
        //bottone submit
        startButton = (Button)findViewById(R.id.button1);
        
        
        startButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				
				// controlla il profilo scelto
				if (rbProfile1.isSelected())
				{
					//TODO
				}
				else if (rbProfile2.isSelected()) 
				{
					
					//TODO
				}
				else if (rbProfile3.isSelected())
				{
					//TODO
				}
				
				//TODO metodo per il parsing del profilo
				
				
				
				//TODO query al db
				
				
				
				// controllo del tipo di Poi scelto
				if(rbHotels.isSelected())
				{
					hotel_chosen = true;
				}
				else if(rbRestaurants.isSelected())
				{
					
				}
				
				
				//passa all'activity contenente la lista dei poi filtrati
				Intent intent = new Intent(MainActivity.this, PoiList.class);
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
}
