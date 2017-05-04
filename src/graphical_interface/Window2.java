package graphical_interface;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Window is a class representing the interface of the project
 * 
 * @author audrey roumieux
 *  
 * Window is a class representing the interface of the project
 * it's extended of JFrame
 * 
 */
public class Window2 extends JFrame {
	
	/**
	 * the main panel of the window
	 */
	private JPanel pan = new JPanel();
	
	/**
	 * parameter to know if is log in/out
	 */
	boolean isLogIn = false;
	
	/**
	 * constructor to create Window
	 */
	public Window2(){
	    this.setTitle("BiblioTool"); //title of apps
	    this.setSize(1000, 750); 
	    this.setExtendedState(MAXIMIZED_BOTH); //take all space
	    this.setResizable(true);
	    this.setLocationRelativeTo(null);
	   
	    //add Menu by default
	    this.setJMenuBar(new BibliotoolMenuBar(this));
	    
	    
	    //add contain by default
	    this.setContentPane(pan);
	    this.MakeContaintDefault();
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
	    this.setVisible(true);
	}
	
	 
	/**
	 * To be change, update if we create different profile
	 * @author audrey roumieux
	 * It call by MakeContaintLogIn() and change the user is logIn
	 */
	public void MakeContaintProfile(){
		//check connection of profile If we are connect, we look this profile page
		this.isLogIn = true;
 	   
		this.setContentPane(new ProfilePanel());
		this.getContentPane().revalidate();
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * It use to change the contain of the windows, when we click on "log in" menu
	 */
	public void MakeContaintLogIn(){
		this.setContentPane(new LogPanel());
		this.getContentPane().revalidate();
		
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * It use to change the contain of the windows, when we click on "sing up" menu
	 */
	public void MakeContaintSign(){
		this.setContentPane(new SignPanel());
		this.getContentPane().revalidate();
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * It use by MakeContaintSearch(), when we have the result of search after 
	 */
	public void MakeContaintResultSearch(){
		
		this.setContentPane(new ResultSearchPanel());
		this.getContentPane().revalidate();
	}
	
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * It use by makeMenu() when we click on search menu
	 */
	public void MakeContaintSearch(){
		this.setContentPane(new FormMetadata());
		//this.setContentPane(new FormMetadata());
		this.getContentPane().revalidate();
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * It use by makeMenu() when we click on search menuPDF
	 */
	public void MakeContaintSearchPDF(){
		this.setContentPane(new FormPDF());
		//this.setContentPane(new FormPDF());
		this.getContentPane().revalidate();
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * It use to change the contain of the window when we click on help menu
	 */
	public void MakeContaintHelp(){
		
		pan.removeAll();
		
		// to be change by the explain Help
		this.setLayout(new GridLayout(1,1)); 
		this.getContentPane().add(new JPanel().add(new JLabel("Text of help")));
		this.getContentPane().revalidate();
	}
	
	
	/**
	 * @author audrey roumieux
	 * It use by the constructor or the "log out" menu for change the contain by default
	 */
	public void MakeContaintDefault(){
		
		this.setContentPane(new DefaultPanel());
		this.getContentPane().revalidate();
		
	} 
	
}
