package shiftsystem;
import java.util.Date;
import java.util.regex.PatternSyntaxException;
import java.lang.Math;
/**
 * This class is a simple POJO for the cart 
 * The cart holds items (it is a transaction), 
 * The cart holds a linked list of Items.  
 * The shift class IS a linked list of carts. 
 * 
 * @author Ryan
 * @version 1.0
 */
public class Cart implements java.io.Serializable
{
	//private cart details
	private double cartTotal;
	private int cartID;
	private double cartQuantity;
	private Enum.PAYMENTTYPE paymentType;
	@SuppressWarnings("unused") //not implemented yet
	private customers.Customer customer; // only utilised in customer credit transaction
	private Date date;
	private String date_string;
	//item dictionary and cart list (linkedlist)
	private itemdictionary.ItemDictionary dict;
	private datastructures.LinkedList cartlist;
	/**
	 * Constructor, sets cartID to random integer, intialises all cart details
	 * @param dict_in
	 */
	public Cart(itemdictionary.ItemDictionary dict_in)
	{
		cartlist = new datastructures.LinkedList();
		this.cartID = (int)(Math.random() * (99999999 - 11111111 + 1) + 11111111); // from 1111 1111 to 9999 9999
		this.date = new Date();
		this.date_string = date.toString();
		this.dict = dict_in;
		this.cartTotal = this.getCartTotal();
	}//END Constructor
	/*
	 * SETTERS
	 */
	/**
	 * This sets the payment type *must be of type enum
	 * @param type Enum.PAYMENTTYPE
	 */
	public void setPaymentType(Enum.PAYMENTTYPE type)
	{
		if(type != null)
			this.paymentType = type;
	}//END setPaymentType
	/**
	 * This method sets the customer (only utilized in customer credit transactions) to 
	 * the input customer. This is not implemented in headless interface.
	 * @param in
	 */
	public void setCustomer(customers.Customer in)
	{
		this.customer = in;
	}//END setCustomer
	/**
	 * This method sets the item dictionary 
	 * @param dict ItemDictionary
	 */
	public void setItemDictionary(itemdictionary.ItemDictionary dict)
	{
		if(dict != null)
			this.dict = dict;
	}//END setItemDictionary
	/**
	 * This sets the cart ID, no requirements.
	 * @param id Integer
	 */
	public void setCartID(int id)
	{
		this.cartID = id;
	}//end setCartID
	/*
	 * GETTERS
	 */
	/**
	 * This method returns the item dictionary
	 * @return dict ItemDictionary
	 */
	public itemdictionary.ItemDictionary getItemDictionary()
	{
		return this.dict;
	}//END getItemDictionary
	/**
	 * Returns the local cart ID
	 * @return cartID Integer
	 */
	public int getCartID()
	{
		return this.cartID;
	}//END getCartID
	/**
	 * This method returns the cartQuantity (items in the cart).
	 * @return cartQuantity Double
	 */
	public double getCartQuantity()
	{
		return this.cartQuantity;
	}//END getCartQuantity
	/**
	 * This method returns the date string. 
	 * @return date_string String
	 */
	public String getDateString()
	{
		return this.date_string;
	}//END getDateString
	
	public Date getDate()
	{
		return this.date;
	}
	
	/**
	 * This method returns the cart total (updating first)
	 * @return cartTotal Double
	 */
	public double getCartTotal()
	{
		this.cartTotal = this.updateCartTotal(); //returns 0 if error
		return this.cartTotal;
	}//END getCartTotal
	/**
	 * This method returns the payment type
	 * @return Enum.PAYMENTTYPE
	 */
	public Enum.PAYMENTTYPE getPaymentType()
	{
		return this.paymentType;
	}//END getPaymentType
	/* 
	 * PUBLIC METHODS
	 */
	/**
	 * This method adds an item based on a string key. It assigns the default
	 * description, quantity, price.
	 * @param key
	 */
	public void addItem(String key)
	{
		Item newItem = new Item(this.dict, key);
		cartlist.addNode(newItem);
		this.cartTotal = this.getCartTotal();
	}//END addItem
	/**
	 * This method adds an item with price and quantity, and a description that
	 * defaults to dictionary lookup value.
	 * @param key
	 * @param price
	 * @param quantity
	 */
	public void addItem(String key, double price, double quantity)
	{
		Item newItem = new Item(this.dict, key);
		newItem.setQuantity(quantity);
		newItem.setUnitPrice(price);
		cartlist.addNode(newItem);
		this.cartTotal = this.getCartTotal();
	}//END addItem
	/**
	 * This method adds an item with all item values specified.
	 * @param key
	 * @param price
	 * @param quantity
	 * @param description
	 */
	public void addItem(String key, double price, double quantity, String description)
	{
		Item newItem = new Item(key, description, price, quantity);
		cartlist.addNode(newItem);
		this.cartTotal = this.getCartTotal();
	}//END addItem
	/**
	 * This method removes the last node from the linked list
	 */
	public void removeLastItem()
	{
		cartlist.removeLastNode();
		this.cartTotal = this.getCartTotal();
	}//END removeLastItem
	/**
	 * This method removes the first node from the linked list
	 */
	public void removeFirstItem()
	{
		cartlist.removeHead();
		this.cartTotal = this.getCartTotal();
	}//END removeFirstItem
	
	public void removeItem(String keycode, String desc, double price, double qty)
	{
		shiftsystem.Item removedItem = new shiftsystem.Item(keycode, desc, price, qty);
		cartlist.removeNode(removedItem);
	}
	/**
	 *  toString method, prints cart total and adds payment type to end of each item line. 
	 */
	public String toString()
	{
		return cartlist.toString().replaceAll("\n", "," +paymentType+" \n" );
	}//END toString
	/*
	 * PRIVATE METHODS
	 */
	/**
	 * This method updates the cart total by traversing the cart linked list.
	 * Will return a total of 0.0 if it encounters an exception reading the linkedlist.
	 * @return doubletotal Double
	 */
	private double updateCartTotal()
	{
		String total; double doubletotal = 0.0;
		try {
			String cartString = cartlist.toString(); 
			if(cartString != null && !cartString.equals(""))
			{
				String[] items = cartString.split("\n",0);
				for(int i = 0; i < items.length; i++)
				{
					total = items[i].split(",",0)[2];
					doubletotal += (Double.parseDouble(total.split(":",0)[0]) *
									Double.parseDouble(total.split(":",0)[1]));
				}
			}
		}catch(NullPointerException e) { //occurs if cartlist is NULL.
			e.printStackTrace();
		}catch(PatternSyntaxException e) { //occurs on split string line of total and cartString
			e.printStackTrace();
		}catch(NumberFormatException e) { //occurs if parseDouble does not have numeric input
			e.printStackTrace();
		}
		return doubletotal;
	}//END updateCartTotal
	/**
	 * Equals method
	 */
	public boolean equals(Cart in)
	{
		if(in.getCartID() == this.cartID)
			return true;
		else
			return false;
	}
}//END CLASS
