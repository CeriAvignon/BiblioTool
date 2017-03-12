package graphe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.spi.CharacterExporter;
import org.gephi.io.exporter.spi.Exporter;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;
public class extractDesc {
    private static MyGraph graph;
	public static void main(String[] args) {
		double density;
		DirectedGraph directedGraph = graph.createDirectedGraph();		
		System.out.println("la densité est de "+ calculateDensity(directedGraph));	
		calculateDiameter(directedGraph);
	}
	public static double calculateDensity(Graph graph){
        GraphDensity d = new GraphDensity();
        double density = d.calculateDensity(graph, false);
        return density;
	}
	public static void calculateDiameter(Graph graph){
		//Map<String, double[]> metrics;
		//MESURE LA fréquence d'apparition d'un noeud sur les plus courts chemins entre les noeuds du réseau
		double[] betweenness;
		//la distance moyenne depuis un noeud de départ vers tous les noeuds du réseau
		double[] closeness;
		double[] harmonicCloseness;
		//la distance depuis un noeud de départ vers le noeud le plus loin dans le réseau
	    double[] eccentricity;
        GraphDistance d = new GraphDistance();
        // créer un map d'indicies
        HashMap<Node, Integer> indicies = d.createIndiciesMap(graph);   
        for (Integer val : indicies.values()) {
          System.out.println(val);
        }
        Map<String, double[]> metrics1 = d.calculateDistanceMetrics(graph, indicies, true, false);
        eccentricity = metrics1.get(d.ECCENTRICITY);
        closeness = metrics1.get(d.CLOSENESS);
        harmonicCloseness = metrics1.get(d.HARMONIC_CLOSENESS);
        betweenness = metrics1.get(d.BETWEENNESS);
        System.out.println("le rayon est de "+ d.getRadius());
        System.out.println("le diamètre est de "+ d.getDiameter());
    	for (double e : closeness) {
			System.out.println(e);
		}
    	System.out.println(d.getReport());
	}	
	
	


}
