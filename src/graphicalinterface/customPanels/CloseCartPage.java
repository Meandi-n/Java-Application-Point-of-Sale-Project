package graphicalinterface.customPanels;

import java.awt.*;
import javax.swing.*;

import graphicalinterface.GetSystemDetails;
import graphicalinterface.GraphicalInterface;

public class CloseCartPage extends JPanel
{
	private int[] screenSize = GetSystemDetails.getScreenDimensions();
	private main.Session currentSession;
	private shiftsystem.Shift currentShift;
	private shiftsystem.Cart currentCart;
	private GraphicalInterface g;
	
	public CloseCartPage(main.Session session, GraphicalInterface g)
	{
		super();
		this.currentSession = session;
		this.currentShift = session.getCurrentShift();
		this.currentCart = currentShift.getCurrentCart();
		this.createInterface();
		this.g = g;
	}
	
	private void createInterface()
	{
		JPanel tooltipBar = new JPanel();
		tooltipBar.setPreferredSize(new Dimension(screenSize[0], 30));
		JLabel label = new JLabel("New Cart Window");
		tooltipBar.add(label, BorderLayout.LINE_START);
		JLabel time = new JLabel(GetSystemDetails.getDate());
		tooltipBar.add(time, BorderLayout.LINE_END);
		tooltipBar.setBackground(Color.lightGray);
		
		/* creating buttons for starting a new shift, or continuing a shift */
		JPanel buttonPanel = new JPanel();
		JButton helloworld = new JButton("hello world");
		helloworld.setPreferredSize(new Dimension((int)(screenSize[0]*0.25),100));
		helloworld.addActionListener(e -> this.dummyevent());
		
		buttonPanel.add(helloworld, BoxLayout.X_AXIS);
		
		/* adding all panels to container of frame */
		this.add(tooltipBar, BorderLayout.PAGE_START);
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	private void dummyevent()
	{
		//this.setVisible(false);
	}
}
