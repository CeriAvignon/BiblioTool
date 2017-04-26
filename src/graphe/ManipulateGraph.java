package graphe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import org.gephi.graph.api.*;
import org.gephi.graph.api.Edge;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public class ManipulateGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.ListeReference());

		DirectedGraph dg = MyGraph.createDirectedGraph();
		 System.out.println("avant la mise a jour du graph");
	     System.out.println("Nodes: " + dg.getNodeCount() + " Edges: " + dg.getEdgeCount());
	    DirectedGraph dg2 = MyGraph.updateGraph();// on mis a jour le graphe
	     System.out.println("apres la mise a jour du graph");
	     System.out.println("Nodes: " + dg.getNodeCount() + " Edges: " + dg.getEdgeCount());
	   
		
		

		
	}
}
