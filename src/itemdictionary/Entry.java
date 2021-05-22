package itemdictionary;
/**
 * This class holds the information for an Entry to the dictionary that
 * holds the item code informations.
 * 
 * @author Ryan
 * @version 1.0
 */
public class Entry implements java.io.Serializable
{
	public double default_price;
	public double default_quantity;
	public String default_description;
	/**
	 * This method is the constructor for entry.
	 * 
	 * @param desc
	 * @param price
	 * @param quantity
	 */
	public Entry(String desc, double price, double quantity)
	{
		this.default_description = desc;
		this.default_price = price;
		this.default_quantity = quantity;
	}//END constructor
}//END Entry
