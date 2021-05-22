package itemdictionary;
import java.util.Dictionary;
import java.util.Hashtable;
/**
 * This class contains the dictionary for item codes.
 * It links code (key) to the item class which contains the default
 * price quantity and description of the item. 
 * 
 * @author email
 * @version 1.0
 */
public class ItemDictionary implements java.io.Serializable
{
	private Dictionary<String, Entry> itemDictionary = new Hashtable<String, Entry>();
	/*
	 * GETTERS
	 */
	/**
	 * Get method for dictionary.
	 * @return Dictionary dict
	 */
	public Dictionary<String, Entry> getDictionary()
	{
		return this.itemDictionary;
	}//END getDictionary
	/**
	 * This method obtains the entry class from the key input. It filters the key input to
	 * ensure it is legal. Returns NULL if input key is NULL.
	 * 
	 * @param key
	 * @return Entry 
	 */
	public Entry getEntry(String key)
	{
		Entry entry = null;
		if(key != null)
			entry = this.itemDictionary.get(key);
		return entry;
	}//END getEntry
	/**
	 * This method obtains the default price of the key associated with the entry.
	 * 
	 * @param key
	 * @return price Double
	 */
	public double getEntryDefaultPrice(String key)
	{
		Entry entry; Double price = 0.0;
		if((entry = getEntry(key)) != null)
			price = entry.default_price;
		return price;
	}//END getEntryDefaultPrice
	/**
	 * This method obtains the default quantity of the key associated with entry.
	 * 
	 * @param key
	 * @return quantity Double
	 */
	public double getEntryDefaultQuantity(String key)
	{
		Entry entry; Double quantity = 0.0;
		if((entry = getEntry(key)) != null)
			quantity = entry.default_quantity;
		return quantity;
	}//END getEntryDefaultPrice
	/**
	 * This method obtains the default description of the key associated with the entry.
	 * 
	 * @param key
	 * @return
	 */
	public String getEntryDefaultDescription(String key)
	{
		Entry entry; String desc = "UNKNOWN DICTIONARY ENTRY";
		if((entry = getEntry(key)) != null)
			desc = entry.default_description;
		return desc;
	}//END getEntryDefaultDescription
	/*
	 * SETTERS
	 */
	/**
	 * This method creates a new entry based on inputs key, desc, price, and quantity.
	 * It then adds it to the dictionary with the key provided.
	 * 
	 * @param key
	 * @param description
	 * @param price
	 * @param quantity
	 */
	public void setEntry(String key, String description, double price, double quantity)
	{		
		Entry newEntry = new Entry(description, price, quantity);
		this.itemDictionary.put(key, newEntry);
	}//END setEntry
}//END class
