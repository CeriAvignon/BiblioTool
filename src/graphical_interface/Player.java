package graphical_interface;

/**
 * 
 * @author Nicolas Duret
 * 
 * Class Player implemented of interfaces MouseMotionListener
 * Allows to handle/manage events on the Panel
 *
 */

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
//import javax.swing.JOptionPane;

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
		if(pan.getOval().contains(e.getX(), e.getY())) {
			//JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
			final JDesktopPane desktop = new JDesktopPane();
			
			final JInternalFrame newwindow = new JInternalFrame("internal window", true, true, true, true);
			newwindow.dispose();
			newwindow.setVisible(true);
			newwindow.setSize(300,240);
			newwindow.setMinimumSize(new Dimension(300,240));
			desktop.add(newwindow);
			newwindow.moveToFront();
			System.out.println("oui");
		}
	}
}




