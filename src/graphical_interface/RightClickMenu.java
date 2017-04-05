package graphical_interface;

import org.gephi.graph.api.Node;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;

/**
 * @author yang shuai
 *
 */
public class RightClickMenu extends JPopupMenu implements ActionListener{ 
    private Node node;
	JMenuItem mntmHide = new JMenuItem("Masquer ce noeud");
	JMenuItem mntmDevelope = new JMenuItem("Déveloper");
	JMenuItem mntmDomain = new JMenuItem("Créer une domaine");
    public RightClickMenu(Node node){}
    
	public RightClickMenu(JPanel center,Node node){

		add(mntmHide);
		add(mntmDevelope);
		add(mntmDomain);
		mntmHide.addActionListener( this );
		mntmDevelope.addActionListener( this );
		this.node = node ;
	}
	public void addNode(Node node)
	{
		this.node=node;
	}
	
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getActionCommand()==mntmHide.getText())
    	{
        	hideNode();
    	}
    	else if(e.getActionCommand()==mntmDevelope.getText())
    	{
    		
    	}
    	else if(e.getActionCommand()==mntmDomain.getText())
    	{
    		
    	}
    }
    
    public void hideNode()
    {
	  	  ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
	      Workspace workspace = pc.getCurrentWorkspace();
	      for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes()) {   
	    	  if(node==this.node)
	    	  {
              	System.out.println(node.getLabel());
	    	  }
	      }
	      for (Edge edge : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getEdges()) {   
	    	  if(edge.getSource().getId() == this.node.getId() || edge.getTarget().getId() ==this.node.getId() )
	    	  {
              	System.out.println(edge.getId());
	    	  }
	      }

    }
	
}
