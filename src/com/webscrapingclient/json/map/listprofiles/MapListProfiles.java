/**
 * 
 */
package com.webscrapingclient.json.map.listprofiles;

import java.util.ArrayList;

import com.webscrapingclient.json.map.profile.CommercialProfile;
import com.webscrapingclient.json.map.profile.Profile;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Vanessa Classe per il recupero della lista dei profili dal parsing
 *         del Json relativo Implementa Parcelable in modo da poter passare una
 *         sua istanza dalla classe SplashScreenActivity, dove viene effettuato
 *         il parsing e creata la lista, alla classe MainActivity in cui la
 *         lista viene utilizzata per popolare lo Spinner per la scelta del
 *         profilo per il recupero della lista dei poi ordinati.
 *
 */
public class MapListProfiles implements Parcelable
{
	private ArrayList<Integer> all_profiles_ids;

	public void addProfileId(CommercialProfile profile){
		all_profiles_ids.add(profile.getId());
	}
	
	public ArrayList<Integer> getAllProfilesIds()
	{
		return all_profiles_ids;
	}

	public void setAllProfilesIds(ArrayList<Integer> allProfilesIds)
	{
		this.all_profiles_ids = allProfilesIds;
	}

	protected MapListProfiles(Parcel in)
	{
		if (in.readByte() == 0x01)
		{
			all_profiles_ids = new ArrayList<Integer>();
			in.readList(all_profiles_ids, Integer.class.getClassLoader());
		} else
		{
			all_profiles_ids = null;
		}
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		if (all_profiles_ids == null)
		{
			dest.writeByte((byte) (0x00));
		} else
		{
			dest.writeByte((byte) (0x01));
			dest.writeList(all_profiles_ids);
		}
	}


	public static final Parcelable.Creator<MapListProfiles> CREATOR = new Parcelable.Creator<MapListProfiles>()
	{
		@Override
		public MapListProfiles createFromParcel(Parcel in)
		{
			return new MapListProfiles(in);
		}

		@Override
		public MapListProfiles[] newArray(int size)
		{
			return new MapListProfiles[size];
		}
	};
}