package datastructures;
/***
 * Generic Binary Tree Class
 * Currently unimplented
 * @author Ryan
 * @Version 1.0
 */
public class BinaryTree implements java.io.Serializable
{
	private TreeNode root;
	/**
	 * Constructor method
	 */
	public BinaryTree()
	{
		this.root = new TreeNode();
	}//END constructor
	/*
	 * PUBLIC METHODS
	 */
	/**
	 * This method adds a node to the tree based off of the contents
	 * and the key.
	 * @param contents
	 * @param key
	 */
	public void addNodeToTree(Object contents, int key)
	{
		TreeNode newNode = new TreeNode(contents, key);
		TreeNode currentNode = this.root;
		while(newNode.parent == null && !this.root.equals(newNode))
		{
			if(this.root == null)
			{
				this.root = newNode;
			}
			else if(currentNode.key > newNode.key)
			{
				if(currentNode.rightChild == null)
				{
					currentNode.rightChild = newNode;
					newNode.parent = currentNode;
				}
				currentNode = currentNode.rightChild;
			}
			else if(currentNode.key <= newNode.key)
			{
				if(currentNode.leftChild == null)
				{
					currentNode.leftChild = newNode;
					newNode.parent = currentNode;
				}
				currentNode = currentNode.leftChild;
			}
		}
	}//END addNodeToTree
	/**
	 * This method returns and removes the contents of the root of the tree.
	 * @return contents Object
	 */
	public Object removeRoot()
	{
		Object returnObject = root.contents;
		this.root.key = 0; //set key to 0 (all other keys above 0)
		this.purgeIllegalSubTree(this.root);
		this.removeLeftMostNode(); //remove our 0 key from tree 
		return returnObject;
	}//END removeRoot
	/**
	 * This method gets the contents of a node with the specified key.
	 * If the key does not correlate to a specified node, then NULL is returned.
	 * @param key
	 * @return contents Object
	 */
	public Object getNode(int key)
	{
		Object contents = null;
		TreeNode foundNode = findNode(key);
		if(foundNode != null)
			contents = foundNode.contents;
		return contents;
	}//END getNode
	/**
	 * This method removes a node from the tree with the specified key. 
	 * If key is not present we do nothing.
	 * @param key
	 */
	public void removeNode(int key)
	{
		TreeNode removalNode = findNode(key);
		if(removalNode != null)
		{
			removalNode.key = 0;
			this.purgeIllegalSubTree(removalNode);
			this.removeLeftMostNode(); //remove our 0 node.
		}
	}//END removeNode
	/*
	 * PRIVATE METHODS
	 */
	/**
	 * This method removes the left most node of the tree.  This is important when removing a node
	 * from the tree. 
	 * We set the node key to 0 (all other keys are above 0), which guarantees the removed node is the 
	 * leftmost node.  This method can then remove that node.
	 */
	private void removeLeftMostNode()
	{
		TreeNode currentNode = this.root;
		while(currentNode != null && currentNode.leftChild != null)
		{
			currentNode = currentNode.leftChild;
		}
		currentNode.parent.leftChild = null;
	}//END removeLeftMostNode
	/**
	 * This method makes sure the binary tree is legal starting with the start node of the tree (root for 
	 * whole tree).
	 * @param startNode
	 */
	private void purgeIllegalSubTree(TreeNode startNode)
	{
		TreeNode tempNode;
		if(startNode.key < startNode.leftChild.key)
		{
			tempNode = startNode;
			startNode = startNode.leftChild;
			startNode.leftChild = tempNode;
			purgeIllegalSubTree(startNode.leftChild);
		}
		if(startNode.key > startNode.rightChild.key)
		{
			tempNode = startNode;
			startNode = startNode.rightChild;
			startNode.rightChild = tempNode;
			purgeIllegalSubTree(startNode.rightChild);
		}
	}//END purgeIllegalSubTree
	/**
	 * Returns the TreeNode with the key matching input parameter. 
	 * If no TreeNode with key is found, a NULL TreeNode class is returned.
	 * @param key
	 * @return TreeNode 
	 */
	private TreeNode findNode(int key)
	{
		TreeNode currentNode = this.root;
		while(currentNode != null && currentNode.key != key) //stop when we find the key
		{
			if(currentNode.key < key)
				currentNode = currentNode.rightChild;
			else if(currentNode.key > key)
				currentNode = currentNode.leftChild;
		}
		return currentNode; //will return NULL if not in tree
	}//END findNode
	/***
	 * This is an internal private class for the binary tree node.
	 * @author Ryan
	 * @version 1.0
	 */
	private class TreeNode
	{
		public Object contents;
		public int key;
		
		public TreeNode parent;
		public TreeNode rightChild;
		public TreeNode leftChild;
		
		public TreeNode(Object cont, int k)
		{
			this.contents = cont;
			this.key = k;
		}
		
		public TreeNode()
		{
			this.contents = null;
			this.key = 0;
		}
		
		public boolean equals(TreeNode in)
		{
			boolean equal = false;
			if(in.key == this.key)
				equal = true;
			return equal;
		}
	}//END class TreeNode
}//END class