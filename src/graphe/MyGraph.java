package graphe;

import java.util.ArrayList;
import java.util.List;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.UndirectedGraph;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.EdgeIterable;

import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;
import org.gephi.datalab.api.AttributeColumnsController;
import org.gephi.graph.*;
public final class MyGraph {
	
	private static final String NULL = null;
	public static List<Article> articles;
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
			//n0.setAttribute(ref, article.getReferences());
			n0.setAttribute(status, article.getStatus());
			directedGraph.addNode(n0);
		}
		
		/*System.out.println("les attributs du node sont :");
			for (Column col : graphModel.getNodeTable()) {
				

			System.out.println(col);
		}*/
			
		for (Reference reference : references) {
			Edge e1 = graphModel.factory().newEdge(directedGraph.getNode(String.valueOf(reference.getSource())),
					directedGraph.getNode(String.valueOf(reference.getTarget())), 0, 1.0, true);
			directedGraph.addEdge(e1);
		}
		
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

	
	
	public static List<String> nodeInfo(List<Node> selectedNodes)
	{
		int nbColumn=0;
		List<String> ListInfoSelectedNodes=new ArrayList<String>();
		
		for (Column col : graphModel.getNodeTable())
		{
			nbColumn++;
		}

		
		for (int i=0; i <selectedNodes.size(); i++) //parcourir la liste selectedNodes envoyée par le groupe IG
		{	
			String stringInfoNode="";
			for (int j=0;j<nbColumn;j++)//parcourir les informations du noeud
			{
				Column col=graphModel.getNodeTable().getColumn(j);
				
				if(((selectedNodes.get(i)).getAttribute(col)!=NULL)&&(col.isIndexed()==true))
				{
					String NodeAttribute=(selectedNodes.get(i)).getAttribute(col).toString();
					stringInfoNode=""+stringInfoNode+" "+col.getTitle()+": "+NodeAttribute+", ";
				}
			}
			ListInfoSelectedNodes.add(i,stringInfoNode);
		}
		return (ListInfoSelectedNodes);
	}
	
	
	
	
	
	
	
	
	/*************co_cité******************/
	/**La methode returnNodesCocite() regroupe les noeuds qui sont cité
	 *  par un même noeud et retourne  le resultat dans une liste qui contient dans chaque element 
	 * une autre liste de noeud qui contient ce regrepement par co_cité ***/
	
	public static ArrayList<ArrayList<Node>> returnNodesCocite()
	{
		int nbLigne=0;
		DirectedGraph directedGraph1 = createDirectedGraph();
		nbLigne=directedGraph1.getEdgeCount();
		//System.out.println("numberRow: " + nbLigne);
		EdgeIterable eI=directedGraph1.getEdges();//iterateur sur le tableau des arcs
		Edge[] tabEdges=eI.toArray();//tableau des edges

		ArrayList<ArrayList<Node>> listeNodeCoCite = new ArrayList<ArrayList<Node>>();
		
		/*for (int i=0; i <tabEdges.length; i++) 
		{
			System.out.println("Les information du noeud "+tabEdges[i].getSource().getLabel()+"->"+tabEdges[i].getTarget().getLabel());
		}
*/
		
		for(int i=0;i<nbLigne-1;i++)
		{

			ArrayList<Node> listeOfNodesCiteeParNodes= new ArrayList<Node>();
			listeOfNodesCiteeParNodes.add(tabEdges[i].getTarget());

			for(int j=i+1;j<nbLigne;j++)
			{

				if(tabEdges[i].getSource().getLabel()==tabEdges[j].getSource().getLabel())
				{

					listeOfNodesCiteeParNodes.add(tabEdges[j].getTarget());
					
				}
			}
			listeNodeCoCite.add(listeOfNodesCiteeParNodes);
					
		}
		
		return listeNodeCoCite;

	}
	
	
	/****Le resultat doit être undirected graph, mais pour le moment j'ai fait directed graph****/
	public static DirectedGraph createUndirectedGraph(ArrayList<ArrayList<Node>> listeNodeCoCite){
		DirectedGraph directedGraph1 = createDirectedGraph();
		EdgeIterable eI=directedGraph1.getEdges();//iterateur sur le tableau des arcs
		Edge[] tabEdges=eI.toArray();//tableau des edges
		for (int i=0; i <tabEdges.length; i++) 
		{
			directedGraph1.removeEdge(tabEdges[i]);//suppression de tous les anciens arcs entre les sommets
        }
		int nbLigne=directedGraph1.getEdgeCount();
       // System.out.println("nbEdges:"+nbLigne);
		 ArrayList<Node> n=new ArrayList<Node> ();

        for(int h =0;h<listeNodeCoCite.size();h++)
		{
			n = listeNodeCoCite.get(h);
			if(n.size()>1)
			{
				for(int j=0;j<n.size()-1;j++)
	
				{
					for(int k=j+1;k<n.size();k++)
						
					{
					
						Edge e1 = graphModel.factory().newEdge(n.get(j),n.get(k)); //creation de nouveaux arcs basés sur la relation de co_cité
						directedGraph1.addEdge(e1);
					}
				}
		
			}
		}
	
	
        
     /*Creation de undirected graph n'est pas encore terminée*/   
	/*	ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		Workspace workspace = pc.getCurrentWorkspace();
		graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
		UndirectedGraph unDirectedGraph = graphModel.getUndirectedGraph();
		createColumns();
	*/
        
		
		return directedGraph1;
	}
	
	/******fin co_cité********/
	
	
	
	
	
	
	
}

