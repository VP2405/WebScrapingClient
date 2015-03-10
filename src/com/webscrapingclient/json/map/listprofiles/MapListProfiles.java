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
	private ArrayList<Integer> allProfilesIds;

	public void addProfileId(CommercialProfile profile){
		allProfilesIds.add(profile.getId());
	}
	
	public ArrayList<Integer> getAllProfilesIds()
	{
		return allProfilesIds;
	}

	public void setAllProfilesIds(ArrayList<Integer> allProfilesIds)
	{
		this.allProfilesIds = allProfilesIds;
	}

	protected MapListProfiles(Parcel in)
	{
		if (in.readByte() == 0x01)
		{
			allProfilesIds = new ArrayList<Integer>();
			in.readList(allProfilesIds, Integer.class.getClassLoader());
		} else
		{
			allProfilesIds = null;
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
		if (allProfilesIds == null)
		{
			dest.writeByte((byte) (0x00));
		} else
		{
			dest.writeByte((byte) (0x01));
			dest.writeList(allProfilesIds);
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
