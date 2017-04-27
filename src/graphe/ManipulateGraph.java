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
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.spi.CharacterExporter;
import org.gephi.io.exporter.spi.Exporter;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import java.util.ArrayList;
import java.util.List;
public class ManipulateGraph {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyGraph.setArticles(MyGraph.listeArticles());
		MyGraph.setReferences(MyGraph.listeReference());
		
		DirectedGraph dg = MyGraph.createDirectedGraph();
		Article art = new Article();
		art.setIdArt(5);
		art.setTitleArt("article5");
		art.setStatus(false);
		Node noeud;
		noeud= MyGraph.creerNode(art);
		MyGraph.changeStatusArticl(noeud);
		System.out.println("le nouveau statut de l'article est "+noeud.getAttribute("status"));
		
		System.out.println("les attributs du node sont :");
		for (Column col : MyGraph.graphModel.getNodeTable()) {
            System.out.println(col);
		}
		System.out.println("Nodes: " + dg.getNodeCount() + " Edges: " + dg.getEdgeCount());
		for (Node node : dg.getNodes()) {
			System.out.println(node.getAttribute("id_Article"));
		}
		DirectedGraph authorGraph = MyGraph.createAuthorsGraph();
		for (Node node : authorGraph.getNodes()) {
			System.out.println(node.getAttribute("id")+": "+node.getAttribute("name_Author"));
		}
		
		Article article = new Article();
		article.setIdArt(5);
    	article.setAuthor(MyGraph.testListAuthor());
		DirectedGraph articleAuthor = MyGraph.createAuthorsGraph();
		for (Node node : articleAuthor.getNodes()) {
			//System.out.println(node.getAttribute("id")+": "+node.getAttribute("name_Author"));
			System.out.println(node.getAttribute("id_Author")+": "+node.getAttribute("name_Author"));
		}

		//MyGraph.partitionGraph(authorGraph);

		//MyGraph.partitionGraph(authorGraph, MyGraph.pubYear);

		//afficher les references des noeuds selectionnés
		List<Reference>ListRef= MyGraph.exportRef(MyGraph.returnlistenodeSelect());
		for(Reference ref:ListRef)
			ref.afficher();   
		
		
		
		
		
		String var = MyGraph.exportGraph(MyGraph.Extension.GML);
		System.out.println(var);
	
		//PartitionGraph partitionGraph = new PartitionGraph();
       // partitionGraph.script();
      
		MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.listeReference());

		DirectedGraph dg1 = MyGraph.createDirectedGraph();
		 System.out.println("avant la mise a jour du graph");
	     System.out.println("Nodes: " + dg1.getNodeCount() + " Edges: " + dg1.getEdgeCount());
	    DirectedGraph dg2 = MyGraph.updateGraph();// on mis a jour le graphe
	     System.out.println("apres la mise a jour du graph");
	     System.out.println("Nodes: " + dg1.getNodeCount() + " Edges: " + dg1.getEdgeCount());
	}
	

	

}
