package headlessinterface;
/**
 * This class just holds all the prompt strings that get printed to the user for the headless interface. 
 * Makes the software less painful on the eyes.
 * 
 * @author Ryan
 * @version 1.0
 */
public class HeadlessInterfacePrompts 
{
	public static String shiftInit = "Welcome:\n[0]:Start new shift\n[1]:ContinueShift";
	public static String fileNamePrompt = "Please input a file Name:";
	public static String shiftOperationsPrompt = "[E]:Exit\n[1]:New cart\n[2]:Refund cart\n[3]:Force a refund of cash\n[4]:Save session as CSV\n[5]:Save session as HTML\n[6]:Save session as serial file";
	public static String cartOperationsPrompt = "[E]:Exit and create reciept\n[1]:Add Item\n[2]:Remove last item";
	public static String newItemPrompt = "Adding new item, please follow the format as follows. key:desc:price:quantity";
	public static String processPaymentPrompt = "[C]:Cash\n[E]:Eftpos\n[CC]:Customer Credit";
	public static String cartIDPrompt = "Enter Cart ID:";
	public static String forcedRefundPrompt = "How many dollars cash to refund?";
}//END class
