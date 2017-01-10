package fr.bibliotool.modele;

public class Edge {
	private int id;
	private int source;
	private int target;
	private static int i=0;

	public Edge() {
		// TODO Auto-generated constructor stub

	}
	

	public Edge(int source, int target) {
		this.id = i++;
		this.source = source;
		this.target = target;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSource() {
		return source;
	}


	public void setSource(int source) {
		this.source = source;
	}


	public int getTarget() {
		return target;
	}


	public void setTarget(int target) {
		this.target = target;
	}
	
	
}
