package com.webscrapingclient.json.map.profile;

/**
 * @author Vanessa Classe per la definizione degli attributi relativi al profilo
 *         utente in base a cui verranno recuperati i poi ordinati. Nel momento
 *         in cui un utente intende visualizzare gli attributi di un particolare
 *         profilo scelto dallo spinner della MainActivity, questa classe viene
 *         utilizzata per costruire un oggetto a partire dal recupero del Json
 *         correlato, ottenuto dalla chiamata all'API REST ../profile/id e dal
 *         parsing dello stesso
 *
 */

public class CommercialProfile
{

	public CommercialProfile(int id, int isMiser, String favouriteCuisine,
			int isDemanding, double isDemandingPercentage,
			double isMiserPercentage, Position lastPosition) {
		super();
		this.id = id;
		this.isMiser = isMiser;
		this.favouriteCuisine = favouriteCuisine;
		this.isDemanding = isDemanding;
		this.isDemandingPercentage = isDemandingPercentage;
		this.isMiserPercentage = isMiserPercentage;
		this.lastPosition = lastPosition;
	}

	private int id;
	private int isMiser;
	private String favouriteCuisine;
	private int isDemanding;
	private double isDemandingPercentage;
	private double isMiserPercentage;
	private Position lastPosition;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public double getIsMiserPercentage()
	{
		return isMiserPercentage;
	}

	public void setIsMiserPercentage(double isMiserPercentage)
	{
		this.isMiserPercentage = isMiserPercentage;
	}

	public Position getLastPosition()
	{
		return lastPosition;
	}

	public void setLastPosition(Position lastPosition)
	{
		this.lastPosition = lastPosition;
	}

	public double getIsDemandingPercentage()
	{
		return isDemandingPercentage;
	}

	public void setIsDemandingPercentage(double isDemandingPercentage)
	{
		this.isDemandingPercentage = isDemandingPercentage;
	}

	public int getIsDemanding()
	{
		return isDemanding;
	}

	public void setIsDemanding(int isDemanding)
	{
		this.isDemanding = isDemanding;
	}

	public String getFavouriteCuisine()
	{
		return favouriteCuisine;
	}

	public void setFavouriteCuisine(String favouriteCuisine)
	{
		this.favouriteCuisine = favouriteCuisine;
	}

	public int getIsMiser()
	{
		return isMiser;
	}

	public void setIsMiser(int isMiser)
	{
		this.isMiser = isMiser;
	}

	@Override
	public String toString()
	{
		return " Profile ID = " + id + "\n Miser = " + isMiser + "\n Favourite Cuisine = " + favouriteCuisine + "\n Demanding = " + isDemanding
				+ "\n Demanding Percentage = " + isDemandingPercentage + "\n Miser Percentage = " + isMiserPercentage + "\n Last Position = "
				+ lastPosition;
	}

}
