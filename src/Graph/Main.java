package Graph;

/**
 * 
 * @author Shuai Yang  
 * 
 * Version Alpha du squelette du graphe (la version beta sera en accord avec le groupe qui g�re le graphe)
 * Cr�ation de la classe Main qui permet de simuler un graphe
 *
 */

import java.util.Vector;
public class Main {
	public static void main(String[] args){
		try{
			Node testq = new Node("testq", null, null);
			Node testr = new Node("testr", null, null);
			Vector<Node> nq = new Vector<Node>();
			nq.addElement(testq);
			nq.addElement(testq);
			nq.addElement(testr);
			nq.addElement(testq);
			Vector<Node> nr = new Vector<Node>();
			nr.addElement(testr);
			nr.addElement(testq);
			Node middle_node = new Node("middle_node", nq, nr); //dim 0
			//System.out.println(middle_node.display());
			
			Vector<Vector<Node>> dim = new Vector<Vector<Node>>();
			Vector<Node> entry_point = new Vector<Node>(); //dim 0
			entry_point.addElement(middle_node);
			entry_point.addElement(middle_node);
			dim.addElement(entry_point);
			dim.addElement(entry_point);
			Tree tree = new Tree(dim);
			System.out.println(tree.display());
			
			System.out.println("End of the program.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
