
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
		//Init a project - and therefore a workspace
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();

		//Get a graph model - it exists because we have a workspace
		GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
		
		//sdsdsdsd____sd_____s_d_s___s_d__
		List<Article> articles = retournerListeArticles();
		List<Reference> ref = ListeReference();
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		for (Article article : articles) {
			Node n0 = graphModel.factory().newNode(String.valueOf(article.getId()));
			n0.setLabel(article.getTitre());
			//cr�er attribut
			directedGraph.addNode(n0);
		}
		 //Create edges
	      for (Reference reference : ref) {
	    	  System.out.println(reference.getTarget());
			      Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())), directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			      directedGraph.addEdge(e1);
			}
		System.out.println("Nodes: "+directedGraph.getNodeCount()+" Edges: "+directedGraph.getEdgeCount());

		//Iterate over nodes
//		for(Node n : directedGraph.getNodes()) {
//		    Node[] neighbors = directedGraph.getNeighbors(n).toArray();
//		    System.out.println(n.getLabel()+" has "+neighbors.length+" neighbors");
//		}

		//Iterate over edges
		for(Edge e : directedGraph.getEdges()) {
		    System.out.println(e.getSource().getId()+" -> "+e.getTarget().getId());
		}
		
		for (int i = 0; i < retournerListeArticles().size(); i++) {
			System.out.println(retournerListeArticles().get(i).getTitre() + " id: "+ (retournerListeArticles().get(i).getId()));
		}
			
		
	}
	
	public static List<Article> retournerListeArticles(){
		List<Article> articles = new ArrayList<Article>();
		for (int i = 0; i < 6; i++) {
			Article art = new Article();
			art.setId(i+1);
			art.setTitre("article"+(i+1));
			articles.add(art);
		}
		return articles;
	}

    public static List<Reference> ListeReference(){
		List<Reference> references = new ArrayList<Reference>();
		for (int i = 1; i <= 5; i++) {
			Reference ref = new Reference();
			ref.setSource(i);
			ref.setTarget(i+1);
			references.add(ref);
		}
		return references;
	}
}
