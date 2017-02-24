package graphical_interface;

/**
 * 
 * @author Nicolas Duret
 * 
 * Class Player implemented of interfaces MouseMotionListener
 * Allows to handle/manage events on the Panel
 *
 */

import java.awt.event.*;
//import javax.swing.SwingUtilities; //peut servir si j'ai besoin d'un check de click

public class Player implements MouseMotionListener
{
    Panel pan;
    
    public Player(Panel pan){
    	this.pan = pan;
    }

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(pan.getOval().contains(e.getX(), e.getY()))
			System.out.println("oui");
		else
			System.out.println("non");
		
	}
	
	public void setCenter() {	//not implemented now
		pan.setLocation(0,0);
	}

}




