package graphicalinterface.customcomponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class StyledLabel extends JLabel
{
	private static final Color blue_grotto = new Color(4,118,208);
	private static final Color dark_blue_grotto = new Color(0,13,22); 
	private static final Color light_blue_grotto = new Color(53,164,251);
	private static final Color ivory = new Color(234,234,224);
	private static final Color neon_green = new Color(29,198,144);
	
	private static Font defaultFont = new Font("arial", 40, 20);
	private static Font smallfont = new Font("arial", 20, 15);
	
	public StyledLabel(String in)
	{
		super(in);
		
		super.setForeground(dark_blue_grotto);
		super.setFont(smallfont);
	}
}
