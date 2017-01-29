package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 * Window is a class representing the interface of the project
 * 
 * @author audrey roumieux
 *  
 * Window is a class representing the interface of the project
 * it's extended of JFrame
 * 
 */
public class Window extends JFrame {
	
	/**
	 * the main panel of the window
	 */
	private JPanel pan = new JPanel();
	
	/**
	 * parameter to know if is log in/out
	 */
	boolean isLogIn = false;
	
	/**
	 * The onglet 
	 */
	private JTabbedPane onglets = new JTabbedPane();
	
	/**
	 * constructor to create window
	 */
	public Window(){
	    this.setTitle("BiblioTool"); //title of apps
	    this.setSize(1000, 750); 
	    this.setExtendedState(MAXIMIZED_BOTH); //take all space
	    this.setResizable(true);
	    this.setLocationRelativeTo(null);
	    
	    //add Menu by default
	    this.setJMenuBar(new BibliotoolMenuBar(this));
	    
	    //add contain by default
	    this.MakeContaintDefault();
	    //this.setContentPane(onglets);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
	    this.setVisible(true);
	}
	
	 

	/**
	 * @author audrey roumieux
	 * @return pan
	 * It call by MakeContaintLogIn() and change the user is logIn
	 */
	public void MakeContaintProfile(){
		//check connection of profile 
		
		//If we are connect, we look this profile page
		Window.this.isLogIn = true;
 	   
 	   pan.removeAll();
 	   // to be change
 	   Window.this.setLayout(new GridLayout(1,1)); 
 	   Window.this.getContentPane().add(new JPanel().add(new JLabel("Page profil")));
 	   
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * @return pan
	 * It use by makeMenu() when we click on log in menu
	 */
	public void MakeContaintLogIn(){
	
		System.out.println("value isLogIn in log-in "+this.isLogIn);
		pan.removeAll();
		
		JButton LogInButton = new JButton("Log in");
		LogInButton.setBounds(750, 250, 40, 10);
		LogInButton.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
					Window.this.MakeContaintProfile();
		       } 
		});
		
		//to be change by Log Form
		this.setLayout(new GridLayout(2,3)); 
		this.getContentPane().add(new JPanel().add(new JLabel("Form of connection")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(LogInButton));
		
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * @return pan
	 * It use by makeMenu() when we click on sing up menu
	 */
	public void MakeContaintSign(){
		
		pan.removeAll();
		JButton SignButton = new JButton("sign up");
		SignButton.setBounds(750, 250, 40, 10);
		SignButton.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
		    	  // to do complete by insertMenmber function
		       } 
		    });
		
		//to be change by Sign Form
		this.setLayout(new GridLayout(3,2));
		this.getContentPane().add(new JPanel().add(new JLabel("Formulaire d'inscription")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(SignButton));
		
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * @return pan
	 * It use by MakeContaintSearch() when we have the result of search
	 */
	public void MakeContaintResultSearch(){
		pan.removeAll();
		
 	    /* to be change by graph result */
		this.setLayout(new GridLayout(2,3)); 
		this.getContentPane().add(new JPanel().add(new JLabel("Graph result")));
		/*
		displayGraph previewJFrame = new displayGraph();
		previewJFrame.script("timeframe1.gexf");
		this.getContentPane().add(previewJFrame);
		*/
	}
	
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * @return pan
	 * It use by makeMenu() when we click on search menu
	 */
	public void MakeContaintSearch(){
		
		pan.removeAll();
		
		//to be change by the search form
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(750, 250, 40, 10);
		searchButton.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
					Window.this.MakeContaintResultSearch();
		       } 
		    });
		
		//to be change by the search form
		this.setLayout(new GridLayout(2,3)); 
		this.getContentPane().add(new JPanel().add(new JLabel("Search Form")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(searchButton));
		
		/*
		this.setLayout(new GridLayout(1,1));
		this.getContentPane().add(new Form());
		*/
		
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * @return pan
	 * It use by makeMenu() when we click on search menu
	 */
	public void MakeContaintSearchPDF(){
		
		pan.removeAll();
		//to be change by the search form
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(750, 250, 40, 10);
		searchButton.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
					Window.this.MakeContaintResultSearch();
		       } 
		    });
		
		//to be change by the search form
		this.setLayout(new GridLayout(2,3)); 
		this.getContentPane().add(new JPanel().add(new JLabel("Search Form with PDF")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(new JLabel("")));
		this.getContentPane().add(new JPanel().add(searchButton));
		/*
		this.setLayout(new GridLayout(1,1));
		this.getContentPane().add(new Form());
		*/
	}
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * @return pan
	 * It use by makeMenu() when we click on help menu
	 */
	public void MakeContaintHelp(){
		
		pan.removeAll();
		
		// to be change by the explain Help
		this.setLayout(new GridLayout(1,1)); 
		this.getContentPane().add(new JPanel().add(new JLabel("Text of help")));
		
	}
	
	
	/**
	 * @author audrey roumieux
	 * @return Void
	 * It use by the constructor or the log out menu for change contain by default
	 */
	public void MakeContaintDefault(){
		
		JLabel label = new JLabel("Welcome on the BiblioTool application");
		Font police = new Font("Tahoma", Font.BOLD, 16);  // change the type, size
		label.setFont(police);
		label.setForeground(Color.blue); // change the color
		pan.add(label);
	} 
	
	
	
	
}
