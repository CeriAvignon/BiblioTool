package graphe;

import java.util.ArrayList;
import java.util.List;

import org.gephi.graph.api.*;
import org.gephi.graph.api.Edge;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public class ManipulateGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.ListeReference());
		
		DirectedGraph dg = MyGraph.createDirectedGraph();
		
		System.out.println("Nodes: " + dg.getNodeCount() + " Edges: " + dg.getEdgeCount());
		
	}
}
