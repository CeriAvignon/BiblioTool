package graphical_interface;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Edge;
import org.gephi.preview.api.G2DTarget;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewMouseEvent;
import org.gephi.preview.api.PreviewProperties;
import org.gephi.preview.api.Vector;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;


/**
 * @author Shuai
 * This class is used for storing nodes and edges that a user want to hide.
 * Can be use for redisplay it with the function "diplayHideNode"
 */
public class HideNodes {
	private Node[] node;
	private Edge[] edge;
	private int potision = -1;

	
	public void hideNode(Node node)
	{
		
	}
}