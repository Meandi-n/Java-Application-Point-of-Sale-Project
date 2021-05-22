package file;
import java.awt.*;
import java.awt.print.*;
import javax.swing.JEditorPane;
/**
 * This class extends JEditorPane and allows us to print the contents
 * of the pane. This pane is used to display cash ups and reciepts. 
 * @author Ryan
 * @version 1.0
 */
public class Printer extends JEditorPane implements Printable
{
	private static final long serialVersionUID = 1L;
	final Component comp;
	/**
	 * Constructor
	 * @param comp
	 */
	public Printer(Component comp)
	{
		this.comp = comp;
	}//END constructor
	/**
	 * method print, overrides JEditorPane method. 
	 */
	@Override
	public int print(Graphics g, PageFormat format, int pageIndex) throws PrinterException 
	{
		if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        // get the bounds of the component
        Dimension dim = comp.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();

        // get the bounds of the printable area
        double pHeight = format.getImageableHeight();
        double pWidth = format.getImageableWidth();

        double pXStart = format.getImageableX();
        double pYStart = format.getImageableY();

        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;
        
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        comp.paint(g2);
        
        return Printable.PAGE_EXISTS;
	}//END print
}//END class
