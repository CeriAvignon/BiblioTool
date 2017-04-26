package graphe;

import java.util.ArrayList;
import java.util.List;

import org.gephi.graph.api.*;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public class ManipulateGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.ListeReference());
		
		DirectedGraph dg = MyGraph.createDirectedGraph();
		
		System.out.println("les attributs du node sont :");
		for (Column col : MyGraph.graphModel.getNodeTable()) {
            System.out.println(col);
		}
		System.out.println("Nodes: " + dg.getNodeCount() + " Edges: " + dg.getEdgeCount());
		
	/*	List<Reference>ListRef= MyGraph.exportRef(MyGraph.returnlistenode());
		for(Reference ref:ListRef)
			ref.afficher();
		*/
		DirectedGraph dg1 = MyGraph.createGraphJournal();
		
		System.out.println("les attributs du node sont :");
		for (Column col : MyGraph.graphModel.getNodeTable()) {
            System.out.println(col);
		}
		List<Reference>ListRef= MyGraph.exportRefOfJournal();
		for(Reference ref:ListRef)
			ref.afficher();   
	
		}

}
