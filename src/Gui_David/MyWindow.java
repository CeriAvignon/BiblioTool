package Gui_David;

import javax.swing.JFrame;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MyWindow extends JFrame /*implements EventListener*/{
	
	public MyWindow(){
	    this.setTitle("Ma premiere fenetre Java");
	    this.setSize(800, 600);
	    Panel info_graph = new Panel();
	    this.setContentPane(info_graph);
	    this.setVisible(true);
	}
}
