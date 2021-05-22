package itemdictionary;
/**
 * This class holds the itemdictionary in the form of a linkedlist of category classes. 
 * The category class holds a 2d array of item entries and item names, as well as a string 
 * for the category name. 
 * 
 * This could likely be streamlined in future by combining the item dictionary and library 
 * into a datastructure that can accomodate both needs. 
 * @author Ryan
 * @version 1.0
 */
public class ItemLibrary implements java.io.Serializable
{
	private datastructures.Category category;
	private datastructures.LinkedList library;
	private int categoryPosition = 0;
	private final int MAX_CATEGORY_ENTRIES = 30;
	/**
	 * Constructor.  Initializes linkedlist, sets current category to new category object. 
	 */
	public ItemLibrary()
	{
		this.library = new datastructures.LinkedList();
		this.newCategory(); //initialise new category for first instance
	}//END constructor
	/**
	 * Method newCategory,  This opens a new category. Maximum category entries are 30. 
	 */
	public void newCategory()
	{
		this.categoryPosition = 0;
		this.category = new datastructures.Category();
		this.category.array = new Object[MAX_CATEGORY_ENTRIES][2]; //new blank category.
	}//END newCategory
	/**
	 * This adds a category entry to the itemlibrary. If we try add more than 30 entries to
	 * one category, we simply print a warning window. and stop adding to that category. 
	 * @param key (String)
	 * @param desc (String)
	 * @param price (double)
	 * @param quantity (double)
	 */
	public void addCategoryEntry(String key, String desc, double price, double quantity)
	{
		if(categoryPosition < 30) //if we exceed maximum we just skip.
		{
			itemdictionary.Entry newEntry = new itemdictionary.Entry(desc, price, quantity);
			category.array[categoryPosition][0] = key; 
			category.array[categoryPosition][1] = newEntry;
			categoryPosition ++;
		}
		else
		{
			@SuppressWarnings("unused")
			graphicalinterface.WarningWindow tooManyEntries 
				= new graphicalinterface.WarningWindow("Too many category entires. Shortening");
		}
	}//END addCategoryEntry
	/**
	 * This adds a category to the list. 
	 * @param name
	 */
	public void addCategoryToList(String name)
	{
		category.name = name;
		library.addNode(category);
	}//END addCategoryToList
	/**
	 * This method outputs the linkedlist as an array
	 * @return Category[] 
	 */
	public datastructures.Category[] toArray()  //might produce an error
	{
		datastructures.Category[] array = new datastructures.Category[50]; //5 categories 10 elements each - make constants in future much neater
		library.pointerToHead();
		datastructures.Category contents;
		for(int i = 0; library.getCurrentNode() != null && (contents = (datastructures.Category)library.getCurrentNodeContents()) != null; i++)
		{
			array[i] = contents; //contents is an array of elements actually a 3D array
			library.setCurrentNode(library.getCurrentNode().getNext());
		}
		return array;
	}//END toArray
}//END class
