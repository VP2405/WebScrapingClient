/**
 * 
 */
package com.webscrapingclient.json.map.orderedlist;

/**
 * @author Vanessa Classe esterna relativa al Json contenente la lista ordinata dei poi
 * relativi al profilo selezionato e recuperata tramite chiamata all'API REST 
 * /scorci/poi/profile/id
 *
 */
public class OrderedListJson
{

	private int status;
	private String description;
	private OrderedListMap map;
	
	/**
	 * 
	 */
	public OrderedListJson()
	{
		// TODO Auto-generated constructor stub
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public OrderedListMap getMap()
	{
		return map;
	}

	public void setMap(OrderedListMap map)
	{
		this.map = map;
	}

}
