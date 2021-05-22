package graphicalinterface.customcomponents;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class SquareButton extends JButton
{
	private static final Color blue_grotto = new Color(4,118,208);
	private static final Color dark_blue_grotto = new Color(0,13,22); 
	private static final Color light_blue_grotto = new Color(53,164,251);
	private static final Color ivory = new Color(234,234,224);
	private static final Color dark_ivory = new Color(224,224,214);
	private static final Color neon_green = new Color(29,198,144);
	
	private static Font defaultFont = new Font("arial", 40, 20);
	
	private static final long serialVersionUID = 1L;
    private Color defaultColor;
    private Color highlight, lightHighlight;
	
	public SquareButton(String in)
	{
		super(in);
		
		
		super.setForeground(ivory);
		super.setFont(defaultFont);
		
		super.setBackground(neon_green);
		
		super.setPreferredSize(new Dimension(super.getWidth(), super.getWidth()));
	
	}

}
