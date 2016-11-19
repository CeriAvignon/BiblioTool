package Gui_Nico;

/**
 * 
 * @author Nicolas Duret
 * 
 * Class Panel extended of JPanel
 * Allows to build items in the contentPanel layer of the window
 *
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class Panel extends JPanel{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics graph){
		int pos_x = this.getWidth()/4;
		int pos_y = this.getHeight()/4;
		//graph.fillOval(pos_x,pos_y,pos_x*2,pos_y*2); -> Oval centered
		graph.fillOval(400,pos_y,pos_x*2,pos_y*2); 		//make an oval form
		graph.fillOval(0,pos_y,pos_x,pos_y);
		Font font = new Font("Courier", Font.BOLD, 20); //make a font for a String
		graph.setFont(font);
		graph.setColor(Color.red);
		graph.drawString("String test", 10, 20); 		//make a String with the label specified 
		
		Player player = new Player(this); 				//build instance of Player with the actual instance
		this.addMouseListener(player); 					//make a mouse listener which will catch button pressed and released
		this.addMouseMotionListener(player); 			//make a mouse motion listener which will catch the motion of the mouse
	}
}