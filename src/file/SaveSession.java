package file;
import java.io.IOException;
/**
 * This method handles the formatting to save the session.  It acts as an 
 * intermediate between the session object and the FileHandler methods.
 * 
 * @author Ryan
 * @version 1.0
 */
/* PUBLIC METHODS IN CLASS
 * SaveSessionasPlainText <- main.Session 		throws IOException if writeFile cannot create file.
 * SerialiseSession <- main.Session 			throws IOException if writeFile cannot create file.
 * createHTMLreciept <- Cart, main.Session		throws IOExcpeiton if writeFile cannot create file OR readWholeFile returns null string.
 * saveSessionHTML <- main.Session				" " 
 * deserialiseSession <- String fileName 		will return null if file cannot be found.
 */
public class SaveSession {
	/*
	 * PUBLIC METHOD OUTPUTTING FILES
	 */
	/**
	 * This method saves the session as a plain text file. 
	 * If the writeFile method encounters an IOError or error with fileName string, 
	 * we throw an IOException here too.  
	 * 
	 * @param session
	 * @throws IOException 
	 */
	public static void SaveSessionasPlainText(main.Session session) throws IOException
	{
		String fileName = "SIMPLEPOS_" + session.getCurrentShift().getShiftStartDate().toString() +
				"_" + session.getCurrentShift().getShiftStartTime().toString() + ".txt";
		file.FileHandler.writeFile(fileName, session.getCurrentShift().toString());
	}//END SaveSessionPlainText
	/**
	 * This method saves the session objects as a serial file.  
	 * It simply specifies the file name based on session information before passing it to the more
	 * generalised serialiseFile method.
	 * 
	 * @param session
	 * @throws IOException 
	 */
	public static void SerialiseSession(main.Session session) throws IOException
	{
		String fileName = "SIMPLEPOS_" + session.getCurrentShift().getShiftStartDate().toString() +
				"_" + session.getCurrentShift().getShiftStartTime().toString() + ".serialfile";
		file.FileHandler.seraliseFile(fileName, session);
	}//END SerialiseSession
	/**
	 * This method modifies a template HTML file for a card receipt, then saves the modified HTML
	 * file as a file with the date and time specified.  If there is an IOException when creating a new 
	 * file with the createDirectory method (caused by a folder name error most likely), then an IOException is thrown.
	 * Also may throw an IOException on the final writeFile method.
	 * 
	 * @param cart
	 * @param session
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation") //using deprecated getDate and getMonth methods.  
	public static String createHTMLreciept(shiftsystem.Cart cart, main.Session session) throws IOException
	{
		//depricated - improve later.
		String directoryName = "Receipts_" + cart.getDate().getDate() + "_" + cart.getDate().getMonth() + "_" + cart.getDate().getYear();
		file.FileHandler.createDirectory(directoryName); //throws IOException
		String fileName = directoryName + "/RECEIPT_" + cart.getCartID() + cart.getDateString() + ".html";
		//the string from the template file
		String htmlString = file.FileHandler.readWholeFile("TEMPLATE_RECIEPT.html"); //will return NULL in an error
		//obtaining cart details
		String items = cart.toString().replace("\n", "<br>");
		double total = cart.getCartTotal();
		String date = cart.getDateString();
		//obtaining company details from session
		String company = session.getCompanyName();
		String operation = session.getOperationSite();
		String ABN = session.getABN();
		//replacing information in original template reciept
		if(htmlString != null) // readWholeFile will return htmlString = null if an error occured
		{
			htmlString = htmlString.replace("$COMPANY", company);
			htmlString = htmlString.replace("$SITE", operation);
			htmlString = htmlString.replace("$ABN", ABN);
			htmlString = htmlString.replace("$date", date);
			htmlString = htmlString.replace("$CART", items);
			htmlString = htmlString.replace("$TOTAL", total+"");
			htmlString = htmlString.replace("$ID", cart.getCartID()+"");
		}
		else
			throw new IOException(); //throw error as IOException
		//writing file
		fileName = file.FileHandler.writeFile(fileName, htmlString); //throws IOException
		
		return fileName;
	}//END saveHTMLreciept
	/**
	 * This method modifies a template HTML file for a takings rule off.  Analagous to createHTMLreciept method in 
	 * this class.  Throws IOException if the HTML string is NULL or if the writeFile method throws IOException
	 * 
	 * @param session
	 * @throws IOException 
	 */
	public static String saveSessionHTML(main.Session session) throws IOException 
	{
		String fileName = "SIMPLEPOS_" + session.getCurrentShift().getShiftStartDate().toString() +
				"_" + session.getCurrentShift().getShiftStartTime().toString() + ".html";
		fileName = fileName.replaceAll(":", "_"); 
		//template HTML code
		String htmlString = file.FileHandler.readWholeFile("TEMPLATE.html"); //will return NULL on internal IOException
		//getting list of all sales for cash eftpos and credit
		String[] takings = session.getShiftSales();
		//takings (in dollars)
		double cash = session.getCurrentShift().getCashTotal();
		double eftpos = session.getCurrentShift().getEFTPOSTotal();
		double credit = session.getCurrentShift().getEFTPOSTotal(); //just a dummy for now
		//shift information
		String date = session.getCurrentShift().getShiftStartDate().toString();
		String time = session.getCurrentShift().getShiftStartTime().toString();
		//company information
		String company = session.getCompanyName();
		String operation = session.getOperationSite();
		String ABN = session.getABN();
		if(htmlString != null)
		{
			htmlString = htmlString.replace("$COMPANY", company);
			htmlString = htmlString.replace("$SITE", operation);
			htmlString = htmlString.replace("$ABN", ABN);
			htmlString = htmlString.replace("$time", time);
			htmlString = htmlString.replace("$date", date);
			//total cash sales done
			htmlString = htmlString.replace("$cashsales", takings[0].replaceAll("\n", "<br>"));
			htmlString = htmlString.replace("$totalcash", (cash)+"");
			//total eftpos sales done
			htmlString = htmlString.replace("$eftpossales", takings[1].replaceAll("\n", "<br>"));
			htmlString = htmlString.replace("$totaleftpos", eftpos+"");
			//total credit sales done
			htmlString = htmlString.replace("$creditsales", takings[2].replaceAll("\n", "<br>"));
			htmlString = htmlString.replace("$totalcredit", credit+""); //same as eftpos for now just a dummy
			//total of all sales for shift
			htmlString = htmlString.replace("$total", (cash+eftpos+credit)+"");
		}
		else
			throw new IOException();
		//writing file
		file.FileHandler.writeFile(fileName, htmlString); //throws IOException
		
		return fileName;
	}//END saveSessionHTML
	/*
	 * PUBLIC METHOD TAKING FILE INPUT
	 */
	/**
	 * This method deserialises a file.  It does not expand on the operations of the FileHandler method, 
	 * but its presence in this class makes more sense when it's called later.
	 * If an IOException occurs in the fileHandler class than the session object is returned as NULL.
	 * 
	 * @param fileName
	 * @return session Object
	 */
	public static main.Session deserialiseSession(String fileName)
	{
		main.Session session = null;
		session = (main.Session)file.FileHandler.deserialiseFile(fileName);
		return session;
	}//END deserialiseSession	
}//END class
