package Gui_Nico;

/**
 * 
 * @author Nicolas Duret
 * 
 * Class Player implemented of interfaces MouseListener and MouseMotionListener
 * Allows to handle/manage events on the Panel
 *
 */

import java.awt.event.*;

import javax.swing.SwingUtilities;

public class Player implements MouseListener, MouseMotionListener
{
    Panel pan;
    int mouse_x, mouse_y; 	//will contain position of the mouse
    int old_x, old_y; 		//will contain position of the panel
    int new_x, new_y;		//will contain new position of the panel
    
    public Player(Panel pan){
    	this.pan = pan;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    	if(SwingUtilities.isLeftMouseButton(e)){ //if left button pressed
    		mouse_x = e.getX();
    		mouse_y = e.getY();
    		old_x = pan.getLocation().x;
    		old_y = pan.getLocation().y;
    	}
    }

    @Override
    public void mouseReleased(MouseEvent e)	//when the mouse is released, the panel will move at the mouse_position
    {
    	if(SwingUtilities.isLeftMouseButton(e))
    		pan.setLocation(old_x+new_x, old_y+new_y);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){ //while the left click is hold we measure the new position
			new_x = e.getX()-mouse_x;	//with this we have the actual shifting/travel of the mouse
			new_y = e.getY()-mouse_y;;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	public void setCenter() {	//not implemented now
		pan.setLocation(0,0);
	}

}




