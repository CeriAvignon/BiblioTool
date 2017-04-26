package graphe;

import java.io.File;
import org.gephi.graph.api.*;
import org.gephi.graph.api.Edge;
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
		
		String var = MyGraph.exportGraph(MyGraph.Extension.GML);
		System.out.println(var);
		Article art = new Article();
		art.setIdArt(5);
		art.setTitleArt("article5");
		art.setStatus(false);
		Node noeud;
		noeud= MyGraph.creerNode(art);
		MyGraph.changeStatusArticl(noeud);
		System.out.println(noeud.getAttribute("status"));
		
		PartitionGraph partitionGraph = new PartitionGraph();
        partitionGraph.script();
        //MG-22 Export graph test
		System.out.println(var);
		
}
}
