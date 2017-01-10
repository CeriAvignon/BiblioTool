package fr.bibliotool.modele;
//Build gexf file 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.w3c.dom.NodeList;

import com.itextpdf.text.log.SysoLogger;

import it.uniroma1.dis.wsngroup.gexf4j.core.EdgeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf;
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph;
import it.uniroma1.dis.wsngroup.gexf4j.core.Mode;
import it.uniroma1.dis.wsngroup.gexf4j.core.Node;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.Attribute;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeClass;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeList;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.StaxGraphWriter;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.data.AttributeListImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.NodeShape;

public class GexfGraph {

	public static void main(String[] args) {
		
		Gexf gexf = new GexfImpl();
		Calendar date = Calendar.getInstance();
		Graphe graphe = XmlToGraph.parseXml();
		
		gexf.getMetadata()
		    .setLastModified(date.getTime())
		    .setCreator("Gephi.org")
		    .setDescription("A Web network");

		AttributeList attrList = new AttributeListImpl(AttributeClass.NODE);
		gexf.getGraph().getAttributeLists().add(attrList);
		gexf.getGraph().setDefaultEdgeType(EdgeType.DIRECTED);
		Attribute attTitle = attrList.createAttribute("0", AttributeType.STRING, "title");
		Attribute attAuthor = attrList.createAttribute("1", AttributeType.LISTSTRING, "author");
		Attribute attYear = attrList.createAttribute("2", AttributeType.INTEGER, "year");
		Attribute attPage = attrList.createAttribute("3", AttributeType.STRING, "page");
		Attribute attVolume = attrList.createAttribute("4", AttributeType.STRING, "volume");
		Attribute attJournal = attrList.createAttribute("5", AttributeType.STRING, "journal");
		Attribute attUrl = attrList.createAttribute("6", AttributeType.STRING, "url");
		
		for (Noeud noeud : graphe.getNoeuds()) {
			Node node = gexf.getGraph().createNode(Integer.toString(noeud.getId()));
			node
		    .setLabel(noeud.getArticle().getTitre())
		    .getAttributeValues()
		        .addValue(attTitle, noeud.getArticle().getTitre())
		        .addValue(attYear, Integer.toString(noeud.getArticle().getAnnee()))
		        .addValue(attPage, noeud.getArticle().getPage())
		        .addValue(attVolume, Integer.toString(noeud.getArticle().getVolume()))
		        .addValue(attJournal, noeud.getArticle().getJournal())
		        .addValue(attUrl, noeud.getArticle().getUrl())
		        ;
		        
			for (int i = 0; i < noeud.getArticle().getAuteur().size(); i++) {
			 node.getAttributeValues().addValue(attAuthor, noeud.getArticle().getAuteur().get(i));
			 //System.out.println(noeud.getArticle().getAuteur().get(i));
			}
			
		}
		Graph node = gexf.getGraph();
		List<Node> nodes = node.getNodes();
        int i = 0;
			for (Edge edge : graphe.getEdges()) {
				Node source = getNoeud(edge.getSource()-1,nodes);
				Node target = getNoeud(edge.getTarget()-1,nodes);
				source.connectTo(Integer.toString(i), Integer.toString(i), EdgeType.DIRECTED, target);
				i++;
			}

		StaxGraphWriter graphWriter = new StaxGraphWriter();
		File f = new File("src/fr/bibliotool/modele/text.gexf");
		Writer out;
		try {
			out =  new FileWriter(f, false);
			graphWriter.writeToStream(gexf, out, "UTF-8");
			System.out.println(f.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
//		for (Noeud noeud : graphe.getNoeuds()){
//			System.out.println(noeud.getId());
//		}
//		for (Node noded : gexf.getGraph().getNodes()){
//			System.out.println(noded.getId());
//		}
		
		System.out.println(genererListeArticle(gexf.getGraph().getNodes(), graphe.getNoeuds()));
		
	}
	 
	public static Node getNoeud(int id, List<Node> noeuds){
		 Node noeud = null;
		 for(Node node1: noeuds){
			 if(Integer.parseInt(node1.getId()) == id){
				 noeud = node1;
			 }
		}
       return noeud; 
	 }
	
	public static List<Article> genererListeArticle(List<Node> nodes, List<Noeud> noeuds){
		List<Article> articles = new ArrayList<>();
			for (Noeud noeud : noeuds) {
				for (Node node : nodes) {
					if (Integer.parseInt(node.getId()) == noeud.getId()) {
						articles.add(noeud.getArticle());
					}
				}		
			}
		
		return articles;
	}
}
