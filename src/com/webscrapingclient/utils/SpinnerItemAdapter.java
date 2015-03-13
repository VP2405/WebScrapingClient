/**
 * 
 */
package com.webscrapingclient.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.webscrapingclientandroid.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Vanessa Adapter per la definizione della view personalizzata dello
 *         Spinner all'interno della NewMainActivity
 *
 */
public class SpinnerItemAdapter extends ArrayAdapter<Integer>
{

	private Context context;
	int resourceId;
	private ArrayList<Integer> list;

	public SpinnerItemAdapter(Context context, int textViewResourceId, ArrayList<Integer> objects)
	{
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.resourceId = textViewResourceId;
		this.list = objects;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View spinnerItemView = convertView;
		spinnerItemView = inflater.inflate(resourceId, parent, false);

		TextView textProfileNumber = (TextView) spinnerItemView.findViewById(R.id.textViewProfileNumber);

		// imposta l'id del profilo in base alla posizione nella lista
		textProfileNumber.setText(list.get(position).toString());
		return spinnerItemView;

	}

}
