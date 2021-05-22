package main;
import java.io.IOException;
/**
 * Session class handles the current program instance, it allows you to create new shifts,
 * obtain some information about shifts relevant to end of day cash ups, and obtains 
 * non-shift-specific information like company credentials and site of operation.
 * 
 * @author email
 * @version 1.0
 */
/* PUBLIC METHODS IN CLASS
 * getOperationSite <- void				returns "" if operationSite is NULL.
 * getCurrentShift <- void 		
 * getCompanyName <- void				returns "" if company name is NULL.
 * getABN <- void						returns "" if ABN is NULL.  Prints warning if NBN is not 11 characters.
 * getShiftSales <- void 
 * setCompanyName <- String				If the input is NULL, we set the company name to "".
 * setABN <- String						If the input is NULL, ABN is set to 0000 0000 000
 * setOperationSite <- String			If the input is NULL, operation site is "".
 * newShift <- void						
 * continueShift <- String 				If an IOException occurs and we cant deserailse file - we create a new shift.
 * printReciept <- void					If an HTMLDocumentException occurs we throw it from the function.  This way the interface classes
 * 										can handle the exception and print a warning to the user.
 */
public class Session implements java.io.Serializable
{
	//Shift system information
	private shiftsystem.Shift currentShift;
	private itemdictionary.ItemDictionary dict;
	private itemdictionary.ItemLibrary lib;
	private shiftsystem.Cart currentCart;
	//company information
	private String companyName;
	private String ABN;
	private String operationSite;
	/**
	 * Constructor
	 */
	public Session()
	{
		this.dict = new itemdictionary.ItemDictionary();
		this.lib = new itemdictionary.ItemLibrary();
		setup.Setup.setupDictionary(dict, lib);
		setup.Setup.setupCompanyDetails(this);
		this.currentShift = new shiftsystem.Shift(dict);
	}//END constructor	
	/*
	 * GETTERS
	 */
	/**
	 * This method returns the operation site string.
	 * Handles NULL strings.
	 * 
	 * @return String
	 */
	public String getOperationSite()
	{
		String site = this.operationSite;
		if(site == null)
			site = "";
		return site;
	}//END getOperationSite
	
	//new
	
	public itemdictionary.ItemDictionary getDict()
	{
		return this.dict;
	}
	
	public itemdictionary.ItemLibrary getLib()
	{
		return this.lib;
	}
	
	/**
	 * This method returns the currentShift.
	 * 
	 * @return shifysystem.Shift
	 */
	public shiftsystem.Shift getCurrentShift()
	{
		return this.currentShift;
	}//END getCurrentShift
	/**
	 * This method returns the company name of the Session.
	 * If company name is NULL returns and empty string.
	 * 
	 * @return String
	 */
	public String getCompanyName()
	{
		String name = this.companyName;
		if(name == null)
			name = "";
		return name;
	}//END getCompanyName
	/**
	 * This method returns the ABN of the company. If ABN is NULL returns empty string.
	 * 
	 * @return String
	 */
	public String getABN()
	{
		String abn = this.ABN;
		if(abn == null)
			abn = "";
		return abn;
	}//END getABN
	/**
	 * This method gets the shift sale list (not numeric total) for both cash, eftpos, and
	 * customer credit.
	 * 
	 * @return String[] takings
	 */
	public String[] getShiftSales2()
	{
		String[] takings = {"", "", ""};
		String allSales = this.currentShift.toString();
		String[] salesArray = allSales.split("\\n");
		for(int i = 0; i < salesArray.length; i++)
		{
			if(salesArray[i].split(",")[3].equals("Cash"))
				takings[0] += (salesArray[i] + "\n");
			else if(salesArray[i].split(",")[3].equals("EFTPOS"))
				takings[1] += (salesArray[i] + "\n");
			else
				takings[2] += (salesArray[i] + "\n");
		}
		return takings;
	}//END getShiftSales
	public String[] getShiftSales()
	{
		String[] takings = {"", "", ""};
		
		takings[0] = this.getCurrentShift().getCashRuleOff();
		takings[1] = this.getCurrentShift().getEftposRuleOff();
		takings[2] = this.getCurrentShift().getCustomerRuleOff();
		
		return takings;
	}//END getShiftSales
	/*
	 * SETTERS
	 */
	/**
	 * This method sets the company name to the input string.
	 * If the input string is NULL, we set the company name to "".
	 * 
	 * @param in
	 */
	public void setCompanyName(String in)
	{
		if((this.companyName = in) == null)
			this.companyName = "";
	}//END setCompanyName 
	/**
	 * This method sets the ABN of the company to the input string.
	 * We handle a NULL string input, and print a warning if the ABN might not be correct by
	 * formatting standards. 
	 * 
	 * @param in
	 */
	public void setABN(String in)
	{
		if((this.ABN = in) == null)
			this.ABN = "0000 0000 000"; //handles default value if NULL input.
		if(this.ABN.replaceAll(" ", "").length() != 11) //prints warning but allows false ABN
			System.out.println("Warning. ABN may not be of correct length");
	}//END setABN
	/**
	 * This method sets the operation site to the input string. 
	 * Handles NULL input.
	 * 
	 * @param in
	 */
	public void setOperationSite(String in)
	{
		if((this.operationSite = in) == null)
			this.operationSite = "";
	}//END setOperationSite
	/*
	 * PUBLIC METHODS
	 */
	/**
	 * This method creates a new shift and amends it to current shift.
	 */
	public void newShift()
	{
		this.currentShift = new shiftsystem.Shift(dict);
	}//END newShift
	/**
	 * This method creates a new shift from a serialised input file. 
	 * If the file cannot be deserialised, it will create a new shift and throw an exception
	 * 
	 * @param String fileName
	 */
	public void continueShift(String fileName) 
	{
		Session defrostedSession;
		defrostedSession = (Session)file.FileHandler.deserialiseFile(fileName);
		if(defrostedSession == null)
		{
			this.newShift();
			graphicalinterface.WarningWindow miscrowavebroken 
				= new graphicalinterface.WarningWindow("Failed to defrost frozen shift.");
		}
		else
		{
			this.currentShift = defrostedSession.getCurrentShift();
		}
	}//END continueShift
	/**
	 * This method prints the receipt for the currentCart and currentSession.
	 * current cart is required to get cart list and total, and current session is 
	 * required to get the company details (ABN, name, operation site).
	 * 
	 * If an IOException occurs and the receipt cannot be printed - print a warning and
	 * do not print receipt.
	 * 
	 * @throws HTMLDocumentException
	 */
	public void printReciept() throws datastructures.HTMLDocumentException
	{
		try {
			file.SaveSession.createHTMLreciept(currentCart, this);
		}catch(IOException e) {
			throw new datastructures.HTMLDocumentException("Can not print reciept");
		}
	}//END printReciept
}//END class
