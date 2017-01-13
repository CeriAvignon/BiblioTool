package Gui_Nico;

import java.awt.event.*;

import javax.swing.SwingUtilities;

/**
 * 
 * Class Player implemented of interfaces MouseListener and MouseMotionListener<br>
 * Allows to handle/manage events on the Panel
 * 
 * @author Nicolas Duret
 *
 */
public class Player implements MouseListener, MouseMotionListener
{
	/**
	 * An object of the class Panel that we'll use to get the location of the current panel
	 * 
	 * @see Player#Player(Panel)
	 * @see Player#mousePressed(MouseEvent)
	 * @see Player#mouseReleased(MouseEvent)
	 * @see Player#setCenter()
	 */
    Panel pan;
    
    /**
	 * Two int that will be used to know the position of the mouse
	 * 
	 * @see Player#mousePressed(MouseEvent)
	 * @see Player#mouseDragged(MouseEvent)
	 */
    int mouse_x, mouse_y;
    
    /**
	 * Two int that will be used to know the old (save) position of the panel
	 * 
	 * @see Player#mousePressed(MouseEvent)
	 * @see Player#mouseReleased(MouseEvent)
	 */
    int old_x, old_y;

    /**
	 * Two int that will be used to know the new position of the panel
	 * 
	 * @see Player#mousePressed(MouseEvent)
	 * @see Player#mouseReleased(MouseEvent)
	 * @see Player#mouseDragged(MouseEvent)
	 */
    int new_x, new_y;
    
    /**
     * Constructor of the class Panel<br>
     * It will set the private panel with the parameter pan
     * @param pan
     * 				Panel that we want to move
     */
    public Player(Panel pan){
    	this.pan = pan;
    }

    /**
     * Inherited method that will know when the clicks of the mouse are pressed<br>
     * This method will be used to set the vars when the left click of the mouse is pressed.
     * 
     * @param e
     * 			The event of the mouse
     */
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

    /**
     * Inherited method that will know when the clicks of the mouse are released<br>
     * This method is used to that when the click of the mouse is released, the panel will move at the current mouse_position.
     * 
     * @param e
     * 			The event of the mouse
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
    	if(SwingUtilities.isLeftMouseButton(e))
    		pan.setLocation(old_x+new_x, old_y+new_y);
    }

    /**
     * Automatically generated
     */
	@Override
	public void mouseClicked(MouseEvent arg0) {}

    /**
     * Automatically generated
     */	
	@Override
	public void mouseEntered(MouseEvent e) {}

    /**
     * Automatically generated
     */
	@Override
	public void mouseExited(MouseEvent e) {}

    /**
     * Inherited method that will know when the clicks of the mouse are dragged<br>
     * When the mouse is dragged we continually update the new position of the Panel pan
     * 
     * @param e
     * 			The event of the mouse
     */
	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)){ //while the left click is hold we measure the new position
			new_x = e.getX()-mouse_x;	//with this we have the actual shifting/travel of the mouse
			new_y = e.getY()-mouse_y;;
		}
	}

    /**
     * Automatically generated
     */
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	/**
	 * Set the location of the Panel pan at the center of the window
	 */
	public void setCenter() {
		pan.setLocation(0,0);
	}

}




