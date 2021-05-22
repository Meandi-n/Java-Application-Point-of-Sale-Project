package graphicalinterface.customcomponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class UnimplementedButton extends JButton
{
	private static Font defaultFont = new Font("arial", 40, 20);
	
	public UnimplementedButton(String in)
	{
		super(in);
		
		super.setBackground(new Color(192,192,192));
		super.setForeground(new Color(128,128,128));
		super.setFont(defaultFont);
		
		super.setToolTipText("Unimplemented at this time");
	}
}
