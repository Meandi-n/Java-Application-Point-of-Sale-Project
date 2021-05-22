package shiftsystem;
import java.time.LocalDate;
import java.time.LocalTime;

import datastructures.EmptyListException;
import datastructures.ListNode;
/**
 * This class is an extension of the linked list class. 
 * it adds abstract functions for creating new carts and amending them
 * to the linked list, as well as defining an item dictionary, 
 * shift details like start and end time, and keeping track of the current
 * working cart. 
 * 
 * @author Ryan
 * @version 1.0
 */
public class Shift extends datastructures.LinkedList implements java.io.Serializable
{
	//shift details
	private Cart currentCart;
	private itemdictionary.ItemDictionary dict;
	private LocalTime shiftStartTime;
	private LocalDate shiftStartDate;
	
	/**
	 * Constructor.
	 * @param dict
	 */
	public Shift(itemdictionary.ItemDictionary dict)
	{
		shiftStartTime = java.time.LocalTime.now();
		shiftStartDate = java.time.LocalDate.now();
		this.dict = dict;
	}//END constructor
	/*
	 * SETTERS
	 */
	/**
	 * This sets the dictionary used by the shift
	 * @param dict
	 */
	public void setShiftDictionary(itemdictionary.ItemDictionary dict)
	{
		if(dict != null)
			this.dict = dict;
	}//END setShiftDictionary
	/*
	 * GETTERS
	 */
	/**
	 * getShiftDictionary returns the item dictionary used by the shift.
	 * @return
	 */
	public itemdictionary.ItemDictionary getShiftDictionary()
	{
		return this.dict;
	}//END getShiftDictionary
	
