package fr.bibliotool.modele;



import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.bibliotool.modele.*;
public class ReadXmlFile {

	public static NodeList nList;
  public static void main(String argv[]) {
	  List<Noeud> Noeuds= new ArrayList<Noeud>();//Lste de noeuds
		List<Edge> Edges=new ArrayList<Edge>();
    try {
	File fXmlFile = new File("src/fr/bibliotool/modele/graph.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	nList = doc.getElementsByTagName("article");
	List<String> author = new ArrayList<String>();
	System.out.println("----------------------------");
	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
		Noeud noeud= new Noeud(); 
		Article article= new Article();
	
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
			article.setTitre(eElement.getElementsByTagName("title").item(0).getTextContent());
			article.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
			article.setPage(eElement.getElementsByTagName("pages").item(0).getTextContent());
			article.setJournal(eElement.getElementsByTagName("pages").item(0).getTextContent());
			article.setAnnee(Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent()));
			article.setVolume(Integer.parseInt(eElement.getElementsByTagName("volume").item(0).getTextContent()));
			NodeList lst = doc.getDocumentElement().getElementsByTagName("authors").item(temp).getChildNodes();
			for (int i = 0; i < lst.getLength(); i++) {	
				Node node = lst.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement1 = (Element) node;
					//System.out.println(lst.item(i).getTextContent());
					author.add(lst.item(i).getTextContent());

				}
			}
			NodeList lst1 = doc.getDocumentElement().getElementsByTagName("reference").item(temp).getChildNodes();
			List<String> ref = new ArrayList<String>();
			for (int i = 0; i < lst1.getLength(); i++) {
				Node node = lst1.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement1 = (Element) node;
					 Edge edge = new Edge();
					 edge.setSource(Integer.parseInt(eElement1.getElementsByTagName("source").item(0).getTextContent()));
					 edge.setTarget(Integer.parseInt(eElement1.getElementsByTagName("target").item(0).getTextContent()));
					 Edges.add(edge);

				}
			    ref.add(lst1.item(i).getTextContent());
			  
			}
			article.setAuteur(author);
			article.setReferences(ref);
			noeud= new Noeud(article);
			Noeuds.add(noeud);
			
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
    Graphe graph = new Graphe(Noeuds,Edges);
	for (Noeud e : graph.getNoeuds()) {
	  System.out.println(e.getArticle().getTitre());
	}
  }

}