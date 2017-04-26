package graphe;

import java.util.ArrayList;
import java.util.List;

import org.gephi.graph.api.Column;
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
	public static List<Journal> journaux;
	public static List<Reference> references;
	public static GraphModel graphModel;
	private MyGraph() { }
	
	public static Column idArt, titleArt, author, doi, pubYear, numPage, nbPage, numVol, numIssue, journal, urlArt, ref, status;
	public static Column idJour, titleJour;

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
			n0.setAttribute(status, article.getStatus());
			
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
	public static DirectedGraph createGraphJournal(){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		
		
		
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		createColumnsJour();
		
		journaux = retournerListeJournaux();
		
		
		for (Journal jour : journaux) {
			Node n0 = graphModel.factory().newNode(String.valueOf(jour.getId()));
			n0.setLabel(jour.getTitre());
			n0.setAttribute(idJour, jour.getId());
			directedGraph.addNode(n0);
		}
		references = exportRefOfJournal();
		for (Reference reference : references) {
			//System.out.println(reference.getTarget());
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			directedGraph.addEdge(e1);
		}
		
		return directedGraph;
	}
	public void changeStatusArticle(Node a) {
		
		a.setAttribute(status,true);
		int id= Integer.parseInt((String) a.getId());
	  // setStatus(id); methode de web mining 
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
public static void createColumnsJour(){
		
		idJour = graphModel.getNodeTable().addColumn("idJour", Integer.class);
		titleJour = graphModel.getNodeTable().addColumn("titleJour", String.class);
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
			Reference a=new Reference();
			List<Reference> references = new ArrayList<Reference>();
			Article art = new Article();
			a.setId(i+1);
			a.setSource(i+1);
			a.setTarget(i+2);
			references.add(a);
			art.setIdArt(i + 1);
			art.setTitleArt("article" + (i + 1));
			art.setReferences(references);
			articles.add(art);
		}
		return articles;
	}
	public static List<Journal> retournerListeJournaux() {
		
		List<Journal> journaux = new ArrayList<Journal>();
		for (int i = 10; i < 15; i++) {
			
			Journal jour = new Journal();
		
			jour.setId(i);
			jour.setTitre("journal" + (i));


			journaux.add(jour);
		}
		return journaux;
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
	
	public static List<Node> returnlistenode(){
		DirectedGraph dg = createDirectedGraph();
		List<Node> ln = new ArrayList<>();
		int j = 1;
		for (Node node : dg.getNodes()) {
			if (j<4) {
				ln.add(node);
				j++;
			}
			
		}
		return ln ;
	}
	//test (cette fonction pour recuperer les données de la base de données)
	static List<Reference> refList(int idAarticl){
		List<Reference> listRef=new ArrayList<Reference>();
		List<Article> listArticl =retournerListeArticles();
		for(Article a:listArticl){
			if(a.getIdArt()==idAarticl){
				listRef=a.getReferences();	}
		}
	return listRef;
	}

	// methode pour determiner les réferences des noeuds selectionneé
	static List<Reference> exportRef(List<Node> listOfNode){
	 List<Reference> listRef=new ArrayList<Reference>();
	 for (Node node:listOfNode){
		 int id= Integer.parseInt((String) node.getId());
		  List<Reference> listBD=refList(id); 
		for (Reference ref:listBD){
			listRef.add(ref);
		}
	 }
	return listRef;
	}
	// methode implementé par webMining 
	static public int returnIdOfJournalByIdArt(int id_art)
	{
		List<Article> listArticl =listeArticlesTestJournaux();
		for(Article a:listArticl)
		{
			if(a.getIdArt()==id_art)
			{
				int id=a.getJournal().getId();
				return id;
			}
		}
		return 0;
	}
public static List<Article> listeArticlesTestJournaux() {
		
		List<Article> articles = new ArrayList<Article>();

		for (int i = 0; i < 4; i++) {
			List<Reference> references = new ArrayList<Reference>();
			Reference a=new Reference();
			
			Article art = new Article();
			Journal j=new Journal();
			j.setId(i+1*10);
			j.setTitre("journal"+(i + 1));
			a.setId(i+1);
			a.setSource(i+1);
			a.setTarget(i+2);
			references.add(a);
			art.setIdArt(i + 1);
			art.setTitleArt("article" + (i + 1));
			art.setReferences(references);
			art.setJournal(j);
			articles.add(art);
		}
		List<Reference> references = new ArrayList<Reference>();
		Article art=new Article();
		Journal j=new Journal();
		j.setId(14);
		j.setTitre("journal 5");
		Reference b=new Reference();
		b.setId(5);
		b.setSource(5);
		b.setTarget(2);
		references.add(b);
		art.setIdArt(5);
		art.setTitleArt("article 5");
		art.setReferences(references);
		art.setJournal(j);
		articles.add(art);
		return articles;
	}
	// tache 30 exporter les references des journaux
	static List<Reference> exportRefOfJournal()
	{
		 int i=1;
		 List<Reference> listRef=ListeReferenceOfArticle() ;
		 List<Reference> newListOfRef=new ArrayList<Reference>();
		 for (Reference ref:listRef)
		 {
			 	int source=returnIdOfJournalByIdArt(ref.getSource());
			 	//System.out.println("source  "+source);
			 	int target=returnIdOfJournalByIdArt(ref.getTarget());
			 	Reference a= new Reference();
			 	a.setId(i);
			 	a.setSource(source);
			 	a.setTarget(target);
			 	newListOfRef.add(a);
			 	i++;
			}
		 
		return newListOfRef;
	}
	
	// methode implemente par web mining
	public static List<Reference> ListeReferenceOfArticle() {
		List<Article> listArticl =listeArticlesTestJournaux();
		List<Reference> references = new ArrayList<Reference>();
		
		for(Article a:listArticl)
		{
				for(Reference ref:a.getReferences())
					{
					Reference r=ref;
					references.add(r);
					}
			
		}

		return references;
	}
	
	
	
	
}