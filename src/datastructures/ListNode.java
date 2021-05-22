package datastructures;
/**
 * this class is a listNode container for the Linked List class
 * 
 * @author Ryan
 * @version 1.0
 */
public class ListNode implements java.io.Serializable
{
	private ListNode nextNode;
	private ListNode previousNode;
	private Object contents;
	/**
	 * Constructor 
	 * 
	 * @param prev ListNode
	 * @param next ListNode
	 * @param cont ListNode
	 */
	public ListNode(ListNode prev, ListNode next, Object cont)
	{
		this.nextNode = next;
		this.previousNode = prev;
		this.contents = cont;
	}//END constructor
	/*
	 * GETTERS
	 */
	/**
	 * Returns next node 
	 * @return ListNode
	 */
	public ListNode getNext()
	{
		return this.nextNode;
	}//END getNext
	/**
	 * Returns previous node
	 * @return ListNode
	 */
	public ListNode getPrevious()
	{
		return this.previousNode;
	}//END getPrevious
	/**
	 * Returns contents of node as object
	 * @return Object
	 */
	public Object getContents()
	{
		return this.contents;
	}//END getContents
	/*
	 * Setters
	 */
	/**
	 * This method sets the next node
	 * @param next
	 */
	public void setNext(ListNode next)
	{
		this.nextNode = next;
	}//END setNext
	/**
	 * This method sets the previous node
	 * @param previous
	 */
	public void setPrevious(ListNode previous)
	{
		this.previousNode = previous;
	}//END setPrevious
	/**
	 * This method sets the contents of node
	 * @param in
	 */
	public void setContents(Object in)
	{
		this.contents = in;
	}//END setContents
	/**
	 * This method returns the value of the .toString() of contents
	 */
	public String toString()
	{
		String tostring;
		if(contents != null)
			tostring = contents.toString();
		else
			tostring = "";
		return tostring;
	}//END toString
}//END class
