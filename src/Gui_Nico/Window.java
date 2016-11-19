package Gui_Nico;

/**
 * 
 * @author Nicolas Duret
 * 
 * Class Window extended of JFrame
 * Allows to build instances of Window
 *
 */

import javax.swing.JFrame;

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;

	public Window(){
		this.setTitle("GUI BiblioTool");
		this.setSize(800,800);
		this.setLocationRelativeTo(null); //put the window at the center of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //enable the close button
		this.setVisible(true);

		this.setContentPane(new Panel()); //build a Panel
	}
}