package datastructures;
/**
 * This is an exception thrown by linkedlist when removing an item 
 * from an empty list. 
 * @author Ryan
 * @version 1.0
 */
public class EmptyListException extends Exception implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	public EmptyListException(String s)
	{
		super(s);
	}
	public void printMessage()
	{
		System.out.println(super.getMessage());
	}
}
