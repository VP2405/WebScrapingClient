package it.uniroma2.scorci.json.map.orderedlist;

import it.uniroma2.scorci.json.map.poi.Restaurant;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe relativa al json che contiene la lista degli id dei poi relativi ad un
 * profilo utente
 * 
 * @author Vanessa
 * 
 */
public class OrderedListMap implements Parcelable {
	private ArrayList<Integer> ordered_hotels_ids_list;
	private ArrayList<Integer> ordered_restaurants_ids_list;

	// metodi getter e setter
	public ArrayList<Integer> getOrdered_hotels_ids_list() {
		return ordered_hotels_ids_list;
	}

	public void setOrdered_hotels_ids_list(
			ArrayList<Integer> ordered_hotels_ids_list) {
		this.ordered_hotels_ids_list = ordered_hotels_ids_list;
	}

	public ArrayList<Integer> getOrdered_restaurants_ids_list() {
		return ordered_restaurants_ids_list;
	}

	public void setOrdered_restaurants_ids_list(
			ArrayList<Integer> ordered_restaurants_ids_list) {
		this.ordered_restaurants_ids_list = ordered_restaurants_ids_list;
	}

	protected OrderedListMap(Parcel in) {
		if (in.readByte() == 0x01) {
			ordered_hotels_ids_list = new ArrayList<Integer>();
			in.readList(ordered_hotels_ids_list, Integer.class.getClassLoader());
		} else {
			ordered_hotels_ids_list = null;
		}
		if (in.readByte() == 0x01) {
			ordered_restaurants_ids_list = new ArrayList<Integer>();
			in.readList(ordered_restaurants_ids_list,
					Integer.class.getClassLoader());
		} else {
			ordered_restaurants_ids_list = null;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (ordered_hotels_ids_list == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(ordered_hotels_ids_list);
		}
		if (ordered_restaurants_ids_list == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(ordered_restaurants_ids_list);
		}
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<OrderedListMap> CREATOR = new Parcelable.Creator<OrderedListMap>() {
		@Override
		public OrderedListMap createFromParcel(Parcel in) {
			return new OrderedListMap(in);
		}

		@Override
		public OrderedListMap[] newArray(int size) {
			return new OrderedListMap[size];
		}
	};
}
