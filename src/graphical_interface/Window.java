package graphical_interface;

/**
 * 
 * @author Audray Roumieux 
 * 
 * Création de la classe Window qui créer la fenêtre
 *
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Window extends JFrame{
	private JPanel pan = new JPanel();
	
	public Window(){
	    this.setTitle("BiblioTool"); 
	    this.setSize(1000, 750); //to be change
	    this.setExtendedState(MAXIMIZED_BOTH); 
	    this.setResizable(true);
	    this.setLocationRelativeTo(null);
	    this.setJMenuBar(MakeMenu());
	    
	    //this.setJMenuBar(MakeContaint());
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
	    this.setVisible(true);
	}
	
	private JMenuBar MakeMenu(){
		ImageIcon icon = new ImageIcon("path_to_the_image.png"); //To change
		JMenuItem menuCERI = new JMenuItem("http://ceri.univ-avignon.fr/", icon);
		JButton menuLog = new JButton("Log In");
		JButton menuSign = new JButton("Sign Up");
		JButton menuSearch = new JButton("Search");
		JButton menuHelp = new JButton("Help");
	    
		menuLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		menuSign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		menuSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new Formulaire();
			}
		});
		
		menuHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuCERI);
		menuBar.add(menuSearch);
		menuBar.add(menuLog);
		menuBar.add(menuSign);
		menuBar.add(menuHelp);
		return menuBar;
	}
	
	/*private JMenuBar MakeContaint(){
		return 1;
	} */
}
