package graphicalinterface.customPanels;
import java.awt.*;
import javax.swing.*;
import graphicalinterface.*;

public class GenericPage extends JPanel
{
	protected int[] screenSize = GetSystemDetails.getScreenDimensions();
	protected GraphicalInterface g;
	protected String title;
	protected JToolBar toolBar;
	
	public GenericPage(String title)
	{
		super();
		this.title = title;
		createInterface();
	}
		
	private void createInterface()
	{
		toolBar = new JToolBar();
		toolBar.setLayout(new GridLayout(1,4));
		toolBar.setPreferredSize(new Dimension(screenSize[0], 30));
	
		graphicalinterface.customcomponents.ToolBarLabel label = new graphicalinterface.customcomponents.ToolBarLabel(title);
		graphicalinterface.customcomponents.ToolBarLabel time = new graphicalinterface.customcomponents.ToolBarLabel(GetSystemDetails.getDate());	
		
		toolBar.add(label);
		toolBar.add(time);
		
		this.setLayout(new BorderLayout());
		this.add(toolBar, BorderLayout.PAGE_START);
	}
}
