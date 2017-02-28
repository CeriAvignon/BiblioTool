package graphical_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Nicolas Duret
 * 
 * Class Test
 * Launch the software and the window
 *
 */

public class Main {
	public static void main(String[] args) {
		//Window window = new Window();
		//window.getClass();
		JFrame window = new JFrame("Internal Frames"); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		final JDesktopPane desktop = new JDesktopPane(); 
		JPanel top = new JPanel(); 
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
		top.add(addWindow); 
		window.add(BorderLayout.NORTH, top);
		window.add(BorderLayout.CENTER, desktop);
		window.setSize(500, 500); 
		window.setVisible(true); 
		window.setLocationRelativeTo(null);
	}
}