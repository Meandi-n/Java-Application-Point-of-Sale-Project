package setup;
import java.util.regex.PatternSyntaxException;
/**
 * This class sets up the company details and dictonary entries. 
 * Essentially it reads configuration files and updates datastructures.
 * @author Ryan
 * @version 1.0
 */
public class Setup {
	/**
	 * This method reads the DEFAULT_DICTIONARY_FILE which is a configuration file 
	 * containing the formatted entries for the dictionary. 
	 * If an exception is thrown we create a dictionary entry using default parameters, 
	 * this signals to the user that an index of their dictionary is not parsed properly.
	 * 
	 * @param dict
	 */
	public static void setupDictionary(itemdictionary.ItemDictionary dict, itemdictionary.ItemLibrary lib) //added item library paramater
	{
		String defaultFile = "DEFAULT_DICTIONARY_FILE.config";
		String[] fileContents = file.FileHandler.readFile(defaultFile);
		if(fileContents != null)
		{
			for(int i = 0; i < fileContents.length; i ++)
			{
				if(!(fileContents[i].charAt(0) == '*')) //skip category notes
				{
					//default entry parameters
					String key = "UNKNOWN";
					double price = 0.0;
					double quantity = 0.0;
					String desc = "Dictionary setup could not parse entry";
					try {
						String[] splitString = fileContents[i].split(":", 0);
						key = splitString[0];
						desc = splitString[1];
						price = Double.parseDouble(splitString[2]);
						quantity = Double.parseDouble(splitString[3]);
					}catch(NumberFormatException e) { //occurs when the price or quantity is not numeric 
						System.out.println(e.getMessage());
						e.printStackTrace();
					}catch(PatternSyntaxException e) { //thrown by thirst line of try catch block
						System.out.println(e.getMessage());
						e.printStackTrace();
					}catch(NullPointerException e) { //thrown by parseDouble if input string is null
						System.out.println(e.getMessage());
						e.printStackTrace(); 
					}catch(ArrayIndexOutOfBoundsException e) { //thrown if we do not split into 4 segments
						System.out.println(e.getMessage());
						e.printStackTrace();
					}finally { //still create an entry, but use the default entry paramaters
						dict.setEntry(key, desc, price, quantity);
						lib.addCategoryEntry(key, desc, price, quantity); //havent handled exceptions here yet
					}
				}
				else
				{
					String categoryName = fileContents[i];
					lib.addCategoryToList(categoryName);
					lib.newCategory();
				}
			}
		}
	}//END setupDictionary
	/**
	 * This method interprets the company details and amends them to the input session. 
	 * this information is printed on receipts and cash up reports. 
	 * This method sets company details to warning messages if the file cannot be read successfully.
	 * 
	 * @param session
	 */
	public static void setupCompanyDetails(main.Session session)
	{
		//Default values
		String companyName = "Cannot Parse Company Name";
		String ABN = "00000000000";
		String operationSite = "Cannot Parse Operation Site";
		String[] contents =file.FileHandler.readFile("COMPANYDETAILS.config"); // will return null if it encounters an exception
		try {
			companyName = contents[0];
			ABN = contents[1];				
			operationSite = contents[2];
		}catch(ArrayIndexOutOfBoundsException e) { //occurs if the company file doesn't contain all information.
			e.printStackTrace(); //note: needs a return character at end of file to work (press enter at end)
		}catch(NullPointerException e) { //occurs if readFile returns NULL (which would be an IOException )
			e.printStackTrace(); 
		}finally { //still updates information to prevent error, but default information signals error to user.
			session.setCompanyName(companyName);
			session.setABN(ABN);
			session.setOperationSite(operationSite);
		}
	}//END setupCompanyDetails
}//END class
