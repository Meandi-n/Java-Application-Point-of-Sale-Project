package graphicalinterface.customPanels;

import java.awt.*;
import javax.swing.*;

import graphicalinterface.GetSystemDetails;
import graphicalinterface.GraphicalInterface;
import graphicalinterface.customcomponents.StyledTextField;

public class ForcedRefundPage extends GenericPage
{
	private main.Session currentSession;
	private shiftsystem.Shift currentShift;
	private shiftsystem.Cart currentCart;
	private GraphicalInterface g;
	
	private graphicalinterface.customcomponents.DoubleField amount;
	
	private shiftsystem.Enum.PAYMENTTYPE paymentType;
	
	public ForcedRefundPage(main.Session session, GraphicalInterface g)
	{
		super("Forced Cash Refund Page");
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
		
		/* creating buttons for starting a new shift, or continuing a shift */
		JPanel inputPanel = new JPanel();
		graphicalinterface.customcomponents.StyledLabel header = new graphicalinterface.customcomponents.StyledLabel("Amount:");
		this.amount = new graphicalinterface.customcomponents.DoubleField(15);
		graphicalinterface.customcomponents.StyledButton button = new graphicalinterface.customcomponents.StyledButton("Force Refund (Cash)");
		
		button.addActionListener(e -> this.refund());
		
		inputPanel.add(header);
		inputPanel.add(this.amount);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; c.gridy = 0;
		mainPanel.add(inputPanel,c);
		c.gridx = 0; c.gridy = 1;
		mainPanel.add(button,c);
		
		this.add(mainPanel);
	}
	
	private void refund()
	{
		currentSession.getCurrentShift().createNewCart();
		currentSession.getCurrentShift().forceRefund(Double.parseDouble(this.amount.getText()));
		graphicalinterface.WarningWindow reminder = new graphicalinterface.WarningWindow("Please refund " + amount.getText() + " cash");
		this.close();
	}
	
	private void close()
	{
		this.setVisible(false);
		g.setCurrentPanel(new ShiftOperationsPage(this.currentSession, this.g));
		g.updateFrame();
	}
}
