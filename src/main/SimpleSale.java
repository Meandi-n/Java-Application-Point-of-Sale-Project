package main;
/**
 * The main class.
 * 
 * @author Ryan
 * @version 1.0
 */
public class SimpleSale 
{
	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//headlessinterface.HeadlessInterface inf = new headlessinterface.HeadlessInterface();
		Session session = new Session();
		graphicalinterface.GraphicalInterface g = new graphicalinterface.GraphicalInterface(session);
	}//END main
}//END class
