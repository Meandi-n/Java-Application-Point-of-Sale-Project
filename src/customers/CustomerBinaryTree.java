package customers;
/**
 * This class contains the customer binary tree.
 * Currently not implemented
 * @author Ryan
 * @version 1.0
 */
public class CustomerBinaryTree extends datastructures.BinaryTree implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	//customer list name - helps if we have multiple customer lists
	@SuppressWarnings("unused")
	private String customerListName = "";
	/**
	 * This is the constructor.
	 * @param name
	 */
	public CustomerBinaryTree(String name)
	{
		super();
		this.customerListName = name;
	}//END constructor
	/**
	 * This function adds a customer to the tree
	 * @param String name
	 */
	public void addCustomer(String name)
	{
		Customer newCustomer = new Customer(name);
		this.addNodeToTree(newCustomer, getKey(newCustomer));
	}//END addCustomer
	/**
	 * This function removes a customer to the tree
	 * @param String name
	 */
	public void removeCustomer(String name)
	{
		int key = this.getKey(name);
		this.removeNode(key);
	}//END removeCustomer
	/**
	 * Find customer from name. Will return NULL if customer cannot be found.
	 * @param name String
	 * @return Customer
	 */
	public Customer findCustomer(String name)
	{
		return (Customer)this.getNode(this.getKey(name));
	}//END findCustomer
	/**
	 * This function takes the customerName and returns the key used to generate the binary tree
	 * @param customerName
	 * @return
	 */
	private int getKey(Customer customer)
	{
		int key = 0;
		for(int i = 0; i < customer.customerName.length(); i++)
			key += (int)customer.customerName.charAt(i);
		return key;
	}//END getKey
	/**
	 * This function gets the key of a customer based on name input.
	 * @param name
	 * @return
	 */
	private int getKey(String name)
	{
		int key = 0;
		for(int i = 0; i < name.length(); i++)
			key += (int)name.charAt(i);
		return key;
	}//END getKey
}//END class
