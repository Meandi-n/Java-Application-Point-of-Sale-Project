package graphicalinterface.customPanels;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.*;

import graphicalinterface.customcomponents.CartTotalPanel;
import graphicalinterface.customcomponents.CashTotalPaidPanel;
import graphicalinterface.customcomponents.DoubleField;
import graphicalinterface.customcomponents.StyledLabel;
import graphicalinterface.customcomponents.StyledTextField;
import graphicalinterface.*;
import itemdictionary.ItemDictionary;
import shiftsystem.Enum.PAYMENTTYPE;

public class CartPage extends GenericPage
{
	private main.Session currentSession;
	private shiftsystem.Shift currentShift;
	private shiftsystem.Cart currentCart;
	private GraphicalInterface g;
	
	/*Sale table data*/
	private final String[] COLUMNNAMES = {"KEY", "DESCRIPTION", "UNIT PRICE", "QTY", "TOTAL PRICE"};;
	private final int STARTITEMS = 0;
	private String[][] tableData;
	private int itemRowCount = 0;
	private graphicalinterface.customcomponents.StyledJTable table;
	private JScrollPane tableSP;
	private DefaultTableModel tableModel;
	
	/*button array category*/
	private graphicalinterface.customcomponents.StyledTabbedPane tabbed;
	private JPanel[] tabPanel;
	private graphicalinterface.customcomponents.DetailButton[] buttons;
	
	/*new item input field*/
	private StyledTextField newItemKey;
	private String keyCode;
	private StyledTextField newItemDescription;
	private graphicalinterface.customcomponents.DoubleField newItemPrice;
	private graphicalinterface.customcomponents.DoubleField newItemQuantity;
	/*sale information*/
	private graphicalinterface.customcomponents.StyledTextField cartTotalField;
	
	/*text field for payment amount*/
	private JTextField cashField;
	
	public CartPage(main.Session session, GraphicalInterface g)
	{
		super("Cart Page");
		
		/*Sale table data*/
		this.tableData = new String[this.STARTITEMS][5];
		
		this.currentSession = session;
		this.currentShift = session.getCurrentShift();
		this.currentCart = currentShift.getCurrentCart();
		this.createInterface();
		this.g = g;
	}
	
	private void createInterface()
	{
		/*adding ID and void button to toolbar*/
		String cartTotal = "Cart ID: " + currentCart.getCartID();
		graphicalinterface.customcomponents.ToolBarLabel ID = new graphicalinterface.customcomponents.ToolBarLabel(cartTotal);
		toolBar.add(ID);
		
		graphicalinterface.customcomponents.DetailButton voidSale = new graphicalinterface.customcomponents.DetailButton("Void Sale");
		voidSale.addActionListener(e -> eventVoidSale());
		toolBar.add(voidSale);
		
		/*adding actual display for creating new cart*/
		
		/*           |
		 *  Sale     | Menu 
		 *  screen   | Pay, remove last item, 
		 * ----------|------------
		 *  menu     | Menu outstanding total, change, generate reciept.
		 *  item det.|
		 */          
		JPanel cartPanel = new JPanel();
		cartPanel.setOpaque(false);
		cartPanel.setLayout(new GridLayout(2,2));
		cartPanel.setPreferredSize(new Dimension(this.screenSize[0]-50, this.screenSize[1]-50));
		
		/*
		 * TOP LEFT GRID
		 */
		this.tableModel = new DefaultTableModel(this.tableData, this.COLUMNNAMES) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		    
		};
		
		this.table = new graphicalinterface.customcomponents.StyledJTable(tableModel);
		this.tableSP = new JScrollPane(table);
		//table.setFillsViewportHeight(true);
		
		//total field
		this.cartTotalField = new graphicalinterface.customcomponents.StyledTextField(5);
		graphicalinterface.customcomponents.StyledLabel cartTotalLabel = new graphicalinterface.customcomponents.StyledLabel("Cart Total: ");
		JPanel cartTotalPanel = new JPanel();
		cartTotalPanel.add(cartTotalLabel); 
		cartTotalPanel.add(cartTotalField);

		cartPanel.add(tableSP);
		
		/*
		 * TOP RIGHT GRID
		 */
		JPanel inputPane = this.generateInputPane();
		
		cartPanel.add(inputPane);
		
		/*
		 * BOTTOM LEFT GRID
		 */
		
		graphicalinterface.customcomponents.StyledTabbedPane categoryPane = this.generateKeyButtonList();
		cartPanel.add(categoryPane);
		this.newItemFieldListeners();
		
		/*
		 * BOTTOM RIGHT GRID
		 */
		
		JPanel purchasePanel = this.generatePurchasePanel();
		
		cartPanel.add(purchasePanel);
		
