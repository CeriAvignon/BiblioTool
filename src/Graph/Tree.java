package Graph;

/**
 * 
 * @author Shuai Yang  
 * 
 * Version Alpha du squelette du graphe (la version beta sera en accord avec le groupe qui g�re le graphe)
 * Cr�ation de la classe Tree qui cr�er le graphe
 *
 */

import java.util.Vector;
public class Tree {
	private Vector<Vector<Node>> dimension;
	
	public Tree(Vector<Vector<Node>> dimension){
		this.setDimension(dimension);
	}

	public Vector<Vector<Node>> getDimension() {
		return dimension;
	}

	public void setDimension(Vector<Vector<Node>> dimension) {
		this.dimension = dimension;
	}
	
	public String display(){ //display th�orique dans la console (sera ensuite coder dans une des t�che)
		String str = "";
		for(int i=0; i < dimension.size(); i++){
			str += "dimension "+i+" : ";
			for(int j=0; j < dimension.get(i).size(); j++){
				str += dimension.get(i).get(j).display();
				if(j != dimension.get(i).size())
					str += " | ";
			}
			if(i != dimension.size()-1)
				str += "\n";
		}
		return str;
	}
}
