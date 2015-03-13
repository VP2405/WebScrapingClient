/**
 * 
 */
package com.webscrapingclient.presentation;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.example.webscrapingclientandroid.R;
import com.webscrapingclient.json.map.listprofiles.MapListProfiles;
import com.webscrapingclient.restservice.CallRestService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Vanessa
 *
 */
public class NewSplashScreenActivity extends Activity
{

	private Button startButton;
	private static int TIME_OUT = 3000;
	private MapListProfiles profilesList;
	private String tag = "SplashScreenActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen2);

		startButton = (Button) findViewById(R.id.buttonStart);
		startButton.setVisibility(View.INVISIBLE);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{

				startButton.performClick();

			}
		}, 3000);

	}

	public void startApplication(View view)
	{
		final ProgressDialog progress = new ProgressDialog(NewSplashScreenActivity.this);

		progress.requestWindowFeature(Window.FEATURE_PROGRESS);
		progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		progress.getWindow().getAttributes().gravity = Gravity.BOTTOM;
		progress.show();
		progress.setContentView(R.layout.custom_pd);
		progress.setTitle(null);
		TextView text = (TextView) progress.findViewById(R.id.progress_msg);
		text.setText("Recupero dati in corso..");
		progress.setIndeterminate(true);
		progress.setCancelable(false);

		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable()
		{
			public void run()
			{

				// TODO richiama il servizio
				try
				{

					CallRestService callRestService = new CallRestService();
					profilesList = callRestService.callRestServiceForListProfiles();

				} catch (ClientProtocolException e)
				{
					Log.v(tag, "ClientProtocolException");
					e.printStackTrace();
				} catch (IOException e)
				{
					Log.v(tag, "IOException");
					e.printStackTrace();
				}

				// passaggio all'activity successiva dopo la scadenza
				// del timer
				Intent i = new Intent(NewSplashScreenActivity.this, NewMainActivity.class);
				i.putExtra("profilesList", profilesList);
				progress.cancel();
				startActivity(i);

				finish();
			}

		}, TIME_OUT);
	}
	
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		Process.killProcess(Process.myPid()); 

	}
}
