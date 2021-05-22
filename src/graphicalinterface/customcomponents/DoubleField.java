package graphicalinterface.customcomponents;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
/**
 * This class is an extention of a jtext field that only allows
 * numbers, decimal places, and backspace to be input to the field. 
 * Stops illegal inputs from user. 
 * @author email
 * @version 1.0
 */
public class DoubleField extends JTextField 
{
	private static final long serialVersionUID = 1L;
	private JTextField point = this;
	/**
	 * Constructor
	 * @param size
	 */
	public DoubleField(int size)
	{
		super(size);
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				String value = point.getText();
				int l = value.length();
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9'  //allow numbers
						|| (e.getKeyChar() == '.' && !point.getText().contains("."))  //allow only 1 decimal place
						|| e.getKeyChar() == '\b') //allow backspace
				{
					point.setEditable(true);
				}
				else //anything else and we cannot edit field. 
				{
					point.setEditable(false);
				}
			}
		});
	}//END constructor
}//END class
