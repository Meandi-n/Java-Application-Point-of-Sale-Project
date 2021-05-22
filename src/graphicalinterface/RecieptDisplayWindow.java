package graphicalinterface;
import java.awt.*;
import javax.swing.*;
/**
 * This is a simple warning window with a message and an OK button.
 * @author Ryan
 * @version 1.0
 */
public class RecieptDisplayWindow extends WindowBase implements java.io.Serializable
{
	private String htmldata;
	public graphicalinterface.customcomponents.StyledButton printButton;
	public graphicalinterface.customcomponents.StyledButton skipButton;
	/**
	 * Constructor
	 * @param message 
	 */
	public RecieptDisplayWindow(String htmldata)
	{
		this.frame = new JFrame(windowName);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setMinimumSize(new Dimension(300,600));
		this.frame.setResizable(false);
		this.frame.setUndecorated(true);
		this.frame.setAlwaysOnTop(true);
		
		this.htmldata = htmldata;
		
		this.createWindow();
	}//END constructors
	/**
	 * This method 
	 * @param message
	 */
	protected void createWindow()
	{
		JPanel recieptLayout = new JPanel();
		recieptLayout.setLayout(new GridLayout(1,2));
		JPanel buttonArray = new JPanel();
		buttonArray.setLayout(new GridLayout(2,1));
		
		JEditorPane htmlPane = new JEditorPane("text/html", htmldata);
		htmlPane.setEditable(false);
		
		JScrollPane recieptDisplay = new JScrollPane(htmlPane);
		recieptDisplay.createVerticalScrollBar();
		
		//add skip and print buttons plus functionality
		skipButton = new graphicalinterface.customcomponents.StyledButton("Skip");
		printButton = new graphicalinterface.customcomponents.StyledButton("Print");
		
		printButton.addActionListener(e -> this.printReciept(htmlPane));
		skipButton.addActionListener(e -> this.skipReciept());
		
		buttonArray.add(skipButton);
		buttonArray.add(printButton);
		
		recieptLayout.add(recieptDisplay);
		recieptLayout.add(buttonArray);
		recieptLayout.setBorder(BorderFactory.createTitledBorder("Top Panel"));
		
		Container container = frame.getContentPane();
		container.add(recieptLayout);
		
		
		this.frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}//END createWarningWindow
	public void closeWindow()
	{
		frame.dispose();
	}//END closeWindow	
	/*
	 * Private Methods
	 */
	private void printReciept(JEditorPane reciept)
	{
		file.Print.printReciept(reciept);
		this.closeWindow();
	}//END printReciept
	private void skipReciept()
	{
		this.closeWindow();
	}//END skipReciepts
}//END class
