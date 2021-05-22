package graphicalinterface.customcomponents;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import graphicalinterface.customPanels.GenericPage;

public class BackgroundPanel extends GenericPage
{	
	private Image background_image;
	public BackgroundPanel(String in)
	{
		super(in);
		this.background_image = new ImageIcon("background.jpg").getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{		
		super.paintComponent(g);
		//g.drawImage(background_image, 0, 0, super.getWidth(), super.getHeight(), null);
	}
}
