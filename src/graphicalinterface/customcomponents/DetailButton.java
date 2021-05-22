package graphicalinterface.customcomponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class DetailButton extends JButton
{
	private static final Color blue_grotto = new Color(4,118,208);
	private static final Color dark_blue_grotto = new Color(0,13,22); 
	private static final Color light_blue_grotto = new Color(53,164,251);
	private static final Color ivory = new Color(234,234,224);
	private static final Color dark_ivory = new Color(134,134,124);
	private static final Color neon_green = new Color(29,198,144);
	
	private static Font defaultFont = new Font("arial", 40, 20);
	private static Font smallfont = new Font("arial", 20, 15);
	
	public DetailButton(String in)
	{
		super(in);
		
		super.setContentAreaFilled(false);
		super.setFocusPainted(false);
		
		super.setForeground(dark_blue_grotto);
		super.setFont(smallfont);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		int x = super.getWidth();
		int y = super.getHeight();
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .8f));
		g2d.setPaint(new GradientPaint(
				new Point(x,0), ivory, 
				new Point(0,y), ivory));
		g2d.fillRect(0, 0, x, y);
		g2d.dispose();
		
		super.paintComponent(g);
	}
}
