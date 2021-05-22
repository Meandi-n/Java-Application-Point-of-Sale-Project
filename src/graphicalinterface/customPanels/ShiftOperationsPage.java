package graphicalinterface.customPanels;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import graphicalinterface.GetSystemDetails;
import graphicalinterface.GraphicalInterface;
import graphicalinterface.customcomponents.BackgroundPanel;

public class ShiftOperationsPage extends BackgroundPanel
{	
	private GraphicalInterface g;
	private main.Session s;
	
	public ShiftOperationsPage(main.Session s, GraphicalInterface g)
	{
		super("Shift Page");
		this.g = g;
		this.s = s;
		this.createInterface();
	}
	
	public void createInterface()
	{		
		/* adding items to toolBar */
		String cashTotal = "Cash total: " + s.getCurrentShift().getCashTotal();
		graphicalinterface.customcomponents.ToolBarLabel cash = new graphicalinterface.customcomponents.ToolBarLabel(cashTotal);
		String eftposTotal = "EFTPOS total: " + s.getCurrentShift().getEFTPOSTotal();
		graphicalinterface.customcomponents.ToolBarLabel eftpos = new graphicalinterface.customcomponents.ToolBarLabel(eftposTotal);
		String customerTotal = "Customer total: " + (s.getCurrentShift().getShiftTotal()
													- s.getCurrentShift().getCashTotal()
													- s.getCurrentShift().getEFTPOSTotal());
		graphicalinterface.customcomponents.ToolBarLabel customer = new graphicalinterface.customcomponents.ToolBarLabel(customerTotal);
		
		JPanel totalsPanel = new JPanel();
		totalsPanel.add(cash); totalsPanel.add(eftpos); totalsPanel.add(customer);
		super.toolBar.add(totalsPanel);
		
		graphicalinterface.customcomponents.DetailButton endShift = new graphicalinterface.customcomponents.DetailButton("End Shift");
		endShift.setPreferredSize(new Dimension((int)(screenSize[0]*0.25),100));
		super.toolBar.add(endShift);
		
		/* creating buttons for starting a new shift, or continuing a shift */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		graphicalinterface.customcomponents.AttentionButton newCartButton = new graphicalinterface.customcomponents.AttentionButton("Create new cart");
		graphicalinterface.customcomponents.StyledButton refundButton = new graphicalinterface.customcomponents.StyledButton("Refund by cart ID");
		graphicalinterface.customcomponents.StyledButton forcedRefund = new graphicalinterface.customcomponents.StyledButton("Force a cash refund");
		graphicalinterface.customcomponents.StyledButton serialiseShift = new graphicalinterface.customcomponents.StyledButton("Serialise shift");
		
		newCartButton.setToolTipText("Create new empty cart in current shift.");
		refundButton.setToolTipText("Refund a specific cart using the cart ID found on the reciept provided.");
		forcedRefund.setToolTipText("Create a cash only refund that creates a new transaction, not requiring the use of a cart ID.");
		serialiseShift.setToolTipText("(Advanced) Save shift as serialised class file that can be edited later. Will not close shift.");
		
		newCartButton.addActionListener(e -> this.newCartEvent());
		refundButton.addActionListener(e -> this.refundEvent());
		forcedRefund.addActionListener(e -> this.forcedRefundEvent());
		endShift.addActionListener(e -> this.endShiftEvent());
		serialiseShift.addActionListener(e -> this.serialiseShift());
		
		newCartButton.setPreferredSize(new Dimension((int)(screenSize[0]*0.20),100));
		refundButton.setPreferredSize(new Dimension((int)(screenSize[0]*0.20),100));
		forcedRefund.setPreferredSize(new Dimension((int)(screenSize[0]*0.20),100));
		serialiseShift.setPreferredSize(new Dimension((int)(screenSize[0]*0.20),100));
	
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 1; c.gridy = 0;
		c.weightx = 1; c.weighty = 1;
		c.gridheight = 3;
		buttonPanel.add(newCartButton, c);
		
		c.gridx = 0; c.gridy = 0;
		c.weightx = 1; c.weighty = 1;
		c.gridheight = 1;
		buttonPanel.add(refundButton, c);
		
		c.gridx = 0; c.gridy = 1;
		c.weightx = 1; c.weighty = 1;
		c.gridheight = 1;
		buttonPanel.add(forcedRefund, c);
		
		c.gridx = 0; c.gridy = 2;
		c.weightx = 1; c.weighty = 1;
		c.gridheight = 1;
		buttonPanel.add(serialiseShift, c);
		
		/* adding all panels to container of frame */
		this.add(buttonPanel);
	}
	
	private void serialiseShift()
	{
		try {
			file.SaveSession.SerialiseSession(this.s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void newCartEvent()
	{
		s.getCurrentShift().createNewCart();
		g.setCurrentPanel(new CartPage(s, g));
		g.updateFrame(); 
		this.setVisible(false);
	}
	
	private void refundEvent()
	{
		s.getCurrentShift().createNewCart();
		g.setCurrentPanel(new RefundPage(s, g));
		g.updateFrame();
		this.setVisible(false);
	}
	
	private void forcedRefundEvent()
	{
		this.setVisible(false);
		s.getCurrentShift().createNewCart();
		g.setCurrentPanel(new ForcedRefundPage(s, g));
		g.updateFrame();
	}
	
	private void endShiftEvent()
	{
		try {
			this.printReciept();
			this.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printReciept() throws IOException
	{
		String htmldata;
		String fileName;
		graphicalinterface.RecieptDisplayWindow recieptDisplay = null;
		
		fileName = file.SaveSession.saveSessionHTML(this.s);
		htmldata = file.FileHandler.readWholeFile(fileName);
		recieptDisplay = new graphicalinterface.RecieptDisplayWindow(htmldata);
		//now we need a reciept window, with an "OKAY" button and a print button. 
		//the okay button will close the window and activate 
	}
	
	private void close()
	{
		this.setVisible(false);
		g.setCurrentPanel(new StartSessionPage(s, g));
		g.updateFrame();
	}
}
