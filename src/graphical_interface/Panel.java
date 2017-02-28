package graphical_interface;

/**
 * 
 * @author Nicolas Duret
 * 
 * Class Panel extended of JPanel
 * Allows to build items in the contentPanel layer of the window
 *
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Panel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Ellipse2D.Float oval;

	public void paintComponent(Graphics graph){
		Graphics2D g2 = (Graphics2D) graph;
		int pos_x = this.getWidth()/4;
		int pos_y = this.getHeight()/4;
		//graph.fillOval(pos_x,pos_y,pos_x*2,pos_y*2); -> Oval centered
		//graph.fillOval(400,pos_y,pos_x*2,pos_y*2); 		//make an oval form
		//graph.fillOval(200,pos_y,pos_x,pos_y);
		//Font font = new Font("Courier", Font.BOLD, 20); //make a font for a String
		oval = new Ellipse2D.Float(pos_x,pos_y,100,100);
		g2.draw(oval);
		
		Player player = new Player(this); 				//build instance of Player with the actual instance
		this.addMouseMotionListener(player); 			//make a mouse motion listener which will catch the motion of the mouse
	}
	
	public Ellipse2D.Float getOval() {
		return this.oval;
	}
}