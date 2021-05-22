package graphicalinterface.customcomponents;

import java.awt.*;
import javax.swing.*;

public class ToolBarLabel extends JLabel
{
	private static Font smallfont = new Font("arial", 20, 15);
	
	public ToolBarLabel(String in)
	{
		super(in);
		
		this.setFont(smallfont);
	}
}


