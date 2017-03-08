package graphical_interface;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.Node;
import org.gephi.preview.api.PreviewMouseEvent;
import org.gephi.preview.api.PreviewProperties;
import org.gephi.preview.spi.PreviewMouseListener;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = PreviewMouseListener.class)
public class MouseListenerTemplate implements PreviewMouseListener {
	
    public void mouseClicked2(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace,JPanel pane) {
		//if it's left click then show its information
		if(event.button.name()=="RIGHT"){
    		for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes()) {
    	          if (clickingInNode(node, event)) {
    	        	  // si on click sur un noeud, il affiche le popup menu
    		        	System.out.println("on the node " + node.getLabel());
    	                 properties.putValue("display-label.node.id", node.getId());    
    	                 // debug of node clicked
    	                 System.err.println("Node " + node.getLabel() + " clicked!");//System.out is ignored in Netbeans platform applications!!
    	                 event.setConsumed(true);//So the renderer is executed and the graph repainted
    	                 return;	
    	            }
    	        }	
        }
        properties.removeSimpleValue("display-label.node.id");
        event.setConsumed(true);//So the renderer is executed and the graph repainted
    }
	
	@Override
    public void mouseClicked(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace) {
		//if it's left click then show its information
		if(event.button.name()=="RIGHT"){
    		for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes()) {
    	          if (clickingInNode(node, event)) {
    	        	  // si on click sur un noeud, il affiche le popup menu
    	        		//RightClickMenu rcmenu=new RightClickMenu(this,node);
    		    		JPopupMenu popup = new JPopupMenu();
    		    		JMenuItem mntmHide = new JMenuItem("Masquer ce noeud");
    		    		popup.add(mntmHide);
    		        	System.out.println("on the node " + node.getLabel());
    	                 properties.putValue("display-label.node.id", node.getId());    
    	                 // debug of node clicked
    	                 System.err.println("Node " + node.getLabel() + " clicked!");//System.out is ignored in Netbeans platform applications!!
    	                 JOptionPane.showMessageDialog(null, "Node " + node.getLabel() + " clicked!" );
    	                 event.setConsumed(true);//So the renderer is executed and the graph repainted
    	                 return;	
    	            }
    	        }	
        }
        properties.removeSimpleValue("display-label.node.id");
        event.setConsumed(true);//So the renderer is executed and the graph repainted
    }

    @Override
    public void mousePressed(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace) {
    	
    }

    @Override
    public void mouseDragged(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace) {
    }

    @Override
    public void mouseReleased(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace) {
    }

    public boolean clickingInNode(Node node, PreviewMouseEvent event) {
        float xdiff = node.x() - event.x;
        float ydiff = -node.y() - event.y;//Note that y axis is inverse for node coordinates
        float radius = node.size();

        return xdiff * xdiff + ydiff * ydiff < radius * radius;
    }
}
