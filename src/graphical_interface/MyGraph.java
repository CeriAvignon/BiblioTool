package graphical_interface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;

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
import org.gephi.datalab.impl.GraphElementsControllerImpl;

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
			//System.out.println(reference.getTarget());
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			directedGraph.addEdge(e1);
		}
		
		return directedGraph;
	}
	
	public static void generateFileGexfFromGraph(DirectedGraph directedGraph)
	{
		try{
				File file=new File("src/testGephi/testGephi.gexf"); // définir l'arborescence
				file.createNewFile();
				FileWriter fw=new FileWriter(file);
				String date =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
				// general header for gexf
				String header="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
				header += "<gexf xmlns:viz=\"http:///www.gexf.net/1.1draft/viz\" version=\"1.1\" xmlns=\"http://www.gexf.net/1.1draft\">\n";
				header += "<meta lastmodifieddate=\""+date+"\">\n";
				header += "<creator>Gephi 0.7</creator>\n</meta>\n";
				header += "<graph defaultedgetype=\"directed\" idtype=\"string\" type=\"static\">\n";
				header += "<attributes class=\"node\" mode=\"static\">\n";
				header += "<attribute id=\"modularity_class\" title=\"Modularity Class\" type=\"integer\"/>\n</attributes>\n";
				fw.write(header);
				
				// information of nodes and egds
				fw.write("<nodes>\n");
				for(Node n : directedGraph.getNodes()) {
					String str = "<node id=\""+n.getId()+"\" label=\""+n.getLabel()+"\">\n";
					str += "<viz:size value=\"25\"/>\n";
					str += "<viz:color b=\"181\" g=\"228\" r=\"255\"/>\n</node>\n";
					fw.write(str);
				}
				fw.write("</nodes>\n");
				
				fw.write("<edges>\n");
				for (Edge e : directedGraph.getEdges()) {
					String str = "<edge id=\""+e.getId()+"\" source=\""+ e.getSource().getId()+"\" target=\""+e.getTarget().getId()+"\"></edge>\n";
					fw.write(str);
				}
				fw.write("</edges>\n</graph>\n</gexf>\n");
				fw.close(); // fermer le fichier à la fin des traitements
		} 
		catch (Exception e) {
		}

	}
	
	/**
	 * @author yang shuai
	 *
	 */
	// this function allow to show neighbors of nodes.
	public static void showNeighbors(DirectedGraph directedGraph)
	{
		// Iterate over nodes
		for(Node n : directedGraph.getNodes()) {
		Node[] neighbors = directedGraph.getNeighbors(n).toArray();
			System.out.println(n.getLabel()+" has "+neighbors.length+" neighbors");
			for(int i=0;i<neighbors.length;i++)
			{
				System.out.print("Its neighbor is "+neighbors[i].getLabel()+"\n");
			}
		}
		System.out.println("");
	}
	
	
	public static List<Article> getArticles() {
		return articles;
	}

	public static void setArticles(List<Article> articles) {
		MyGraph.articles = articles;
	}

	public static List<Reference> getReferences() {
		return references;
	}

	public static void setReferences(List<Reference> references) {
		MyGraph.references = references;
	}

	// methode implementee par le groupe web-mining
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

	// methode implementee par le groupe web-mining
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
