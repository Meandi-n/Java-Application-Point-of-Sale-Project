package graphicalinterface.customPanels;

import java.awt.*;
import javax.swing.*;

import graphicalinterface.GetSystemDetails;
import graphicalinterface.GraphicalInterface;
import graphicalinterface.customcomponents.StyledLabel;
import graphicalinterface.customcomponents.StyledTextField;

public class RefundPage extends GenericPage
{
	private main.Session currentSession;
	private shiftsystem.Shift currentShift;
	private shiftsystem.Cart currentCart;
	private GraphicalInterface g;
	
	private graphicalinterface.customcomponents.DoubleField idfield;
	
	public RefundPage(main.Session session, GraphicalInterface g)
	{
		super("Refund Page");
		this.currentSession = session;
		this.currentShift = session.getCurrentShift();
		this.currentCart = currentShift.getCurrentCart();
		this.createInterface();
		this.g = g;
	}
	
	private void createInterface()
	{
		graphicalinterface.customcomponents.DetailButton voidSale = new graphicalinterface.customcomponents.DetailButton("Back");
		voidSale.addActionListener(e -> close());
		toolBar.add(voidSale);
		
		JPanel refundPanel = new JPanel();
		refundPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		StyledLabel refundLabel = new graphicalinterface.customcomponents.StyledLabel("Cart ID = ");
		this.idfield = new graphicalinterface.customcomponents.DoubleField(20);
		graphicalinterface.customcomponents.StyledButton refundButton = new graphicalinterface.customcomponents.StyledButton("Refund");
		refundButton.addActionListener(e -> this.refund());
		
		JPanel inputPanel = new JPanel();
		inputPanel.add(refundLabel);
		inputPanel.add(this.idfield);
		
		c.gridx = 0; c.gridy = 0;
		c.weightx = 1; c.gridwidth = 1; 
		refundPanel.add(inputPanel, c);
		c.gridx = 0; c.gridy = 1;
		c.weightx = 1; c.gridwidth = 1; 
		refundPanel.add(refundButton, c);
		
		this.add(refundPanel);
	}
	
	private void refund()
	{
		String id = idfield.getText();
		try{
			shiftsystem.Cart removedCart = currentShift.processRefund(Integer.parseInt(id));
			graphicalinterface.WarningWindow refund = new graphicalinterface.WarningWindow("Please refund " 
						+ removedCart.getCartTotal() + " on " + removedCart.getPaymentType().toString());
		}catch(NullPointerException e) {
			graphicalinterface.WarningWindow error = new graphicalinterface.WarningWindow("Cart ID not found.");
		}catch(NumberFormatException e) {
			graphicalinterface.WarningWindow error = new graphicalinterface.WarningWindow("Cart ID not found. No decimal places in cart ID.");
		}		
		this.close();
	}
	
	private void close()
	{
		this.setVisible(false);
		g.setCurrentPanel(new ShiftOperationsPage(this.currentSession, this.g));
		g.updateFrame();
	}
}
