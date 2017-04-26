package graphe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.appearance.api.Function;
import org.gephi.appearance.api.Partition;
import org.gephi.appearance.api.PartitionFunction;
import org.gephi.appearance.plugin.PartitionElementColorTransformer;
import org.gephi.appearance.plugin.palette.Palette;
import org.gephi.appearance.plugin.palette.PaletteManager;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.spi.CharacterExporter;
import org.gephi.io.exporter.spi.Exporter;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.Modularity;
import org.openide.util.Lookup;

public final class MyGraph {
	
	public static List<Article> articles;
	public static List<Reference> references;
	public static GraphModel graphModel;
	private MyGraph() { }
	
	public static Column idArt, titleArt, author, doi, pubYear, numPage, nbPage, numVol, numIssue, journal, urlArt, ref, status;
	public static Column idAuth, name_auth, first_name, affiliation;
	
	public static DirectedGraph createDirectedGraph(){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		createArticleColumns();
		
		articles = listeArticles();
		references = listeReference();
		
		for (Article article : articles) {
			Node n0 = graphModel.factory().newNode(String.valueOf(article.getIdArt()));
			n0.setLabel(article.getTitleArt());
			n0.setAttribute(idArt, article.getIdArt());
			n0.setAttribute(pubYear, article.getPubYear());
			n0.setAttribute(titleArt, article.getTitleArt());
			n0.setAttribute(doi, article.getDoi());
			n0.setAttribute(numPage, article.getNumPage());
			n0.setAttribute(nbPage, article.getNbPage());
			n0.setAttribute(numVol, article.getNumVol());
			n0.setAttribute(numIssue, article.getNumIssue());
			n0.setAttribute(journal, article.getJournal());
			n0.setAttribute(urlArt, article.getUrlArt());
			//n0.setAttribute(ref, article.getReferences());
			n0.setAttribute(status, article.getStatus());
			for (Author auth : article.getAuthor()) {
				n0.setAttribute(author, auth.getFirst_name()+" "+auth.getName_auth());
			}
			
			directedGraph.addNode(n0);
		}
		
		System.out.println("les attributs du node sont: ");
			for (Column col : graphModel.getNodeTable()) {
			System.out.println(col);
		}
			
		for (Reference reference : references) {
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			directedGraph.addEdge(e1);
		}
		
		return directedGraph;
	}
	
	public static DirectedGraph createAuthorsGraph(){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);
		DirectedGraph authorGraph = graphModel.getDirectedGraph();
		createAuthorColumns();
		
		List<Author> authors = listAllAuthors();
		references = listeReference();
		
		for (Author author : authors) {
			Node n0 = graphModel.factory().newNode();
			n0.setAttribute(idAuth, author.getId_auth());
			n0.setAttribute(name_auth, author.getName_auth());
			n0.setAttribute(first_name, author.getFirst_name());
			n0.setAttribute(affiliation, author.getAffiliation());
			
			authorGraph.addNode(n0);
		}
		
		for (Reference ref : references) {
			
		}
		
		return authorGraph;
	}
	
	public static DirectedGraph createAuthorsGraph(Article article){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);
		DirectedGraph authorGraph = graphModel.getDirectedGraph();
		createAuthorColumns();
		
		List<Author> authors = listAuthorsByArticle(article);
		references = listeReference();
		
		for (Author author : authors) {
			Node n0 = graphModel.factory().newNode();
			n0.setAttribute(idAuth, author.getId_auth());
			n0.setAttribute(name_auth, author.getName_auth());
			n0.setAttribute(first_name, author.getFirst_name());
			n0.setAttribute(affiliation, author.getAffiliation());
			
			authorGraph.addNode(n0);
		}
		
		
		return authorGraph;
	}
	
	public static void createArticleColumns(){
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
	
	public static void createAuthorColumns(){
		idAuth = graphModel.getNodeTable().addColumn("id_Author", Integer.class);
		name_auth = graphModel.getNodeTable().addColumn("name_Author", String.class);
		first_name = graphModel.getNodeTable().addColumn("first_name", String.class);
		affiliation = graphModel.getNodeTable().addColumn("afficliation", String.class);
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
	public static List<Article> listeArticles() {
		List<Article> articles = new ArrayList<Article>();
		for (int i = 0; i < 6; i++) {
			Article art = new Article();
			art.setIdArt(i + 1);
			art.setTitleArt("article" + (i + 1));
			art.setAuthor(testListAuthor());
			articles.add(art);
		}
		return articles;
	}

	public static List<Author> testListAuthor(){
		List<Author> test = new ArrayList<Author>();
		for (int i = 0; i < 5; i++) {
			Author a = new Author(i, "nom_author"+(i+1), "prenom_author"+(i+1), "affiliation"+(i+1));
			test.add(a);
		}
		return test;
	}
	
	// methode implementee par le groupe web-mining
	public static List<Reference> listeReference() {
		List<Reference> references = new ArrayList<Reference>();
		for (int i = 1; i <= 5; i++) {
			Reference ref = new Reference();
			ref.setSource(i);
			ref.setTarget(i + 1);
			references.add(ref);
		}
		return references;
	}
	
	public static List<Author> listAllAuthors(){
		List<Author> authors = new ArrayList<Author>();
		List<Article> articles = listeArticles();
		Iterator<Author> iterate_auth;
		for (Article article : articles) {
			iterate_auth = article.getAuthor().iterator();
			while(iterate_auth.hasNext()){
				Author auth = iterate_auth.next();
				authors.add(auth);
			}
		}
		return authors;
	}
	
	public static List<Author> listAuthorsByArticle(Article article){
		List<Author> authors = new ArrayList<Author>();
		Iterator<Author> iterate_auth = article.getAuthor().iterator();
		while(iterate_auth.hasNext()){
			Author auth = iterate_auth.next();
			authors.add(auth);
		}
		
		return authors;
	}
	
	public enum Extension {
	    GEXF,
	    GML		  
}
	
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
	
	public static void partitionGraph(DirectedGraph graph, Column col){
		AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class);
        AppearanceModel appearanceModel = appearanceController.getModel();
		
		Column column = graph.getModel().getNodeTable().getColumn(col.getId());
		Function func = appearanceModel.getNodeFunction(graph, column, PartitionElementColorTransformer.class);
		Partition partition = ((PartitionFunction) func).getPartition();
        Palette palette = PaletteManager.getInstance().generatePalette(partition.size());
        partition.setColors(palette.getColors());
        appearanceController.transform(func);
		
      //Export
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        try {
            ec.exportFile(new File("partition1.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
		
	}
	
	public static void partitionGraph(DirectedGraph graph){
		AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class);
        AppearanceModel appearanceModel = appearanceController.getModel();
        
        Modularity modularity = new Modularity();
        modularity.execute(graph.getModel());
        
        Column modColumn = graph.getModel().getNodeTable().getColumn(Modularity.MODULARITY_CLASS);
        Function func2 = appearanceModel.getNodeFunction(graph, modColumn, PartitionElementColorTransformer.class);
        Partition partition2 = ((PartitionFunction) func2).getPartition();
        System.out.println(partition2.size() + " partitions found");
        Palette palette2 = PaletteManager.getInstance().randomPalette(partition2.size());
        partition2.setColors(palette2.getColors());
        appearanceController.transform(func2);
        
      //Export
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        try {
            ec.exportFile(new File("partition1.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
	}
	
	
}
