/**
 * 
 */
package com.webscrapingclient.utils;

/**
 * @author Vanessa
 *
 */
public class Map
{
	private CommercialProfile commercialprofile;
	

	public CommercialProfile getCommercialProfile()
	{
		return commercialprofile;
	}

	public void setCommercialProfile(CommercialProfile commercialProfile)
	{
		this.commercialprofile = commercialProfile;
	}

	@Override
	public String toString()
	{
		return "Map [commercialprofile=" + commercialprofile + "]";
	}
	
	

}
