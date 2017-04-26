package graphe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.impl.GraphModelImpl;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.spi.CharacterExporter;
import org.gephi.io.exporter.spi.Exporter;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public final class MyGraph {
	
	public static List<Article> articles;
	public static List<Article> articles2;

	public static List<Reference> references;
	public static GraphModel graphModel;
	private MyGraph() { }

	public static Column idArt, titleArt, author, doi, pubYear, numPage, nbPage, numVol, numIssue, journal, urlArt, ref, status;
	
	public static DirectedGraph createDirectedGraph(){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		createColumns();
		
		articles = retournerListeArticles();
		references = ListeReference();
		
		
		for (Article article : articles) {
			Node n0 = graphModel.factory().newNode(String.valueOf(article.getIdArt()));
			n0.setLabel(article.getTitleArt());
			n0.setAttribute(idArt, article.getIdArt());
			n0.setAttribute(pubYear, article.getPubYear());
			n0.setAttribute(author, article.getAuthor());
			n0.setAttribute(titleArt, article.getTitleArt());
			n0.setAttribute(doi, article.getDoi());
			n0.setAttribute(numPage, article.getNumPage());
			n0.setAttribute(nbPage, article.getNbPage());
			n0.setAttribute(numVol, article.getNumVol());
			n0.setAttribute(numIssue, article.getNumIssue());
			n0.setAttribute(journal, article.getJournal());
			n0.setAttribute(urlArt, article.getUrlArt());
			n0.setAttribute(ref, article.getReferences());
			n0.setAttribute(status, article.getStatus());
			
			directedGraph.addNode(n0);
		}
		
		System.out.println("les attributs du node sont :");
		for (Column col : graphModel.getNodeTable()) {
            System.out.println(col.getTitle());
		}
		
		for (Reference reference : references) {
			//System.out.println(reference.getTarget());
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			directedGraph.addEdge(e1);
		}
		for(Edge e : directedGraph.getEdges()) {
		    System.out.println(e.getSource().getId()+" -> "+e.getTarget().getId());
		}
		for(Node e : directedGraph.getNodes()) {
		    System.out.println(e.getAttribute(idArt));
		}
	
		return directedGraph;
	}
	public static DirectedGraph createDirectedGraph2(){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		//pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		//createColumns();
		
		articles2 = retournerListeArticles2();
		references = ListeReference();
		
		
		for (Article article : articles2) {
			Node n1 = graphModel.factory().newNode(String.valueOf(article.getIdArt()));
			n1.setLabel(article.getTitleArt());
			n1.setAttribute(idArt, article.getIdArt());
			n1.setAttribute(pubYear, article.getPubYear());
			n1.setAttribute(author, article.getAuthor());
			n1.setAttribute(titleArt, article.getTitleArt());
			n1.setAttribute(doi, article.getDoi());
			n1.setAttribute(numPage, article.getNumPage());
			n1.setAttribute(nbPage, article.getNbPage());
			n1.setAttribute(numVol, article.getNumVol());
			n1.setAttribute(numIssue, article.getNumIssue());
			n1.setAttribute(journal, article.getJournal());
			n1.setAttribute(urlArt, article.getUrlArt());
			n1.setAttribute(ref, article.getReferences());
			n1.setAttribute(status, article.getStatus());
			
			directedGraph.addNode(n1);
		}
		
		System.out.println("les attributs du node sont :");
		for (Column col : graphModel.getNodeTable()) {
            System.out.println(col.getTitle());
		}
		
//		for (Reference reference : references) {
//			//System.out.println(reference.getTarget());
//			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
//			directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
//			directedGraph.addEdge(e1);
//		}
		
		return directedGraph;
	}
	public static void createColumns(){
		
		idArt = graphModel.getNodeTable().addColumn("id_Article", Integer.class);
		titleArt = graphModel.getNodeTable().addColumn("title_Article", String.class);
		author = graphModel.getNodeTable().addColumn("authors", String.class);
		doi = graphModel.getNodeTable().addColumn("doi", String.class);
		pubYear = graphModel.getNodeTable().addColumn("pub_Year", Integer.class);
		numPage = graphModel.getNodeTable().addColumn("num_Page", Integer.class);
		nbPage = graphModel.getNodeTable().addColumn("nb_Page", Integer.class);
		numVol = graphModel.getNodeTable().addColumn("num_Volume", Integer.class);
		numIssue = graphModel.getNodeTable().addColumn("num_Issue", Integer.class);
		journal = graphModel.getNodeTable().addColumn("journal", String.class);
		urlArt = graphModel.getNodeTable().addColumn("url_Article", String.class);
		ref = graphModel.getNodeTable().addColumn("reference", Integer.class);
		status = graphModel.getNodeTable().addColumn("status", Boolean.class);
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

	public static List<Article> getArticles2() {
		return articles2;
	}
	public static void setArticles2(List<Article> articles2) {
		MyGraph.articles2 = articles2;
	}
	// methode implementee par le groupe web-mining
	public static List<Article> retournerListeArticles() {
		List<Article> articles = new ArrayList<Article>();
		for (int i = 0; i < 6; i++) {
			Article art = new Article();
			art.setIdArt(i + 1);
			art.setTitleArt("article" + (i + 1));
			articles.add(art);
		}
		return articles;
	}
	public static List<Article> retournerListeArticles2() {
		List<Article> articles2 = new ArrayList<Article>();
		for (int i = 7; i <=8; i++) {
			Article art = new Article();
			art.setIdArt(i + 1);
			art.setTitleArt("article" + (i + 1));
			articles2.add(art);
		}
		return articles2;
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
	
	 public static void exportGraph(){
		 //Export full graph gexf
	 ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
	 Workspace workspace = pc.getCurrentWorkspace();
	 ExportController ec = Lookup.getDefault().lookup(ExportController.class);
	 try {
	     ec.exportFile(new File("graphe.gexf"));
	 } catch (IOException ex) {
	     ex.printStackTrace();
	     return;
	 }
	}
	 //énumeration pour les extensions
	 public enum Extension {
		    GEXF,
		    GML		  
	 }
	 //return chemin,nom du fichier et reçoit par paramétre choix d'une extension
	  public static String exportGraph(Extension ext){
			 //Export full graph gexf
		  if(ext==Extension.GEXF){
			  ExportController ec = Lookup.getDefault().lookup(ExportController.class);
			   try {
			       ec.exportFile(new File("graphe.gexf"));
			   } catch (IOException ex) {
			       ex.printStackTrace();
			   }
			  
		  }
		  else{
			  ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
			   Workspace workspace = pc.getCurrentWorkspace();
			   ExportController ec = Lookup.getDefault().lookup(ExportController.class);
			   //Export GML
		        Exporter exporterGraphML = ec.getExporter("graphml");     //Get GraphML exporter
		        exporterGraphML.setWorkspace(workspace);
		        StringWriter stringWriter = new StringWriter();
		        ec.exportWriter(stringWriter, (CharacterExporter) exporterGraphML);
		        FileWriter fw = null;
		        try {
		            fw = new FileWriter("graphe.graphml");
		            fw.write(stringWriter.toString());
		            fw.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			  
		  }
	       return System.getProperty("user.dir")+"/graphe."+ext;
			  
		   }
}
