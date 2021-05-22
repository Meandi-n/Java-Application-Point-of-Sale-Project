package graphicalinterface.customcomponents;

import java.awt.*;
import javax.swing.*;

public class CashTotalPaidPanel extends JPanel
{
	private static Font defaultFont = new Font("arial", 40, 20);
	private static final Color ivory = new Color(234,234,224);
	private static final Color red = new Color(252, 90, 90);
	private static final Color neon_green = new Color(29,198,144);
	
	public CashTotalPaidPanel(JTextField field)
	{
		JLabel cartLabel = new graphicalinterface.customcomponents.StyledLabel("Cash Paid = ");
		
		this.add(cartLabel);
		this.add(field);
		//cart total field 
		this.setBackground(neon_green);
		
		this.setOpaque(true);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 0;
		c2.gridy = 0;
		this.add(cartLabel, c2);
		c2.gridx = 1;
		c2.gridy = 0;
		this.add(field, c2);
		
		
		field.setForeground(Color.black);
		field.setFont(defaultFont);
		field.setBorder(null);
		
		cartLabel.setForeground(ivory);
		cartLabel.setFont(defaultFont);
	}
}