		/*
		 * ADDING IT ALL 
		 */
		this.add(cartPanel);
	}
	
	private JPanel generatePurchasePanel()
	{
		JPanel purchasePanel = new JPanel();
		purchasePanel.setOpaque(false);
		purchasePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		graphicalinterface.customcomponents.DetailButton cashButton = new graphicalinterface.customcomponents.DetailButton("Cash");
		graphicalinterface.customcomponents.DetailButton creditButton = new graphicalinterface.customcomponents.DetailButton("EFTPOS");
		graphicalinterface.customcomponents.UnimplementedButton customerCreditButton = new graphicalinterface.customcomponents.UnimplementedButton("Customer");
		
		this.cashField = new graphicalinterface.customcomponents.StyledTextField(5);
		CashTotalPaidPanel cashInputPanel = new CashTotalPaidPanel(this.cashField);
				
		cashButton.addActionListener(e -> this.payCash());
		creditButton.addActionListener(e -> this.payEFTPOS());
		//customerButton.addActionListener(e -> this.payCash());
		//adding it all to the purchase panel
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0; c.gridy = 0;
		c.weightx = 1; c.weighty = 1;
		purchasePanel.add(cashInputPanel, c);
		c.gridx = 1; c.gridy = 0;
		c.weightx = 1; c.weighty = 1;
		purchasePanel.add(cashButton, c);
		c.gridx = 1; c.gridy = 1;
		c.weightx = 1; c.weighty = 1;
		purchasePanel.add(creditButton, c);
		c.gridx = 1; c.gridy = 2;
		c.weightx = 1; c.weighty = 1;
		purchasePanel.add(customerCreditButton, c);
		
		return purchasePanel;
	}
	
	private void payCash()
	{
		this.currentCart.setPaymentType(PAYMENTTYPE.Cash);
		try {
			double change = Double.parseDouble(this.cashField.getText()) - this.currentCart.getCartTotal();
			if(change >= 0)
			{
				graphicalinterface.WarningWindow changeWindow = new graphicalinterface.WarningWindow("Please return " + change + " to customer.");
				changeWindow.button.addActionListener(e -> this.printReciept());
			}
			else
			{
				graphicalinterface.WarningWindow insufficientFunds = new graphicalinterface.WarningWindow("Insufficient Funds");
			}
		}catch(NumberFormatException e) {
			graphicalinterface.WarningWindow invalidFunds = new graphicalinterface.WarningWindow("Invalid Funds. Please input a number in cash paid field.");
		}
	}
	
	private void payEFTPOS()
	{
		this.currentCart.setPaymentType(PAYMENTTYPE.EFTPOS);
		graphicalinterface.WarningWindow eftWindow = new graphicalinterface.WarningWindow("Please charge the customer " + this.currentCart.getCartTotal() + " on EFTPOS");
		
		eftWindow.button.addActionListener(e -> this.printReciept());
		
		this.closeWindow();
	}
	
	private void printReciept()
	{
		String htmldata;
		String fileName;
		graphicalinterface.RecieptDisplayWindow recieptDisplay = null;
		try {
			fileName = file.SaveSession.createHTMLreciept(currentCart, currentSession);
			htmldata = file.FileHandler.readWholeFile(fileName);
			recieptDisplay = new graphicalinterface.RecieptDisplayWindow(htmldata);
		}catch (IOException e) {
			System.out.println("IOEXCEPTION E");
		}finally {
			this.closeWindow();
		}
		//now we need a reciept window, with an "OKAY" button and a print button. 
		//the okay button will close the window and activate 
	}
	
	private void closeWindow()
	{
		this.setVisible(false);
		g.setCurrentPanel(new ShiftOperationsPage(this.currentSession, this.g));
		g.updateFrame();
	}
	
	private void setEntriesForItemField(String buttonText)
	{
		String code = buttonText.split(":",0)[0];
		String description = buttonText.split(":",0)[1];
		this.newItemKey.setText(code);
		this.newItemDescription.setText(description);
		
		itemdictionary.Entry selectedEntry = this.currentSession.getDict().getEntry(code);
		if(selectedEntry != null)
		{
			this.newItemPrice.setText(String.valueOf(selectedEntry.default_price));
			this.newItemQuantity.setText(String.valueOf(selectedEntry.default_quantity));
		}
	}
	
	private void eventVoidSale()
	{
		currentShift.removeLastNode();
		this.closeWindow();
	}
	
	private void newItemFieldListeners()
	{
		for(int a = 0; this.buttons[a] != null; a++)
		{
			final int b = a;
			buttons[a].addActionListener(e -> this.setEntriesForItemField(buttons[b].getText()));
		}	
	}
	
	private JPanel generateInputPane()
	{
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//headers
		graphicalinterface.customcomponents.StyledLabel keyHeader = new graphicalinterface.customcomponents.StyledLabel("KEY CODE");
		graphicalinterface.customcomponents.StyledLabel descHeader = new graphicalinterface.customcomponents.StyledLabel("Description");
		graphicalinterface.customcomponents.StyledLabel priceHeader = new graphicalinterface.customcomponents.StyledLabel("Unit Price");
		graphicalinterface.customcomponents.StyledLabel quantityHeader = new graphicalinterface.customcomponents.StyledLabel("Quantity");
		//fields
		this.newItemKey = new StyledTextField("KEY", 5);
		this.newItemKey.setEditable(false);
		this.newItemDescription = new StyledTextField("desc", 15);
		this.newItemPrice = new graphicalinterface.customcomponents.DoubleField(5);
		this.newItemQuantity = new graphicalinterface.customcomponents.DoubleField(5);
		
		
		graphicalinterface.customcomponents.SquareButton addItemButton = new graphicalinterface.customcomponents.SquareButton("Add");
		graphicalinterface.customcomponents.SquareButton removeLastItemButton = new graphicalinterface.customcomponents.SquareButton("Remove Last");
		graphicalinterface.customcomponents.SquareButton removeSelectedItemButton = new graphicalinterface.customcomponents.SquareButton("Remove Selected");
		
		
		removeLastItemButton.addActionListener(e -> removeLastItemUpdateTable());
		addItemButton.addActionListener(e -> addItemUpdateTable());
		removeSelectedItemButton.addActionListener(e -> removeSelectedItem());
		
		
		CartTotalPanel cartTotalPanel = new CartTotalPanel(this.cartTotalField);
		
		//formatting panel
		c.weightx = 1; c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0; c.gridy = 0; panel.add(keyHeader, c);
		c.gridx = 1; c.gridy = 0; panel.add(descHeader, c);
		c.gridx = 2; c.gridy = 0; panel.add(priceHeader, c);
		c.gridx = 3; c.gridy = 0; panel.add(quantityHeader, c);
		
		c.gridx = 0; c.gridy = 1; panel.add(this.newItemKey, c);
		c.gridx = 1; c.gridy = 1; panel.add(this.newItemDescription, c);
		c.gridx = 2; c.gridy = 1; panel.add(this.newItemPrice, c);
		c.gridx = 3; c.gridy = 1; panel.add(this.newItemQuantity, c);
		
		c.gridx = 0; c.gridy = 2; panel.add(addItemButton, c);
		c.gridx = 1; c.gridy = 2; panel.add(removeLastItemButton, c);
		c.weightx = 2; c.gridwidth = 2;
		c.gridx = 2; c.gridy = 2; panel.add(removeSelectedItemButton, c);
		
		c.weightx = 4; c.gridwidth = 4;
		c.gridx = 0; c.gridy = 3; panel.add(cartTotalPanel, c);
		
		
		return panel;
	}
	
	private void removeSelectedItem()
	{
		int row = this.table.getSelectedRow();
		
		if(row != -1)
		{
			String key = (String)tableModel.getValueAt(row, 0);
			String desc = (String)tableModel.getValueAt(row, 1);
			double price = Double.parseDouble((String)tableModel.getValueAt(row, 2));
			double qty = Double.parseDouble((String)tableModel.getValueAt(row, 3));
			
			this.currentCart.removeItem(key, desc, price, qty);
			
			this.tableModel.removeRow(row);
			
			this.updateTotalField();
		}
		
	}
	
	private void addItemUpdateTable()
	{
		//new item fields
		double price = Double.parseDouble(this.newItemPrice.getText());
		double quantity = Double.parseDouble(this.newItemQuantity.getText());
		String description = this.newItemDescription.getText();
		String key = this.newItemKey.getText();
		
		this.currentCart.addItem(key, price, quantity, description);
		
		Object[] newRow = {key, description, String.valueOf(price), 
				String.valueOf(quantity), String.valueOf(quantity * price)};
	
		this.tableModel.addRow(newRow);
		//this.table.setModel(tableModel);
		
		this.updateTotalField();
				
	}
	
	private void removeLastItemUpdateTable()
	{
		this.currentCart.removeLastItem();
		if(tableModel.getRowCount() != 0)
		{
			this.tableModel.removeRow(tableModel.getRowCount()-1);
			this.updateTotalField();
		}
	}
	
	private void updateTotalField()
	{
		double total = this.currentCart.getCartTotal();
		this.cartTotalField.setText(String.valueOf(total));
	}
	
	private graphicalinterface.customcomponents.StyledTabbedPane generateKeyButtonList()
	{
		this.tabbed = new graphicalinterface.customcomponents.StyledTabbedPane();
		this.tabPanel = new JPanel[5];
		this.buttons = new graphicalinterface.customcomponents.DetailButton[100];
		itemdictionary.ItemLibrary currentLib = currentSession.getLib();
		String name; 
			
		datastructures.Category[] array = currentLib.toArray();
		Object[][] contents;
		int buttonCount = 0;
		
		for(int i = 0; i < array.length && array[i] != null; i ++)
		{
			contents = (Object[][])array[i].array;
			tabPanel[i] = new JPanel();
			name = array[i].name.replace("*", "");
			for(int ii = 0; contents[ii][0] != null; ii ++)
			{
				keyCode = (String)contents[ii][0];
				buttons[buttonCount] = new graphicalinterface.customcomponents.DetailButton(keyCode + ": " + ((itemdictionary.Entry)contents[ii][1]).default_description);
				tabPanel[i].add(buttons[buttonCount]);
				buttonCount ++;
			}
			tabbed.addTab(name, tabPanel[i]);
		}
		return tabbed;
	}
}
