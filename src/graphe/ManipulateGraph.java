package graphe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.gephi.graph.api.*;
import org.gephi.graph.api.Edge;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.spi.CharacterExporter;
import org.gephi.io.exporter.spi.Exporter;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public class ManipulateGraph {

	public static void main(String[] args) {		
		MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.ListeReference());
		MyGraph.setArticles2(MyGraph.retournerListeArticles2());
		DirectedGraph dg = MyGraph.createDirectedGraph();
		DirectedGraph dg2 = MyGraph.createDirectedGraph2();
		System.out.println("Nodes: " + dg2.getNodeCount() + " Edges: " + dg2.getEdgeCount());
		MyGraph.exportGraph("gml");
	}
}
