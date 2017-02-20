package graphe;

import java.util.ArrayList;
import java.util.List;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.impl.GraphModelImpl;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public final class MyGraph {
	
	public static List<Article> articles;
	public static List<Reference> references;
	
	private MyGraph() { }
	
	public static DirectedGraph createDirectedGraph(){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		
		GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		
		articles = retournerListeArticles();
		references = ListeReference();
		
		for (Article article : articles) {
			Node n0 = graphModel.factory().newNode(String.valueOf(article.getId()));
			n0.setLabel(article.getTitre());
			directedGraph.addNode(n0);
		}
		
		for (Reference reference : references) {
			System.out.println(reference.getTarget());
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			directedGraph.addEdge(e1);
		}
		
		return directedGraph;
	}
	
	
	public static List<Article> retournerListeArticles() {
		List<Article> articles = new ArrayList<Article>();
		for (int i = 0; i < 6; i++) {
			Article art = new Article();
			art.setId(i + 1);
			art.setTitre("article" + (i + 1));
			articles.add(art);
		}
		return articles;
	}

	public static List<Reference> ListeReference() {
		List<Reference> references = new ArrayList<Reference>();
		for (int i = 1; i <= 5; i++) {
			Reference ref = new Reference();
			ref.setSource(i);
			ref.setTarget(i + 1);
			references.add(ref);
		}
		return references;
	}
}
