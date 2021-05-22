package graphicalinterface.customcomponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class StyledButton extends JButton
{
	private static final Color blue_grotto = new Color(4,118,208);
	private static final Color light_blue_grotto = new Color(53,164,251);
	private static final Color ivory = new Color(234,234,224);
	
	private static Font defaultFont = new Font("arial", 40, 20);
	
	public StyledButton(String in)
	{
		super(in);
		
		super.setContentAreaFilled(false);
		super.setFocusPainted(false);
		
		super.setForeground(ivory);
		super.setFont(defaultFont);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		int x = super.getWidth();
		int y = super.getHeight();
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9f));
		g2d.setPaint(new GradientPaint(
				new Point(x,0), blue_grotto, 
				new Point(0,y), light_blue_grotto));
		g2d.fillRect(0, 0, x, y);
		g2d.dispose();
		
		super.paintComponent(g);
	}
}
