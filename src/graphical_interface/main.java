package graphical_interface;

import java.io.IOException;
import org.gephi.layout.plugin.AutoLayout;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import org.gephi.graph.api.Node;
import javax.swing.JFrame;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.graph.DegreeRangeBuilder.DegreeRangeFilter;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.preview.api.*;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
//import org.gephi.toolkit.demos.plugins.preview.PreviewSketch;
import org.openide.util.Lookup;
import org.gephi.io.exporter.api.ExportController;


/**
 *
 * @author Mathieu Bastian
 */
public class main {

	public DirectedGraph directedGraph;

	public main(){}
	
	public main(DirectedGraph dg)
	{
		this.directedGraph=dg;
	}
	
    public void script() {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Import file
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        Container container;
        try {
            File file = new File(getClass().getResource("testGephi.gexf").toURI());
            container = importController.importFile(file);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);

        //Preview configuration
        PreviewController previewController = Lookup.getDefault().lookup(PreviewController.class);
        PreviewModel previewModel = previewController.getModel();
        previewModel.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS, Boolean.TRUE);
        previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR, new DependantOriginalColor(Color.BLACK));
        previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.FALSE);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_OPACITY, 50);
        previewModel.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR, Color.WHITE);

        //New Processing target, get the PApplet
        G2DTarget target = (G2DTarget) previewController.getRenderTarget(RenderTarget.G2D_TARGET);
        final PreviewSketch previewSketch = new PreviewSketch(target);
        previewController.refreshPreview();

        //Add the applet to a JFrame and display
        JFrame frame = new JFrame("Test Preview");
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(previewSketch, BorderLayout.CENTER);

        frame.setSize(1024, 768);
        
        //Wait for the frame to be visible before painting, or the result drawing will be strange
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                previewSketch.resetZoom();
            }
        });
        frame.setVisible(true);
    }

    public void loadMatrix() {
		//Charge un graphe contenu dans un fichier .gexf et le place dans this.directedGraph
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
	    pc.newProject();
	    Workspace workspace = pc.getCurrentWorkspace();
		ImportController importController = Lookup.getDefault().lookup(ImportController.class);
	    Container container;
	    try {
	    	File file = new File(getClass().getResource("testGephi.gexf").toURI());
	        container = importController.importFile(file);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return;
	    }

	    importController.process(container, new DefaultProcessor(), workspace);
	    GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
	    this.directedGraph = graphModel.getDirectedGraph();
	    
	    
	}
    
	public void layoutGraph() {
		//permet de positionner les noeuds
		YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(1f));
		layout.setGraphModel(this.directedGraph.getModel());
		layout.initAlgo();
		layout.resetPropertiesValues();

		for (int i = 0; i < 100 && layout.canAlgo(); i++) {
		   layout.goAlgo();
		}
		layout.endAlgo();
	}
	
	public void changeColor() {	    
	    //modifie la couleur des noeuds
		int i =1;
	    for(Node n : this.directedGraph.getNodes()) {
	    	if (i>3) {
	    		n.setColor(Color.BLUE);
	    	}
	    	else {
	    		i++;
	    		n.setColor(Color.RED);
	    	}
	    }	  
	}
	
	public void saveGexf() {
		//sauvegarde le graphe dans un fichier gexf
		ExportController ec = Lookup.getDefault().lookup(ExportController.class);
	    try {
	        ec.exportFile(new File("bin/gephi/testGephi.gexf"));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return;
	    }
	}
	
	public static void main(String[] args) {
    	MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.ListeReference());
		DirectedGraph dg = MyGraph.createDirectedGraph();
		main myGraph = new main(dg);
		//myGraph.loadMatrix();
		//myGraph.layoutGraph();
		//myGraph.changeColor();
		System.out.println("Nodes: " + myGraph.directedGraph.getNodeCount() + " Edges: " + myGraph.directedGraph.getEdgeCount());
		myGraph.saveGexf();
		myGraph.script();

		for (Edge e : myGraph.directedGraph.getEdges()) {
			System.out.println(e.getId()+" : " + e.getSource().getId() + " -> " + e.getTarget().getId());
		}
        MyGraph.showNeighbors(myGraph.directedGraph);

    }

}
