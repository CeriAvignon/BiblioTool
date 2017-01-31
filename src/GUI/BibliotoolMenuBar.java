package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * It use by the class Window for create the menu
 * @author audrey roumieux
 *
 */
public class BibliotoolMenuBar extends JMenuBar {

	/**
	 * The contents of File in the menu bar
	 */
	JMenu menuFile = new JMenu("File");
	JMenuItem menuFileNewWindow = new JMenuItem("New window");
	JMenuItem menuFileOpenFile = new JMenuItem("Open File");
	JMenuItem menuFileSaveAs = new JMenuItem("Save As ...");
	//JMenuItem menuFilePrint = new JMenuItem("Print");
	JMenuItem menuFileExit = new JMenuItem("Exit");
	
	/**
	 * The contents of Profile in the menu bar
	 */
	JMenu menuProfile = new JMenu("Profile");
	JMenuItem menuLogIn = new JMenuItem("Log In");
	JMenuItem menuLogOut = new JMenuItem("Log Out");
	JMenuItem menuSign = new JMenuItem("Sign Up");
	
	/**
	 * The contents of Edit in the menu bar
	 */
	JMenu menuEdit = new JMenu("Edit");
	JMenuItem menuSearch = new JMenuItem("Search");
	//JMenuItem menuSearchPDF = new JMenuItem("Search with PDF");
	JMenuItem menuCopy = new JMenuItem("Copy");
	JMenuItem menuPaste = new JMenuItem("Paste");
	
	/**
	 * The contents of Help in the menu bar
	 */
	JMenu menuHelp = new JMenu("Help");
	JMenuItem menuAboutBibliotool = new JMenuItem("About BiblioTool ...");
	
	Window window;
	
	/**
	 * constructor to create the menu
	 * @param window
	 */
	public BibliotoolMenuBar(Window window){
		 this.window = window;
		
		addItemsToMenu(this);
		
		menuLogOut.addActionListener(new ActionListener() {
 		   public void actionPerformed(ActionEvent e) {
 			  window.isLogIn = false;
 			  addItemsToMenu(BibliotoolMenuBar.this);
 			  window.MakeContaintDefault();
 		   }
 	   });
		
		menuLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItemsToMenu(BibliotoolMenuBar.this);
				window.MakeContaintLogIn();
			}
		});
		
		menuSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.MakeContaintSign();
			}
		});
		
		menuSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.MakeContaintSearch();
				
			}
		});
		
		/*
		menuSearchPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.MakeContaintSearchPDF();
			}
		});*/
		
		menuAboutBibliotool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.MakeContaintHelp();
			}
		});
	}
	
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * @param component
	 * @return boolean
	 * it use by addIemsToMenu() for add item on menu if is not present in the menu
	 */
	private boolean addIfNotPresent(JMenuBar menuBar, JComponent component ) {
		  if(!Arrays.asList(menuBar.getComponents()).contains(component)) {
		    menuBar.add(component);
		    return true;
		  }
		  return false;
		}
	
	/**
	 * @author audrey roumieux
	 *
	 */
	 class OpenD implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		      JFileChooser c = new JFileChooser();
		      c.showOpenDialog(window);
		    }
		  }
	 
	 /**
	  * @author audrey roumieux
	  *
	  */
	 class SaveD implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		      JFileChooser c = new JFileChooser();
		      c.showSaveDialog(window);
		    }
		  }
	
	/**
	 * @author audrey roumieux
	 * @param menuBar
	 * it use by the constructor for add all items in the menu
	 */
	private void addItemsToMenu(JMenuBar menuBar){

		addIfNotPresent(menuBar, menuFile);
		menuFileNewWindow.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  
			  }        
		});
		menuFileNewWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		menuFile.add(menuFileNewWindow);
		menuFileOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		menuFileOpenFile.addActionListener(new OpenD());
		menuFile.add(menuFileOpenFile);
		menuFileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		menuFileSaveAs.addActionListener(new SaveD());
		menuFile.add(menuFileSaveAs);
		//menuFile.add(menuFilePrint);
		menuFile.addSeparator();
		menuFileExit.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		        System.exit(0);
		      }        
		 });
		menuFile.add(menuFileExit);
		
		
		addIfNotPresent(menuBar, menuEdit);
		menuEdit.add(menuSearch);
		//menuEdit.add(menuSearchPDF);
		menuEdit.addSeparator();
		
		menuEdit.add(menuCopy);
		
		menuEdit.add(menuPaste);
		
		addIfNotPresent(menuBar, menuProfile);
		if(!window.isLogIn){
			//if(Arrays.asList(menuProfile.getComponents()).contains(menuLogOut)) {
				menuProfile.remove(menuLogOut);
			//}
			 menuProfile.add(menuLogIn);
		} 
		else { 
			menuProfile.removeAll();
			menuProfile.add( menuLogOut);
			
		}
		menuProfile.add( menuSign);
		
		addIfNotPresent(menuBar, menuHelp);
		menuHelp.add(menuAboutBibliotool);
		
		// For refresh the menu
		validate();
		repaint();
	}
	
}
