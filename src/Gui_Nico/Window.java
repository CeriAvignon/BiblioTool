package Gui_Nico;

import javax.swing.JFrame;

/**
 * 
 * Class Window extended of JFrame <br>
 * Allows to build instances of Window
 * 
 * @author Nicolas Duret
 *
 */
public class Window extends JFrame{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the class Window
	 * <p>
	 * This constructor will set the different options of the window and will build a Panel
	 * </p>
	 * 
	 */
	public Window(){
		this.setTitle("GUI BiblioTool");
		this.setSize(800,800);
		this.setLocationRelativeTo(null); //put the window at the center of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //enable the close button
		this.setVisible(true);

		this.setContentPane(new Panel()); //build a Panel
	}
}