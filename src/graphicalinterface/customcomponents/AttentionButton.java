package graphicalinterface.customcomponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class AttentionButton extends JButton
{
	private static final Color blue_grotto = new Color(4,118,208);
	private static final Color dark_blue_grotto = new Color(0,13,22); 
	private static final Color light_blue_grotto = new Color(53,164,251);
	private static final Color ivory = new Color(234,234,224);
	private static final Color neon_green = new Color(29,198,144);
	private static final Color dark_neon_green = new Color(39,208,154);
	
	private static Font defaultFont = new Font("arial", 40, 20);
	private static Font smallfont = new Font("arial", 10, 10);
	
	public AttentionButton(String in)
	{
		super(in);
		
		super.setContentAreaFilled(false);
		super.setFocusPainted(false);
		
		super.setForeground(ivory);
		super.setFont(defaultFont);
		super.setBorder(new LineBorder(ivory));
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		int x = super.getWidth();
		int y = super.getHeight();
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9f));
		g2d.setPaint(new GradientPaint(
				new Point(x,0), neon_green, 
				new Point(0,y), dark_neon_green));
		g2d.fillRect(0, 0, x, y);
		g2d.dispose();
		
		super.paintComponent(g);
	}
}
