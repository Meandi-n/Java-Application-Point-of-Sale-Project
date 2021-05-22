package file;
import java.awt.print.*;
import javax.swing.*;
/**
 * This class holds the static method for printing 
 * using the windows print job system.  It is fed a printable java
 * component.  
 * @author email
 * @version 1.0
 */
public class Print 
{
	/**
	 * This method takes input of a component and feeds it to a printer
	 * via the printer job. 
	 * @param reciept
	 */
	public static void printReciept(JEditorPane reciept)
	{
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PageFormat preFormat = pjob.defaultPage();
		preFormat.setOrientation(PageFormat.PORTRAIT);
		PageFormat postFormat = pjob.pageDialog(preFormat);
		
		if(preFormat != postFormat)
		{
			pjob.setPrintable(new file.Printer(reciept), postFormat);
			if(pjob.printDialog())
			{
				try {
					pjob.print();
				} catch (PrinterException e) {
					@SuppressWarnings("unused")
					graphicalinterface.WarningWindow warning = new graphicalinterface.WarningWindow("Could not print document");
					e.printStackTrace();
				}
			}
		}
	}//END printReciept
}//END class
