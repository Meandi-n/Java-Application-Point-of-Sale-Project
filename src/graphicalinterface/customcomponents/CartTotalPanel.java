package graphicalinterface.customcomponents;

import java.awt.*;
import javax.swing.*;

public class CartTotalPanel extends JPanel
{
	private static Font defaultFont = new Font("arial", 40, 20);
	private static final Color ivory = new Color(234,234,224);
	private static final Color red = new Color(252, 90, 90);
	
	public CartTotalPanel(JTextField cartTotalField)
	{
		//cart total field 
		this.setBackground(red);
		
		this.setOpaque(true);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		
		JLabel cartLabel = new graphicalinterface.customcomponents.StyledLabel("Cart Total = ");
		
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 0;
		c2.gridy = 0;
		this.add(cartLabel, c2);
		c2.gridx = 1;
		c2.gridy = 0;
		this.add(cartTotalField, c2);
		
		
		cartTotalField.setForeground(ivory);
		cartTotalField.setFont(defaultFont);
		cartTotalField.setOpaque(false);
		cartTotalField.setBorder(null);
		
		cartLabel.setForeground(ivory);
		cartLabel.setFont(defaultFont);
		
		this.setBorder(BorderFactory.createLineBorder(Color.red));
	}
}
