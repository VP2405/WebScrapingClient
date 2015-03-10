/**
 * 
 */
package com.webscrapingclient.json.map.orderedlist;

import java.util.ArrayList;

import com.webscrapingclient.json.map.poi.Restaurant;

/**
 * @author Vanessa
 *
 */
public class OrderedListMap
{

	private ArrayList<Restaurant> ordered_poi_ids_list;

	public ArrayList<Restaurant> getOrderedPoiIdsList()
	{
		return ordered_poi_ids_list;
	}

	public void setOrderedPoiIdsList(ArrayList<Restaurant> ordered_poi_ids_list)
	{
		this.ordered_poi_ids_list = ordered_poi_ids_list;
	}
}
