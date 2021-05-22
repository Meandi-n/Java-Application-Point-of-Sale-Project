package graphicalinterface;
import java.awt.*;
import javax.swing.*;
/**
 * This is a simple warning window with a message and an OK button.
 * @author Ryan
 * @version 1.0
 */
public class WarningWindow extends WindowBase implements java.io.Serializable
{
	private String message;
	public JButton button;
	/**
	 * Constructor
	 * @param message 
	 */
	public WarningWindow(String message)
	{
		this.frame = new JFrame(windowName);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setMinimumSize(new Dimension(300,100));
		this.frame.setResizable(false);
		this.frame.setUndecorated(false);
		this.frame.setAlwaysOnTop(true);
		this.message = message;
		this.createWindow();
	}//END constructors
	/**
	 * This method prints a simple warning window with an OK button
	 * @param message
	 */
	protected void createWindow()
	{
		this.frame.setLayout(new GridLayout(2,1));
		JLabel MSG = new JLabel(this.message);
		MSG.setSize(new Dimension(300,100));
		MSG.setHorizontalAlignment(SwingConstants.CENTER);
		button = new JButton("OK");
		button.setSize(new Dimension(100,50));
		button.addActionListener(e -> this.closeWindow());
		
		Container container = frame.getContentPane();
		container.add(MSG);
		container.add(button);
		
		this.frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}//END createWarningWindow
	public void closeWindow()
	{
		frame.dispose();
	}//END closeWindow
}//END class
