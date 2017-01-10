package fr.bibliotool.modele;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itextpdf.text.log.SysoLogger;

public class XmlToGraph {
	
	public static NodeList nList;
	
	public static Graphe parseXml(){	
		
		List<Noeud> Noeuds= new ArrayList<Noeud>();//Lste de noeuds
		List<Edge> Edges=new ArrayList<Edge>();
	    
		try {
			File fXmlFile = new File("src/fr/bibliotool/modele/graph.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("article");
			for (int temp = 0; temp < nList.getLength(); temp++) {
		
				Node nNode = nList.item(temp);
				Noeud noeud= new Noeud(); 
				Article article= new Article();		
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					article.setTitre(eElement.getElementsByTagName("title").item(0).getTextContent());
					article.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
					article.setPage(eElement.getElementsByTagName("pages").item(0).getTextContent());
					article.setJournal(eElement.getElementsByTagName("pages").item(0).getTextContent());
					article.setAnnee(Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent()));
					article.setVolume(Integer.parseInt(eElement.getElementsByTagName("volume").item(0).getTextContent()));
					NodeList lst = doc.getDocumentElement().getElementsByTagName("authors").item(temp).getChildNodes();
					List<String> author = new ArrayList<String>();
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
	//	for (Noeud e : graph.getNoeuds()) {
	//	  System.out.println(e.getArticle().getTitre());
	//	}
		return graph;
	}
	
//	public static Noeud creerNoeudfromXml(Article article, Node node, NodeList nodelist){
//			Noeud noeud = null;
//
//			Element eElement = (Element) node;
//			article.setTitre(eElement.getElementsByTagName("title").item(0).getTextContent());
//			article.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
//			article.setPage(eElement.getElementsByTagName("pages").item(0).getTextContent());
//			article.setJournal(eElement.getElementsByTagName("pages").item(0).getTextContent());
//			article.setAnnee(Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent()));
//			article.setVolume(Integer.parseInt(eElement.getElementsByTagName("volume").item(0).getTextContent()));
//			article.setAuteur(articleAuteur(nodelist, node));
//			//article.setReferences(ref);
//			noeud= new Noeud(article);
//		
//	
//		return noeud;
//		
//	}
//	
//	public List<String> articleAuteur(NodeList nodelist, Node node){
//		List<String> author = new ArrayList<String>();
//		for (int i = 0; i < nodelist.getLength(); i++) {	
//			Node art = nodelist.item(i);
//			if (node.getNodeType() == Node.ELEMENT_NODE) {
//				Element eElement1 = (Element) node;
//				//System.out.println(lst.item(i).getTextContent());
//				author.add(nodelist.item(i).getTextContent());
//			}
//		}
//		return author;
//	}
}
