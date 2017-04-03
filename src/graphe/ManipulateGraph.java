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
		
		MyGraph.setArticles(MyGraph.listeArticles());
		MyGraph.setReferences(MyGraph.listeReference());
		
		DirectedGraph dg = MyGraph.createDirectedGraph();
		
		System.out.println("Nodes: " + dg.getNodeCount() + " Edges: " + dg.getEdgeCount());
		for (Node node : dg.getNodes()) {
			System.out.println(node.getAttribute("id_Article"));
		}
//		DirectedGraph authorGraph = MyGraph.createAuthorsGraph();
//		for (Node node : authorGraph.getNodes()) {
//			System.out.println(node.getAttribute("id")+": "+node.getAttribute("name_Author"));
//		}
		
		Article article = new Article();
		article.setIdArt(5);
		article.setAuthor(MyGraph.testListAuthor());
		DirectedGraph articleAuthor = MyGraph.createAuthorsGraph(article);
		for (Node node : articleAuthor.getNodes()) {
			System.out.println(node.getAttribute("id")+": "+node.getAttribute("name_Author"));
		}
		
	}
}
