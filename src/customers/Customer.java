package customers;
/**
 * This handles customer infromation
 * Currently not implemented. 
 * @author Ryan
 * @version 1.0
 */
public class Customer implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	public String customerName;
	public double totalPurchases;
	/**
	 * Constructor
	 */
	public Customer(String name)
	{
		this.customerName = name;
	}//END constructor
}//END class