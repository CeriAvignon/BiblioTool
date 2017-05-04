package graphical_interface;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DefaultPanel extends JPanel {
	
	public DefaultPanel(){
		
		JLabel label = new JLabel("Welcome on the BiblioTool application");
		Font police = new Font("Tahoma", Font.BOLD, 16);  // change the type, size
		label.setFont(police);
		label.setForeground(Color.blue); // change the color
		this.add(label);
	} 
	
}
