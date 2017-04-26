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
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

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

public final class MyGraph{
	
	public static List<Article> articles;

	public static List<Journal> journaux;
	public static List<Reference> references;
	public static GraphModel graphModel;
	private MyGraph() { }

	public static Column idArt, titleArt, author, doi, pubYear, numPage, nbPage, numVol, numIssue, journal, urlArt, ref, status;
	public static Column idAuth, name_auth, first_name, affiliation;
	
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
	public static Column idJour, titleJour;

	public static DirectedGraph createDirectedGraph(){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();

		Workspace workspace = pc.getCurrentWorkspace();		
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		createArticleColumns();
		
		articles = listeArticles();
		references = listeReference();
		for(Article a : articles)
			System.out.println("id article: " + a.getIdArt());
		
		for(Reference ref : references)
			System.out.println("edge: " + ref.getSource() + " -> " + ref.getTarget());
		
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
			
		
	/*	System.out.println("les attributs du node sont :");
		for (Column col : graphModel.getNodeTable()) {
            System.out.println(col.getTitle());
		}
		*/
	
		
		for (Reference reference : references) {
			//System.out.println(reference.getTarget());
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			directedGraph.addEdge(e1);
		}
		
		return directedGraph;
	}
	// creer un graphe des journaux
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
		references = exportRefOfJournals();
		//créer des liens entre les journaux a partir de la fonction exportRefOfJournal() 
		for (Reference reference : references) {
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			directedGraph.addEdge(e1);
		}
		for(Edge e : directedGraph.getEdges()) {
		   // System.out.println(e.getSource().getId()+" -> "+e.getTarget().getId());
		}
		for(Node e : directedGraph.getNodes()) {
		   // System.out.println(e.getAttribute(idArt));
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
		List<Author> authors = testListAuthor();
		List<Article> articles = listeArticles();
		//on fait appel à la méthode qui exporte les references d'un auteur
		List<Reference> references = exportRefAuthors(authors,articles);
		for (Author author : authors) {
			Node n0 = graphModel.factory().newNode(String.valueOf(author.getId_auth()));
			n0.setAttribute(idAuth, author.getId_auth());
			n0.setAttribute(name_auth, author.getName_auth());
			n0.setAttribute(first_name, author.getFirst_name());
			n0.setAttribute(affiliation, author.getAffiliation());
			authorGraph.addNode(n0);
		}
		for (Reference ref : references) {
			System.out.println(ref.getSource()+ "->" +ref.getTarget());
			Edge e1 = graphModel.factory().newEdge(authorGraph.getNode(String.valueOf(ref.getSource())),authorGraph.getNode(String.valueOf(ref.getTarget())), 0, 1.0, true);
			//authorGraph.addEdge(e1);
		}
		
		return authorGraph;
	}
	public void changeStatusArticle(Node a) {
		
		a.setAttribute(status,true);
		int id= Integer.parseInt((String) a.getId());
	  // setStatus(id); methode de web mining 
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
		public static void createColumnsJour(){
		
		idJour = graphModel.getNodeTable().addColumn("idJour", Integer.class);
		titleJour = graphModel.getNodeTable().addColumn("titleJour", String.class);
	}
	

	

	// methode implementee par le groupe web-mining
	public static List<Article> listeArticles() {
		List<Article> articles = new ArrayList<Article>();
		for (int i = 1; i < 7; i++) {
			Article art = new Article();
			art.setIdArt(i);
			art.setTitleArt("article" + (i));
			Author auth = new Author(i, "nom_author"+(i), "prenom_author"+(i), "affiliation"+(i));
			List<Author> test = new ArrayList<Author>();
			test.add(auth);
			art.setAuthor(test);
			Reference a=new Reference();
			a.setId(i);
			a.setSource(i);
			a.setTarget(i+1);
			List<Reference> references = new ArrayList<Reference>();
			references.add(a);
			art.setReferences(references);
			articles.add(art);
		}
		return articles;
	}
	//on crée une liste d'auteurs
	public static List<Author> testListAuthor(){
		List<Author> test = new ArrayList<Author>();
		for (int i = 1; i < 7; i++) {
			Author a = new Author(i, "nom_author"+(i), "prenom_author"+(i), "affiliation"+(i));
			test.add(a);
		}
		return test;
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
	// créer liste des journaux (pour le test)
	public static List<Journal> retournerListeJournaux() {
		
		List<Journal> journaux = new ArrayList<Journal>();
		for (int i = 10; i < 15; i++) {
			
			Journal jour = new Journal();
		
			jour.setId(i);
			jour.setTitre("journal" + (i));

		}
		
		return journaux;
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
	public static List<Reference> listeReference() {
		List<Reference> references = new ArrayList<Reference>();

		for (int i = 1; i < 5; i++) {
			Reference ref = new Reference();
			ref.setSource(i);
			ref.setTarget(i + 1);
			references.add(ref);
		}
		
		Reference ref1 = new Reference();
		ref1.setSource(1);
		ref1.setTarget(4);
		Reference ref2 = new Reference();
		ref2.setSource(2);
		ref2.setTarget(5);
		Reference ref3 = new Reference();
		ref3.setSource(3);
		ref3.setTarget(4);
		references.add(ref1);references.add(ref2);references.add(ref3);
		return references;
	}
	
	// methode pour mettre a jour le graphe apres le developpement d'un noeud
	public static DirectedGraph updateGraph(){
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		Workspace workspace = pc.getCurrentWorkspace();
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		//method webmining
		List<Article> listArticl= returnNewList();
         // methode webmining
		List<Reference> listRef=ListNewReferences();
		
		for (Article article : listArticl) {
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
     	for (Reference reference : listRef) {
			//System.out.println(reference.getTarget());
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
		directedGraph.addEdge(e1);
	}
		
		return directedGraph;
	}
	// methode implemente par webMining (la nouvelle recherche) permet de retourner
	//la liste des articles associés a la nouvelle recherche
	public static List<Article> returnNewList() {
		List<Article> articles = new ArrayList<Article>();
		for (int i = 7; i <=8; i++) {
			Article art = new Article();
			art.setIdArt(i );
			art.setTitleArt("article"+i);
			art.setNbPage(500);
			articles.add(art);
		}	

		return articles;
	}

	// methode implemente par web-Mining : liste des references de la nouvelle recherche
	public static List<Reference> ListNewReferences() {
		List<Reference> references = new ArrayList<Reference>();
		Reference ref1 = new Reference();
		ref1.setSource(7);
		ref1.setTarget(8);
		Reference ref2 = new Reference();
		ref2.setSource(7);
		ref2.setTarget(5);
		Reference ref3 = new Reference();
		ref3.setSource(8);
		ref3.setTarget(1);
		references.add(ref1);
		references.add(ref2);
		references.add(ref3);
	
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
	//Ali
	public static List<Author> listAuthorsByArticle(Article article){
		List<Author> authors = new ArrayList<Author>();
				Iterator<Author> iterate_auth = article.getAuthor().iterator();
				while(iterate_auth.hasNext()){
					Author auth = iterate_auth.next();
					authors.add(auth);
				}
		return authors;
		
	}
	// retourner la liste d'auteurs par article
	public static List<Author> listAuthorsByArticle(int idArticle, List<Article> articles){
		List<Author> authors = new ArrayList<Author>();
		for(Article article:articles){
			if(article.getIdArt()==idArticle){
				Iterator<Author> iterate_auth = article.getAuthor().iterator();
				while(iterate_auth.hasNext()){
					Author auth = iterate_auth.next();
					authors.add(auth);
				}
			}
		}
		return authors;
		
	}
	// retourne la liste des articles par auteur
	public static List<Article> listArticlesByAuthor(int idAuthor,List<Article> articles){
		List<Article> articlesA = new ArrayList<Article>();
		for(Article article : articles){
		Iterator<Author> iterate_auth = article.getAuthor().iterator();
		while(iterate_auth.hasNext()){
			Author auth = iterate_auth.next();
			if(auth.getId_auth()==idAuthor){
				articlesA.add(article);
			}
		 }
		}
		return articlesA;
	}
	//retourne la liste des réf d'un article donné
	static List<Reference> refList(int idArticle, List<Article> articles){
		List<Reference> listRef=new ArrayList<Reference>();
		for(Article a:articles){
			if(a.getIdArt()==idArticle){
				listRef=a.getReferences();	
				}
		}
		return listRef;
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

	//MG-22
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
	

	public static void changeStatusArticl(Node a) {
		
		a.setAttribute(status,true);//on change le statut de noeud sélectionné
		//int id= Integer.parseInt((String) a.getId());
	  // setStatus(id); methode de web mining pour changer le statut dans la base de données
	}
	// cette methode pour tester la methode changeStatusArticl
	public static Node creerNode(Article article){
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
		return n0;
	}
	//retourne la liste des references d'auteurs
	public static List<Reference> exportRefAuthors(List<Author> authors,List<Article> articles){
		//List articles avec authors
		List<Reference> references = new ArrayList<Reference>();
		for(Author auth: authors){
			List<Article> articlesAuth = new ArrayList<Article>();
			articlesAuth=listArticlesByAuthor(auth.getId_auth(),articles);
			for(Article art:articlesAuth){
				List<Reference> referenceArticle = new ArrayList<Reference>();
				referenceArticle=refList(art.getIdArt(),articlesAuth);
				for(Reference ref: referenceArticle){
					Reference ref1 = new Reference();
					ref1.setSource(auth.getId_auth());
					List<Author> authorsArticle= new ArrayList<Author>();
					authorsArticle=listAuthorsByArticle(ref.getTarget(),articles);
					for(Author auth2: authorsArticle){
						ref1.setTarget(auth2.getId_auth());
						references.add(ref1);
					}
				}
			}
			
		}

		return references;
	}
 	
	


	
	/*public static List<Article> returnDistinctList() {
	List<Article> articles =returnNewList();
	List<Article> DistincArt =new ArrayList<Article>();
  for (Article article : articles) {
	  if(!DistincArt.contains(article)){
		  System.out.println(article.getIdArt());
		  System.out.println(article.getTitleArt());
		  DistincArt.add(article);

	  }else rtyrrt9
	
}
	return DistincArt ;
}
*/

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
	
	// methode implementé par webMining permet de retourner l id du journal associé a l id d'article
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
	/* methodes listeArticlesTestJournaux() :creer des articles (id,titre,journal,ref ...) pour 
	tester la methode exportRefOfJournal 
	normalement on utilise la liste des articles retournés par la BDD*/
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
	// tache 30 exporter les references des journaux pour creér de liens entre
   // les noeuds journaus
	static List<Reference> exportRefOfJournals()
	{
		 int i=1;
		// ListeReferenceOfArticle() methode de webminin base de données
		 List<Reference> listRef=ListeReferenceOfArticle() ;
		 List<Reference> newListOfRef=new ArrayList<Reference>();
		 for (Reference ref:listRef)
		 {       // je récupére l id du journal correspodant a l id d article (source et target)
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
	
	// methode implemente par web mining qui retourne liste des references des articles 
	public static List<Reference> ListeReferenceOfArticle() 
	{
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
