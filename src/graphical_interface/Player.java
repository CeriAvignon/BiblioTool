package graphical_interface;

import java.awt.BorderLayout;

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

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
			/*final JDesktopPane desktop = new JDesktopPane(); 
			//JPanel top = new JPanel(); 
			final JButton addWindow = new JButton("Add Window"); 
			addWindow.addActionListener(new ActionListener() { 
				@Override 
				public void actionPerformed(ActionEvent e) { 
					final JInternalFrame newWindow = new JInternalFrame(("Internal Window"), true, true, true, true); //1st boolean - Resizable //2nd boolean - Closable //3rd boolean - Maximizable //4th boolean - 
					JButton exit = new JButton("Close window"); 
					exit.addActionListener(new ActionListener() { 
						@Override public void actionPerformed(ActionEvent e) { 
							newWindow.dispose(); 
						} 
					}); 
					JPanel t = new JPanel(); 
					t.add(exit); 
					newWindow.add(t, BorderLayout.NORTH);
					newWindow.setVisible(true); 
					newWindow.setSize(300, 240); 
					newWindow.setMinimumSize(new Dimension(300, 240)); 
					desktop.add(newWindow); 
					newWindow.moveToFront();
				}
			}); 
			pan.add(addWindow);
			pan.window.add(BorderLayout.CENTER, desktop);
			//pan.window.setSize(500, 500); 
			pan.window.setVisible(true); 
			pan.window.setLocationRelativeTo(null);*/
			
			System.out.println("Souris sur le noeud");
		}
	}
}




