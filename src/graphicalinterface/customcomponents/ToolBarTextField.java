package graphicalinterface.customcomponents;

import java.awt.*;
import javax.swing.*;

public class ToolBarTextField extends JTextField
{
	private static Font smallfont = new Font("arial", 20, 15);
	
	public ToolBarTextField(String in)
	{
		super(in);
		
		this.setFont(smallfont);
	}
}


