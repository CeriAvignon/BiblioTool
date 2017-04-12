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
	   // DirectedGraph dg2 = MyGraph.updateGraph();
		//System.out.println("Nodes: " + dg2.getNodeCount() + " Edges: " + dg2.getEdgeCount());
		System.out.println("Nodes: " + dg.getNodeCount() + " Edges: " + dg.getEdgeCount());
		List<Node> listOfNodes=MyGraph.listOfNodes();
		for(Node l:listOfNodes){
			System.out.print(l.getAttribute(MyGraph.idArt));
			System.out.println(l.getAttribute(MyGraph.titleArt));
			
		}
		
		//MyGraph.returnDistinctList();
		
		
		/* PreviewModel previewModel = Lookup.getDefault().lookup(PreviewController.class).getModel();
	        previewModel.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS, Boolean.TRUE);
	        previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_PROPORTIONAL_SIZE, Boolean.FALSE);
		 ExportController ec = Lookup.getDefault().lookup(ExportController.class);
	        try {
	            ec.exportFile(new File("ExportGraph.pdf"));
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            return;
	        }*/
		

		
	}
}
