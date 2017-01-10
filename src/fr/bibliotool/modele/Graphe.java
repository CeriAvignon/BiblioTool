package fr.bibliotool.modele;

import java.util.List;

public class Graphe {

	private int id;
	private List<Noeud> noeuds;
	private List<Edge> edges;
	private static int i=0;
	
	public Graphe() {
		super();
	}
	public Graphe(List<Noeud> noeuds, List<Edge> edges) {
		this.id=i++;
		this.noeuds = noeuds;
		this.edges = edges;
	}
	public Graphe(int id, List<Noeud> noeuds, List<Edge> edges) {
		this.id = id;
		this.noeuds = noeuds;
		this.edges = edges;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Noeud> getNoeuds() {
		return noeuds;
	}
	public void setNoeuds(List<Noeud> noeuds) {
		this.noeuds = noeuds;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	
	
}
