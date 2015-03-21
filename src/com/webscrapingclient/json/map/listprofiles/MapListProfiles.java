package com.webscrapingclient.json.map.listprofiles;

import java.util.ArrayList;

import com.webscrapingclient.json.map.profile.CommercialProfile;
import com.webscrapingclient.json.map.profile.Profile;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe per il recupero della lista dei profili dal parsing del Json relativo
 * Implementa Parcelable in modo da poter passare una sua istanza dalla classe
 * SplashScreenActivity, dove viene effettuato il parsing e creata la lista,
 * alla classe MainActivity in cui la lista viene utilizzata per popolare lo
 * Spinner per la scelta del profilo per il recupero della lista dei poi
 * ordinati.
 * 
 * @author Vanessa
 * 
 */
public class MapListProfiles implements Parcelable {

	private ArrayList<Integer> all_profiles_ids;

	/**
	 * Costruttore per la classe MapListProfiles
	 * 
	 * @param all_profiles_ids
	 */
	public MapListProfiles(ArrayList<Integer> all_profiles_ids) {
		super();
		this.all_profiles_ids = all_profiles_ids;
	}

	/**
	 * Aggiunge l'id del profilo passato alla lista degli id
	 * 
	 * @param profile
	 */
	public void addProfileId(CommercialProfile profile) {
		all_profiles_ids.add(profile.getId());
	}

	/**
	 * Restituisce la lista degli id di tutti i profili presenti
	 * 
	 * @return
	 */
	public ArrayList<Integer> getAllProfilesIds() {
		return all_profiles_ids;
	}

	/**
	 * Imposta come lista dei profili quella passata al metodo
	 * 
	 * @param allProfilesIds
	 */
	public void setAllProfilesIds(ArrayList<Integer> allProfilesIds) {
		this.all_profiles_ids = allProfilesIds;
	}

	/**
	 * Costruttore di MapListProfiles per l'interfaccia Parcelable
	 * 
	 * @param in
	 */
	protected MapListProfiles(Parcel in) {
		if (in.readByte() == 0x01) {
			all_profiles_ids = new ArrayList<Integer>();
			in.readList(all_profiles_ids, Integer.class.getClassLoader());
		} else {
			all_profiles_ids = null;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (all_profiles_ids == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(all_profiles_ids);
		}
	}

	public static final Parcelable.Creator<MapListProfiles> CREATOR = new Parcelable.Creator<MapListProfiles>() {
		@Override
		public MapListProfiles createFromParcel(Parcel in) {
			return new MapListProfiles(in);
		}

		@Override
		public MapListProfiles[] newArray(int size) {
			return new MapListProfiles[size];
		}
	};
}
