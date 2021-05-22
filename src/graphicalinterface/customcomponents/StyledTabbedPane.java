package graphicalinterface.customcomponents;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;

public class StyledTabbedPane extends JTabbedPane
{
	private static Font defaultFont = new Font("arial", 40, 15);
	private static final Color ivory = new Color(234,234,224);
	public StyledTabbedPane()
	{
		super();
		
		this.setFont(defaultFont);
		this.setBackground(ivory);
	}
}
