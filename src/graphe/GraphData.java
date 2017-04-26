package graphe;

import java.util.Arrays;

public class GraphData {
	private double nodes;
	private double edges;
	private double density;
	private double radius;
	private double diameter;
	//Mesure la fréquence d'apparition d'un noeud sur les plus courts chemins entre les noeuds du réseau
	private double[] betweeness;
	//Mesure la distance depuis un noeud de départ vers le noeud le plus loin dans le réseau
	private double[] eccentricity;
	//Mesure la distance moyenne depuis un noeud de départ vers tous les noeuds du réseau
	private double[] closeness;
	private double[] harmonicCloseness;
	public double getNodes() {
		return nodes;
	}
	public void setNodes(double nodes) {
		this.nodes = nodes;
	}
	public double getEdges() {
		return edges;
	}
	public void setEdges(double edges) {
		this.edges = edges;
	}
	public double getDensity() {
		return density;
	}
	public void setDensity(double density) {
		this.density = density;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getDiameter() {
		return diameter;
	}
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	public double[] getBetweeness() {
		return betweeness;
	}
	public void setBetweeness(double[] betweeness) {
		this.betweeness = betweeness;
	}
	public double[] getEccentricity() {
		return eccentricity;
	}
	public void setEccentricity(double[] eccentricity) {
		this.eccentricity = eccentricity;
	}
	public double[] getCloseness() {
		return closeness;
	}
	public void setCloseness(double[] closeness) {
		this.closeness = closeness;
	}
	public double[] getHarmonicCloseness() {
		return harmonicCloseness;
	}
	public void setHarmonicCloseness(double[] harmonicCloseness) {
		this.harmonicCloseness = harmonicCloseness;
	}
	@Override
	public String toString() {
		return "GraphData [nodes=" + nodes + ", edges=" + edges + ", density=" + density + ", radius=" + radius
				+ ", diameter=" + diameter + ", betweeness=" + Arrays.toString(betweeness) + ", eccentricity="
				+ Arrays.toString(eccentricity) + ", closeness=" + Arrays.toString(closeness) + ", harmonicCloseness="
				+ Arrays.toString(harmonicCloseness) + "]";
	}
	
	

}
