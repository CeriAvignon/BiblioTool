package graphe;

import java.util.ArrayList;
import java.util.List;

import org.gephi.graph.api.*;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public class ManipulateGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.ListeReference());
		
		DirectedGraph dg = MyGraph.createDirectedGraph();
		List<Node> selectedNodes=new ArrayList<Node>();
	
		//System.out.println("Nodes: " + dg.getNodeCount() + " Edges: " + dg.getEdgeCount());
		
		/*******/

		Node node1 = dg.getNode("1");
		Node node2 = dg.getNode("2");
		Node node3 = dg.getNode("3");

		selectedNodes.add(node1);
		selectedNodes.add(node2);
		selectedNodes.add(node3);


		List<String> listInfoSelectedNodes1=new ArrayList<String>();
		listInfoSelectedNodes1=MyGraph.nodeInfo(selectedNodes);
		System.out.println("");

		int i=0;
		for (String s : listInfoSelectedNodes1) {
		//	System.out.println("Les information du noeud "+i+" sont :");
			i++;
		//	System.out.println("***"+s+"***");
		}
		System.out.println("");

		/**********/		
		
		 ArrayList<ArrayList<Node>> cocitant=new  ArrayList<ArrayList<Node>>();
		 ArrayList<Node> n=new ArrayList<Node> ();
		 cocitant=MyGraph.returnNodesCocitant();
		System.out.println("");
		System.out.println("***Le resultat de regroupement des noeuds par cocitation (co_citant) ****");

		for(int h =0;h<cocitant.size();h++)
		{
			n = cocitant.get(h);
			System.out.println("***"+h+") ****");
			for(int j=0;j<n.size();j++)

			{
				System.out.println("***"+n.get(j).getLabel()+"*****");

				
			}
			
			
		}
		System.out.println("");

		System.out.println("***Affichage des arcs qui relient les noeuds co_citant ****");

		DirectedGraph dg2 = MyGraph.createUndirectedGraph2(cocitant);

		for (Edge e : dg2.getEdges())
		{
	           System.out.println(e.getSource().getId() + " -> " + e.getTarget().getId());
	    }

		
		
		
	}
}
