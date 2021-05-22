package graphicalinterface;
import java.awt.*;
import javax.swing.*;
/**
 * This abstract class defines the required variables for all windows. 
 * Only one window main is made per application instance. 
 * We also have the receipt window, and warning windows that extend from this class. 
 * @author email
 * @version 1.0
 */
public abstract class WindowBase implements java.io.Serializable
{
	public GraphicsEnvironment graphics;
	public GraphicsDevice device;
	public JFrame frame;
	public String windowName;
	public JPanel currentPanel;
	
	abstract public void closeWindow();
	abstract protected void createWindow();
}//END class