	/**
	 * This method returns the current cart 
	 * @return currentCart
	 */
	public shiftsystem.Cart getCurrentCart()
	{
		return this.currentCart;
	}//END getCurrentCart
	/**
	 * This method returns the shiftStartTime
	 * @return LocalTime shiftStartTime
	 */
	public LocalTime getShiftStartTime()
	{
		return this.shiftStartTime;
	}//END getShiftStartTime
	/**
	 * This method returns the shift start date. 
	 * @return LocalDate shiftStartDate
	 */
	public LocalDate getShiftStartDate()
	{
		return this.shiftStartDate;
	}//END getShiftStartDate
	/**
	 * This method returns the shift total (eftpos or cash).
	 * @return Double shiftTotal
	 */
	public double getShiftTotal()
	{
		return this.getEFTPOSTotal() + this.getCashTotal();
	}//END getShiftTotal
	/**
	 * This method returns the EFTPOS total
	 * @return Double total
	 */
	public double getEFTPOSTotal()
	{
		datastructures.ListNode traversalNode = this.head; //start from head
		Cart traversalCart = null;
		
		double total = 0.0;
		
		while(traversalNode != null)
		{
			traversalCart = (Cart)traversalNode.getContents();
			if(traversalCart.getPaymentType() == Enum.PAYMENTTYPE.EFTPOS)
			{
				total += traversalCart.getCartTotal();
			}
			traversalNode = traversalNode.getNext();
		}
		
		return total;
	}//END getEFTPOSTotal
	public String getEftposRuleOff()
	{
		datastructures.ListNode traversalNode = this.head; //start from head
		Cart traversalCart = null;
		
		String ruleOff = "";
		
		while(traversalNode != null)
		{
			traversalCart = (Cart)traversalNode.getContents();
			if(traversalCart.getPaymentType() == Enum.PAYMENTTYPE.EFTPOS)
			{
				ruleOff += traversalCart.toString();
			}
			traversalNode = traversalNode.getNext();
		}
		
		return ruleOff;
	}//END getCashTotal
	/**
	 * This method returns the cash total.
	 * @return Double total
	 */
	public double getCashTotal()
	{
		datastructures.ListNode traversalNode = this.head; //start from head
		Cart traversalCart = null;
		
		double total = 0.0;
		
		while(traversalNode != null)
		{
			traversalCart = (Cart)traversalNode.getContents();
			if(traversalCart.getPaymentType() == Enum.PAYMENTTYPE.Cash)
			{
				total += traversalCart.getCartTotal();
			}
			traversalNode = traversalNode.getNext();
		}
		
		return total;
	}//END getCashTotal
	public String getCashRuleOff()
	{
		datastructures.ListNode traversalNode = this.head; //start from head
		Cart traversalCart = null;
		
		String ruleOff = "";
		
		while(traversalNode != null)
		{
			traversalCart = (Cart)traversalNode.getContents();
			if(traversalCart.getPaymentType() == Enum.PAYMENTTYPE.Cash)
			{
				ruleOff += traversalCart.toString();
			}
			traversalNode = traversalNode.getNext();
		}
		
		return ruleOff;
	}//END getCashTotal
	/**
	 * This method returns the cash total.
	 * @return Double total
	 */
	public double getCustomerTotal()
	{
		datastructures.ListNode traversalNode = this.head; //start from head
		Cart traversalCart = null;
		
		double total = 0.0;
		
		while(traversalNode != null)
		{
			traversalCart = (Cart)traversalNode.getContents();
			if(traversalCart.getPaymentType() == Enum.PAYMENTTYPE.CustomerCredit)
			{
				total += traversalCart.getCartTotal();
			}
			traversalNode = traversalNode.getNext();
		}
		
		return total;
	}//END getCashTotal
	public String getCustomerRuleOff()
	{
		datastructures.ListNode traversalNode = this.head; //start from head
		Cart traversalCart = null;
		
		String ruleOff = "";
		
		while(traversalNode != null)
		{
			traversalCart = (Cart)traversalNode.getContents();
			if(traversalCart.getPaymentType() == Enum.PAYMENTTYPE.CustomerCredit)
			{
				ruleOff += traversalCart.toString();
			}
			traversalNode = traversalNode.getNext();
		}
		
		return ruleOff;
	}//END getCashTotal
	/*
	 * PUBLIC METHODS
	 */
	/**
	 * CreateNewCart creates a new cart by adding a node to the linkedlist
	 * and assigning a cart object to the node contents.  The current cart is
	 * then updates to the new cart just created. 
	 */
	public void createNewCart()
	{
		Cart newCart = new Cart(dict);
		this.addNode(newCart);
		this.currentCart = newCart;
	}//END createNewCart
	/**
	 * This method is an abstractor of the removeNode linkedlist
	 * method.  we feed a cartID into the method.  The linkedlist class
	 * equates two nodes as being equal if the node1.equals(node2) is true.
	 * .equals() looks at cartID ONLY.
	 * 
	 * processed refunds may be done cash or cart, but forced refunds (next method)
	 * MUST be done with cash.  note that if we process a refund for a transaction that 
	 * was originally eftpos, we must refund on eftpos or else we will not be ballanced. 
	 * 
	 * @param cartID
	 */
	public Cart processRefund(int cartID)
	{
		ListNode currentNode = this.head;
		ListNode previousNode = null;
		Cart currentCart = null;
		if(this.head != null)
		{
			currentCart = (Cart)currentNode.getContents();
			previousNode = currentNode;
			while(currentNode != null && currentCart.getCartID() != cartID)
			{
				currentCart = (Cart)currentNode.getContents();
				previousNode = currentNode;
				currentNode = currentNode.getNext();
			}
			if(currentCart.getCartID() == cartID)
				super.removeNode(previousNode);
		}
		return currentCart;
	}//END processRefund
	/**
	 * This method forces a refund not be removing a cart from the linkedlist, but by creating a new
	 * cart with a negative quantity in item 1.
	 * @param amount
	 */
	public void forceRefund(double amount)//must always be cash
	{
		if(amount >= 0)
			amount *= -1; //if they input $10 for a refund we correct this to -$10
		currentCart = new shiftsystem.Cart(null);
		currentCart.addItem("REFUND", amount, 1, "Refund");
		currentCart.setPaymentType(shiftsystem.Enum.PAYMENTTYPE.Cash); // forced refunds MUST be done in cash
		this.addNode(currentCart);
	}//END forceRefund
}//END class
