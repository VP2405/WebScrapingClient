/**
 * 
 */
package com.webscrapingclient.presentation;

import com.example.webscrapingclientandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author Vanessa
 *
 */
public class PoiList extends Activity
{

	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		
		
		
		list = (ListView)findViewById(R.id.list);
		
		
		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
				
				//Passa all'activity contenente i dettagli del poi selezionato
				Intent intent = new Intent(PoiList.this, PoiDetailsActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

}
