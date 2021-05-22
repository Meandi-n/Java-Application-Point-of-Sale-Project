package datastructures;
/**
 * This is a custom exception thrown when the HTML document
 * cannot be parsed or edited by the program.  These documents are used to 
 * display, print, and save reciepts and cash ups. 
 * @author Ryan
 * @version 1.0
 */
public class HTMLDocumentException extends Exception implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	public HTMLDocumentException(String s)
	{
		super(s);
	}
	public void printMessage()
	{
		System.out.println(super.getMessage());
	}
}
