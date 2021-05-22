package graphicalinterface.customcomponents;

import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.*;

public class StyledJTable extends JTable
{
	private static Font defaultFont = new Font("arial", 40, 15);
	private static final Color ivory = new Color(234,234,224);
	
	public StyledJTable(TableModel model)
	{
		super(model);
		
		this.setFont(defaultFont);
		this.setRowHeight(this.getRowHeight()+10);
		this.showHorizontalLines = false;
	}
}
