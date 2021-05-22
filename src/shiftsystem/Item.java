package shiftsystem;
/**
 * This class holds the 'item' data.  
 * The Cart class holds a linked list of items, 
 * and the shift class IS a linked list of carts. 
 * The item class holds information about price, quantity, description
 * and key of an item.
 * @author Ryan
 * @version 1.0
 */
public class Item implements java.io.Serializable
{
	//item variables
	private String description; 
	private double unit_price;
	private double quantity;
	private String itemKey; 
	/*the item Key corresponds to a string key Configured on startup 
	 * this key corresponds to a dictionary entry that has defauly values.
	 * We may also use this key to gather statistical data about sale quantities 
	 * for each item type at EOD */
	private itemdictionary.ItemDictionary currentDictionary;
	/**
	 * Constructor, overloaded. Price, quantity, description set to
	 * default values.
	 * (defuault as per item dictionary)
	 * @param dict
	 * @param key
	 */
	public Item(itemdictionary.ItemDictionary dict, String key)
	{
		this.currentDictionary = dict;
		this.itemKey = key;
		this.description = currentDictionary.getEntryDefaultDescription(key);
		this.quantity = currentDictionary.getEntryDefaultQuantity(key);
		this.unit_price = currentDictionary.getEntryDefaultPrice(key);
	}//END constructor
	/**
	 * Constructor, overloaded.  sets nothing to default values.
	 * (default as per item dictionary)
	 * @param key
	 * @param desc
	 * @param price
	 * @param quantity
	 */
	public Item(String key, String desc, double price, double quantity)
	{
		this.itemKey = key;
		setDescription(desc);
		setUnitPrice(price);
		setQuantity(quantity);
	}//END constructor
	/*
	 * SETTERS
	 */
	/**
	 * Sets the description
	 * @param in
	 */
	public void setDescription(String in)
	{
		if(in != null)
			this.description = in;
	}//END setDescription
	/**
	 * Sets the price
	 * @param price
	 */
	public void setUnitPrice(double price)
	{
		this.unit_price = price;
	}//END setUnitPrice
	/**
	 * Sets the unit quantity
	 * @param quantity
	 */
	public void setQuantity(double quantity)
	{
		this.quantity = quantity;
	}//END setQuantity
	/**
	 * toString method 
	 * @return String
	 */
	public String getItemKey()
	{
		return this.itemKey;
	}
	public String toString()
	{
		return this.itemKey + "," + this.description 
				+ "," + this.unit_price + ":" + this.quantity + "\n";
	}//END toString
	
	public boolean equals(Item in)
	{
		if(this.itemKey.equals(in.getItemKey()) &&
		    this.description.equals(in.description) &&
		    this.unit_price == in.unit_price &&
		    this.quantity == in.quantity)
			return true;
		else
			return false;
	}
}//END class
