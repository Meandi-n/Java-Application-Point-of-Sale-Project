package graphicalinterface;
import java.awt.*;
import javax.swing.*;
/**
 * This class implements a general window that 
 * is extended for specific applications.
 * @author Ryan
 * @version 1.0
 */
public class Window extends WindowBase implements java.io.Serializable
{
	/**
	 * Constructor method
	 * @param name
	 */
	public Window(String name)
	{
		this.windowName = name;
		this.createWindow();
	}//END constructor
	/**
	 * creates a simple window
	 */
	protected void createWindow()
	{
		this.frame = new JFrame(windowName);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int[] screenSize = GetSystemDetails.getScreenDimensions();
		this.frame.setMinimumSize(new Dimension(screenSize[0], screenSize[1]));
		this.frame.setResizable(false);
		//this.frame.setUndecorated(true);
		this.graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.device = graphics.getDefaultScreenDevice();
		//this.device.setFullScreenWindow(frame);
	}//END createWindow
	/**
	 * This method closes the window currently in use.
	 */
	public void closeWindow()
	{
		frame.dispose();
	}//END closeWindow
	/**
	 * This updates the frame.  It sets the contents of the window (frame) to the current
	 * panel (current panel can be edited).
	 */
	public void updateFrame()
	{
		if(currentPanel != null)
			frame.setContentPane(currentPanel);
		frame.setSize(new Dimension(frame.getSize())); //this stops the glitch where the screen wont update
		frame.setVisible(true);
	}//END updateFrame
	/**
	 * This allows the current panel to be changed. Done before updateFrame() is called. 
	 * @param in
	 */
	public void setCurrentPanel(JPanel in)
	{
		this.currentPanel = in;
	}//END setCurrentPanel
}//END class
