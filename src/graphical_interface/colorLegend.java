package graphical_interface;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class colorLegend extends JLabel {
	
	public colorLegend() {

			ImageIcon red = new ImageIcon(new ImageIcon("Rond_rouge.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));;
	        ImageIcon green = new ImageIcon(new ImageIcon("Rond_vert.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));;
	        
	        this.setText("L�gende :");
	        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	        this.add(Box.createRigidArea(new Dimension(150,0)));
	          
	        JLabel iconRed = new JLabel(red);
	        iconRed.setText("PDF non disponible");
	        this.add(iconRed);
	        this.add(Box.createRigidArea(new Dimension(50,0)));
	        
	        JLabel iconGreen = new JLabel(green);
	        iconGreen.setText("PDF disponible");
	        this.add(iconGreen);
		
		
	}
}
