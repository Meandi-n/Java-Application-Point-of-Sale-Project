package datastructures;
/**
 * This class implements a double ended, doubly linked list.
 * 
 * @author Ryan
 * @version 1.0
 */
/* PUBLIC METHODS IN CLASS
 * getLastNode <- void 		
 * findNode <- Object contents 		throws EmptyListException if head is NULL
 * addNode <- Object contents 		
 * removeLastNode <- void
 * removeHead <- void
 * removeNode <- Object contents
 * toString <- void
 * toArray <- void
 */
public class LinkedList implements java.io.Serializable
{
	protected ListNode head;
	private ListNode pointerNode;
	private int listLength;
	/*
	 * PUBLIC ACCESSOR METHODS
	 */
	/**
	 * This method returns the last node contents without removing the node
	 * @return Object 
	 */
	public Object getLastNode()
	{
		return pointerNode.getContents();
	}//END getLastNode
	/**
	 * This method returns the contents of the current Node. 	
	 * @return Object
	 */
	public Object getCurrentNodeContents()
	{
		return this.pointerNode.getContents();
	}//END getCurrentNodeContents
	/**
	 * Returns the current Node
	 * @return ListNode
	 */
	public ListNode getCurrentNode()
	{
		return this.pointerNode;
	}//end GetCurrentNode
	/*
	 * PUBLIC MUTATOR METHODS
	 */
	/**
	 * This method adds a node to the linked list.
	 * @param contents
	 */
	public void addNode(Object contents)
	{
		if(this.head == null)
		{
			pointerNode = new ListNode(null, null, contents);
			this.head = pointerNode;
		}
		else
		{
			pointerNode.setNext(new ListNode(pointerNode, null, contents));
			pointerNode = pointerNode.getNext();
		}
		listLength ++;
	}//END addNode
	/**
	 * This method removes the last node from the linkedList. The pointerNode
	 * is never moved aside from removing or adding a node, hence it is the last node
	 * in the LinkedList.
	 */
	public void removeLastNode()
	{
		if(this.head != null && this.head.getNext() != null)
		{
			pointerNode.getPrevious().setNext(null);
			pointerNode = pointerNode.getPrevious();
		}
		else
			this.head = null;
		listLength --;
	}//END removeLastNode
	/**
	 * This method removes the read of the linkedList
	 */
	public void removeHead()
	{
		if(this.head != null && this.head.getNext() != null)
		{
			this.head.getNext().setPrevious(null);
			this.head = this.head.getNext();
		}
		else
			this.head = null;
		listLength --;
	}//END removeHead
	
	/**
	 * This method removes a node based upon finding the node with the matching contents
	 * of the input parameter 
	 * @param contents
	 */
	public ListNode removeNode(Object contents)
	{
		ListNode removedNode = null;
		try
		{
			removedNode = this.findNode(contents); //this method returns the node with matching contents
			System.out.println(removedNode.toString());
			if(removedNode.getPrevious() != null && removedNode.getNext() != null) //if we are in the middle of the list
			{
				removedNode.getPrevious().setNext(removedNode.getNext());
				removedNode.getNext().setPrevious(removedNode.getPrevious());
				System.out.println("removeNode: Node removed");
			}
			else if(removedNode.getPrevious() == null) //if we are the head of list.
			{
				this.removeHead();
				System.out.println("removeNode:  head removed");
			}
			else if(removedNode.getNext() == null) //if we are at the tail
			{
				this.removeLastNode();
				System.out.println("removeNode:  tail removed");
			}
			listLength --;
		}catch(datastructures.EmptyListException e) {
			e.printStackTrace();
			e.printMessage();
			removedNode = null;
		}catch(NullPointerException e) {
			System.out.println("removeNode: Node not found");
			removedNode = null;
		}
		return removedNode;
	}//END removeNode
	public ListNode removeNode(ListNode removedNode)
	{
		try
		{
			if(removedNode.getPrevious() != null && removedNode.getNext() != null) //if we are in the middle of the list
			{
				removedNode.getPrevious().setNext(removedNode.getNext());
				removedNode.getNext().setPrevious(removedNode.getPrevious());
				System.out.println("removeNode ListNode: Node removed");
			}
			else if(removedNode.getPrevious() == null) //if we are the head of list.
			{
				this.removeHead();
				System.out.println("removeNode ListNode:  head removed");
			}
			else if(removedNode.getNext() == null) //if we are at the tail
			{
				this.removeLastNode();
				System.out.println("removeNode ListNode:  tail removed");
			}
			listLength --;
		}catch(NullPointerException e) {
			System.out.println("removeNode ListNode: Node not found");
			removedNode = null;
		}
		return removedNode;
	}//END removeNode
	/**
	 * This method sets the pointerNode (used to traverse list) to the head of the list. 
	 */
	public void pointerToHead()
	{
		this.pointerNode = this.head;
	}//END pointerToHead
	/**
	 * This method sets the currentNode to the input Node
	 * @param inNode
	 */
	public void setCurrentNode(ListNode inNode)
	{
		this.pointerNode = inNode;
	}//END setCurrentNode
	/*
	 * PUBLIC toString and toArray METHODS
	 */
	/**
	 * This method outputs the linked list as a String
	 * 
	 * @return String
	 */
	public String toString()
	{
		ListNode traversalNode = head;
		Object currentContents;
		String output = "";
		while(traversalNode != null)
		{
			currentContents = traversalNode.getContents();
			try { output += currentContents.toString(); //may throw error if object of contents is NULL or primitive
			}catch(NullPointerException e) {
				e.printStackTrace();
				output += "";
			}
			traversalNode = traversalNode.getNext();
		}
		return output;
	}//END toString
	/**
	 * This method is very similar to the toString method, but it returns 
	 * each listNode as an element of an array.
	 * 
	 * @return String[]
	 */
	public String[] toArray()
	{
		ListNode traversalNode = head;
		Object currentContents;
		String[] output = new String[listLength];
		for(int i = 0; traversalNode != null; i++)
		{
			currentContents = traversalNode.getContents();
			try{ output[i] = currentContents.toString(); //may throw exception
			} catch(NullPointerException e) { //just set output to null if exception thrown
				e.printStackTrace();
				output[i] = null;
			}
			traversalNode = traversalNode.getNext();
		}
		return output;
	}//END toArray
	/*
	 * PRIVATE METHODS
	 */
	/**
	 * This method finds a node that has matching contents (object), returning the node it found.
	 * If the list is empty, meaning head is null, an EmptyListException is thrown
	 * 
	 * @param contents
	 * @return ListNode
	 * @throws EmptyListException
	 */
	private ListNode findNode(Object contents) throws datastructures.EmptyListException
	{
		ListNode currentNode = head;
		ListNode returnNode = null;
		
		if(head != null)
		{
			System.out.println("head isnt null");
			while(currentNode != null)
			{
				System.out.println("comparing: " + currentNode.getContents() + " =?= " + contents);
				if(currentNode.getContents().toString().equals(contents.toString()))
					returnNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		else
			throw new datastructures.EmptyListException("LinkedList.findNode method found an empty list");
		return returnNode;
	}//END findNode
}//END class
