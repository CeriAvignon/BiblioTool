package graphe;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.project.api.ProjectController;
import org.openide.util.Lookup;

public class NewGraph {
    Article art;
	Graph graph;
	static GraphModel graphModel;
	public static List<Article> articles;
	//public static List<List<Article>> newArticles;
	public static List<Reference> references;
	public static HashMap<Article,List<Article>> newArticles = new HashMap<Article,List<Article>>();
	
	
	public DirectedGraph Update() {
        
	ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
	pc.newProject();
	
	graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
	DirectedGraph directedGraph = graphModel.getDirectedGraph();
	
	articles = retournerListeArticles();
	references = ListeReference();

	for (Map.Entry<Article, List<Article>> article : newArticles.entrySet()) {
		for (Article article2 : articles){
			if (article.getKey() != article2 ){
	    Node n1 = graphModel.factory().newNode(String.valueOf(article.getKey().getIdArt()));
	    directedGraph.addNode(n1);
		Node n2 = graphModel.factory().newNode(String.valueOf(article2.getIdArt()));
		directedGraph.addNode(n2);
		Edge e = directedGraph.getEdge(n1, n2);
		directedGraph.addEdge(e);
	}
	}
	
}
	return directedGraph;}
	// methode implementee par le groupe web-mining
		public static List<Article> retournerListeArticles() {
			List<Article> articles = new ArrayList<Article>();
			for (int i = 0; i < 6; i++) {
				Article art = new Article();
				art.setIdArt(i + 1);
				art.setTitleArt("article" + (i + 1));
				art.setNbPage(50+i);
				art.setNumVol(700);
				art.setDoi("AIIII001");
				art.setStatus(true);
				articles.add(art);
			}
			return articles;
		}

		// methode implementee par le groupe web-mining
		public static List<Reference> ListeReference() {
			List<Reference> references = new ArrayList<Reference>();
			for (int i = 1; i <= 5; i++) {
				Reference ref = new Reference();
				ref.setSource(i);
				ref.setTarget(i + 1);
				references.add(ref);
			}
			Reference ref1 = new Reference();
			ref1.setSource(5);
			ref1.setTarget(3);
			references.add(ref1);
			Reference ref2 = new Reference();
			ref2.setSource(5);
			ref2.setTarget(2);
			references.add(ref2);
			
			Reference ref3 = new Reference();
			ref3.setSource(3);
			ref3.setTarget(1);
			references.add(ref3);
			
			return references;
		}

		
        }
    


