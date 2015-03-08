package com.webscrapingclient.utils;

public class CommercialProfile {
	
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
		return " id=" + id + ",\n isMiser=" + isMiser + ",\n favouriteCuisine=" + favouriteCuisine + ",\n isDemanding=" + isDemanding
				+ ",\n isDemandingPercentage=" + isDemandingPercentage + ",\n isMiserPercentage=" + isMiserPercentage + ",\n lastPosition=" + lastPosition;
	}

	
	
}

