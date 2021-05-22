package graphicalinterface;
import java.awt.*;
import java.time.LocalDate;
/**
 * this method returns system details to the user. 
 * used to a high level WindowBase class to get screen dimensions. 
 * @author Ryan
 * @version 1.0
 */
public class GetSystemDetails 
{
	/**
	 * Returns the screen dimensions
	 * @return double[] = {width, height}
	 */
	public static int[] getScreenDimensions()
	{
		int[] dimensions = {0,0};
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		dimensions[0] = (int)screenSize.getWidth();
		dimensions[1] = (int)screenSize.getHeight();
		return dimensions;
	}//END getScreenDimensions
	/**
	 * This method returns the date in a string form
	 * @return date String
	 */
	public static String getDate()
	{
		LocalDate date = java.time.LocalDate.now();
		return date.toString();
	}//END getDate
}//END class
