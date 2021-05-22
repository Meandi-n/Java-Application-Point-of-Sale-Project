package headlessinterface;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
/**
 * This class handles the headless interface for working the application with the console.
 * @author Ryan
 * @version 1.0
 */
public class HeadlessInterface {
	private main.Session currentSession;
	private Scanner sc;
	/**
	 * Main method for the HeadlessInterface - the constructor, 
	 * handles the running of the program from the console.
	 */
	public HeadlessInterface()
	{
		sc = new Scanner(System.in);
		this.currentSession = new main.Session();
		/*
		 * Prompt user if they wish to start a new shift, or continue the current shift.
		 */
		System.out.println(HeadlessInterfacePrompts.shiftInit);
		String input = sc.nextLine();
		this.newContinueShift(input); //handle the shift creation
		/*
		 * Now that we have created a shift, we can prompt the user on all the operations 
		 * they can do on the shift.
		 */
		do
		{
			System.out.println(HeadlessInterfacePrompts.shiftOperationsPrompt);
			input = sc.nextLine();
			switch(input)
			{
			case "E":
				break;
			case "1":
				this.newCartInShift();
				break;
			case "2":
				this.refundCartInShift();
				break;
			case "3":
				this.forcedRefundInCart();
				break;
			case "4":
				this.saveSessionPlainText();
				break;
			case "5":
				this.saveSessionHTML();
				break;
			case "6":
				this.serialiseSession();
				break;
			default:
				System.out.println("Invalid input");
			}
			this.printCurrentShiftTransactions();
			this.printShiftTotals();
		}while(!input.equals("E"));
	}//END headlessInterface()
	/**
	 * this method save serial file of session.
	 */
	private void serialiseSession()
	{
		try {
			file.SaveSession.SerialiseSession(currentSession);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}//END serialiseSession
	/**
	 * This method prints the current shift transactions to the screen
	 */
	private void printCurrentShiftTransactions()
	{
		System.out.println("Current shift transactions:");
		System.out.println(currentSession.getCurrentShift().toString());
	}//END printCurrentShiftTransactions
	/**
	 * This method prints the shift totals to the screen
	 */
	private void printShiftTotals()
	{
		System.out.println("Cash total of shift = " + currentSession.getCurrentShift().getCashTotal());
		System.out.println("EFTPOS total of shift = " + currentSession.getCurrentShift().getEFTPOSTotal());
	}//END printShiftTotals
	/**
	 * This method saves the session as a HTML file
	 */
	private void saveSessionHTML()
	{
		try {
			file.SaveSession.saveSessionHTML(currentSession);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}//END saveSession HTML
	/**
	 * This method salves session as plaintext
	 */
	private void saveSessionPlainText()
	{
		try {
			file.SaveSession.SaveSessionasPlainText(currentSession);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}//END saveSessionPlainText
	/**
	 * This method takes input and decides weather to continue a new shift
	 * by deserialising a file or creating a new shift.
	 * @param input
	 */
	private void newContinueShift(String input)
	{
		if(input == "1")
		{
			System.out.println(HeadlessInterfacePrompts.fileNamePrompt);
			String file = sc.nextLine();
			currentSession.continueShift(file);
		}
		else
		{
			currentSession.newShift();
		}
	}//END newContinueShift
	/**
	 * This method refunds a cart in shift based on cart ID.
	 */
	private void refundCartInShift()
	{
		boolean success = true;
		do {
			System.out.println(HeadlessInterfacePrompts.cartIDPrompt);
			String id = sc.nextLine();
			shiftsystem.Shift currentShift = currentSession.getCurrentShift();
			try {
				currentShift.processRefund(Integer.parseInt(id));
				success = true;
			}catch(NumberFormatException e) {
				System.out.println("Please input a numeric cart number, no letters or symbols");
				success = false;
			}
		}while(success == false);
	}//END refudnCartInShift
	/**
	 * This method creates a forced cash refund
	 */
	private void forcedRefundInCart()
	{
		boolean success = true;
		do {
			System.out.println(HeadlessInterfacePrompts.forcedRefundPrompt);
			String amount = sc.nextLine();
			shiftsystem.Shift shift = currentSession.getCurrentShift();
			try {
				shift.forceRefund(Double.parseDouble(amount));
				success = true;
			}catch(NumberFormatException e) {
				System.out.println("Please input a number");
				success = false;
			}
		}while(success == false);
	}//END forcedRefundInCart
	/**
	 * This method handles input to the cart, including adding items, 
	 * assigning a payment method (by method call), and closing the cart.
	 */
	private void newCartInShift()
	{
		shiftsystem.Shift currentShift = currentSession.getCurrentShift();
		currentShift.createNewCart();
		String input;
		do
		{
			System.out.println(HeadlessInterfacePrompts.cartOperationsPrompt);
			input = sc.nextLine();
			switch(input)
			{
			case "E":
				this.processPayment();
				try {
					file.SaveSession.createHTMLreciept(currentShift.getCurrentCart(), currentSession);
				} catch (IOException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				break;
			case "1":
				this.newItemInCart();
				break;
			case "2":
				this.removeLastItem();
				break;
			default:
				System.out.println("Invalid Input.");
			}
		}while(!input.equals("E"));
	}//END newCartInShift
	/**
	 * This method adds a new item to the current cart based on user inputs
	 */
	private void newItemInCart()
	{
		System.out.println(HeadlessInterfacePrompts.newItemPrompt);
		shiftsystem.Cart currentCart = this.currentSession.getCurrentShift().getCurrentCart();
		String input = sc.nextLine();
		double price; double quantity;
		String key; String description;
		try {
			String[] split = input.split(":", 0);
			key = split[0];
			description = split[1];
			price = Double.parseDouble(split[2]);
			quantity = Double.parseDouble(split[3]);
			currentCart.addItem(key, price, quantity, description);
		}catch(PatternSyntaxException e) { //if we have not input correct format
			System.out.println("Please follow format");
		}catch(ArrayIndexOutOfBoundsException e) { //if we follow format but dont include enough fields.
			e.printStackTrace();
			System.out.println(e.getMessage());
		}catch(NumberFormatException e){
			System.out.println("Please input numbers for price and quantity");
		}finally {
			System.out.println("Updated cart is as follows:");
			System.out.println(currentCart.toString());
			System.out.println("Cart total is = " + currentCart.getCartTotal());
		}
	}//END newItemInCart
	/**
	 * This method removes the last item from the cart.
	 */
	private void removeLastItem()
	{
		shiftsystem.Cart currentCart = currentSession.getCurrentShift().getCurrentCart();
		currentCart.removeLastItem();
		
		System.out.println("Updated cart " + currentCart.getCartID() + " is as follows:");
		System.out.println(currentCart.toString());
		System.out.println("Cart total is = " + currentCart.getCartTotal());
	}//END removeLastItem
	/**
	 * This method processes the payment type for the current cart.
	 */
	private void processPayment()
	{
		shiftsystem.Cart currentCart = currentSession.getCurrentShift().getCurrentCart();
		System.out.println("Cart total is = " + currentCart.getCartTotal());
		String input;
		do {
		System.out.println(HeadlessInterfacePrompts.processPaymentPrompt);
		input = sc.nextLine();
			switch(input)
			{
			case "C":
				currentCart.setPaymentType(shiftsystem.Enum.PAYMENTTYPE.Cash);
				break;
			case "E":
				currentCart.setPaymentType(shiftsystem.Enum.PAYMENTTYPE.EFTPOS);
				break;
			case "CC":
				currentCart.setPaymentType(shiftsystem.Enum.PAYMENTTYPE.CustomerCredit);
				break;
			}
		}while(!input.equals("C") && !input.equals("E") && !input.equals("CC"));
	}//END processPayment
}//END class 
