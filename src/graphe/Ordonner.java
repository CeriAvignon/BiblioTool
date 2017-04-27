package graphe;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.gephi.graph.api.Node;


public class Ordonner{
	     
	    private static Map<Node,List<Node>> voisins = new HashMap<Node,List<Node>>();
	   
	    public String toString () {
	        StringBuffer s = new StringBuffer();
	        for (Node n: voisins.keySet()) s.append("\n    " + n + " -> " + voisins.get(n));
	        return s.toString();                
	    }
	    
	   
	    public void add (Node n) {
	        if (!voisins.containsKey(n))
	        	voisins.put(n, new ArrayList<Node>());
	    }
	    
	    
	    public boolean contains (Node n) {
	        return voisins.containsKey(n);
	    }
	    
	    
	    public void add (Node n1, Node n2) {
	        this.add(n1); this.add(n2);
	        voisins.get(n1).add(n2);
	    }
	    
	    
	    public void remove (Node n1, Node n2) {
	        if (!(this.contains(n1) && this.contains(n2)))
	            throw new IllegalArgumentException("Le noeud n'existe pas");
	        voisins.get(n1).remove(n2);
	    }
	    
	    // degree : le nombre des arcs qui adjacente à un noeud.
	    public Map<Node,Integer> outDegree () {
	        Map<Node,Integer> resultat = new HashMap<Node,Integer>();
	        for (Node n: voisins.keySet()) resultat.put(n, voisins.get(n).size());
	        return resultat;
	    }
	    
	    public static Map<Node,Integer> inDegree () {
	        Map<Node,Integer> resultat = new HashMap<Node,Integer>();
	        for (Node n: voisins.keySet()) resultat.put(n, 0);       
	        for (Node n1: voisins.keySet()) {
	            for (Node n2: voisins.get(n1)) {
	                resultat.put(n2, resultat.get(n2) + 1);
	            }
	        }
	        return resultat;
	    }
	    
	   
	    public static List<Node> Sort () {
	        Map<Node, Integer> degree = inDegree();
	        Stack<Node> zeroNodes = new Stack<Node>();        
	        for (Node n: degree.keySet()) {
	            if (degree.get(n) == 0) zeroNodes.push(n);
	        }
	      
	        List<Node> resultat = new ArrayList<Node>();
	        while (!zeroNodes.isEmpty()) {
	            Node n = zeroNodes.pop();                  
	            resultat.add(n);                         
	            
	            for (Node voisin: voisins.get(n)) {
	                degree.put(voisin, degree.get(neighbor) - 1);
	               
	                if (degree.get(voisin) == 0) zeroNodes.push(voisin);
	            }
	        }
	        
	        if (resultat.size() != voisins.size()) return null;
	        return resultat;
	    }
	    
	  
	    
	  
}





