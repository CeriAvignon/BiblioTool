package Graph;

/**
 * 
 * @author Shuai Yang  
 * 
 * Version Alpha du squelette du graphe (la version beta sera en accord avec le groupe qui g�re le graphe)
 * Cr�ation de la classe Node qui cr�er un noeud
 *
 */

import java.util.Vector;
public class Node {
	private String name;
	private Vector<Node> quoted_articles;
	private Vector<Node> referent_articles;
	
	public Node(String name, Vector<Node> quoted_articles, Vector<Node> referent_articles){
		this.name = name;
		this.setQuoted_articles(quoted_articles);
		this.setReferent_articles(referent_articles);
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public Vector<Node> getQuoted_articles() {
		return quoted_articles;
	}

	public void setQuoted_articles(Vector<Node> quoted_articles) {
		this.quoted_articles = quoted_articles;
	}

	public Vector<Node> getReferent_articles() {
		return referent_articles;
	}

	public void setReferent_articles(Vector<Node> referent_articles) {
		this.referent_articles = referent_articles;
	}
	
	//m�thode qui disparaitra dans la version b�ta
	private String giveItemsVector(Vector<Node> tmp_nodes) throws NullPointerException{
		int size = tmp_nodes.size();
		Vector<String> tmp_s = new Vector<String>(size);
		for(int i=0; i < size; i++){
			if(tmp_nodes.get(i) != null)
				tmp_s.addElement(tmp_nodes.get(i).getName());
			else{
				throw new NullPointerException("un article est null");
			}
		}
		String return_s = "(";
		for(int i=0; i < size; i++){
			if(i != 0)
				return_s += ", ";
			return_s += "'"+tmp_s.get(i)+"'";
			if(i == size-1)
				return_s += ")";
		}
		return return_s;
	}
	
	public String display(){ //display th�orique dans la console (sera ensuite coder dans une des t�che)
		String return_q = giveItemsVector(this.quoted_articles);
		String return_r = giveItemsVector(this.referent_articles);
		return ("Affichage du noeud : '"+name+"' | il cite : "+return_q+" | et il est r�f�renc� par : "+return_r+" .");
	}
}










