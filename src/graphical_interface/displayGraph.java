package graphical_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.appearance.api.Partition;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.graph.DegreeRangeBuilder.DegreeRangeFilter;
import org.gephi.filters.plugin.partition.PartitionBuilder.NodePartitionFilter;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.gephi.graph.api.Node;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.AutoLayout;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.preview.api.G2DTarget;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.api.RenderTarget;
import org.gephi.preview.plugin.items.NodeLabelItem;
import org.gephi.preview.types.DependantColor;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import graphical_interface.PreviewSketch;

public class displayGraph {

	public DirectedGraph directedGraph;
	

	public void displayGexf() {
    	
    	///////////////////////////////////////////////////////////////
    	
    		//Affiche le graphe contenu dans le fichier Java.gexf (placé dans le répertoire bin)
    	 
        //Init a project
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Import file
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        Container container;
        try {
            File file = new File(getClass().getResource("graph.gexf").toURI());
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
        previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR, new DependantOriginalColor(Color.WHITE));
        previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.FALSE);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_OPACITY, 50);
        previewModel.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR, Color.BLACK);
        previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_PROPORTIONAL_SIZE, Boolean.TRUE);
        
        previewModel.getProperties().putValue(PreviewProperty.NODE_BORDER_WIDTH, 2);
        previewModel.getProperties().putValue(PreviewProperty.NODE_BORDER_COLOR, new DependantColor(Color.GREEN));
        previewModel.getProperties().putValue(NodeLabelItem.VISIBLE, Boolean.FALSE);
        previewModel.getProperties().putValue(PreviewProperty.NODE_BORDER_COLOR, new DependantColor(Color.WHITE));
        
        //New Processing target, get the PApplet
        G2DTarget target = (G2DTarget) previewController.getRenderTarget(RenderTarget.G2D_TARGET);
        final PreviewSketch previewSketch = new PreviewSketch(target);
        previewController.refreshPreview();

        //Add the applet to a JFrame and display
        /*JFrame frame = new JFrame("Preview");
=======
        JFrame frame = new JFrame("Preview");
>>>>>>> refs/remotes/origin/IG-24
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(previewSketch, BorderLayout.CENTER);
     
      //Wait for the frame to be visible before painting, or the result drawing will be strange
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                previewSketch.resetZoom();
            }
        });
        
        frame.setSize(1024, 768);
        frame.setVisible(true);
<<<<<<< HEAD
        */
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(previewSketch,BorderLayout.CENTER);
        
        JLabel titre = new JLabel("Choix de la sauvegarde :");
        panel.add(titre, BorderLayout.SOUTH);
        //panel.add(titre);
        
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        //frame.add(new JButton("1"), BorderLayout.WEST);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                previewSketch.resetZoom();
            }
        });
        frame.setSize(1024, 768);
        frame.setVisible(true);
        
        
        /*GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
        DegreeRangeFilter degreeFilter = new DegreeRangeFilter();
        degreeFilter.init(graphModel.getGraph());
        degreeFilter.setRange(new Range(10, Integer.MAX_VALUE));     //Remove nodes with degree < 10
        Query query = filterController.createQuery(degreeFilter);
        GraphView view = filterController.filter(query);
        graphModel.setVisibleView(view);    //Set the filter result as the visible view

        //Count nodes and edges on filtered graph
        DirectedGraph graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());
        */
        
        
        /*GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        AppearanceModel appearanceModel = Lookup.getDefault().lookup(AppearanceController.class).getModel();
        FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
        NodePartitionFilter partitionFilter = new NodePartitionFilter(graphModel.getNodeTable().getColumn("isSelected"), appearanceModel);
        partitionFilter.unselectAll();
        partitionFilter.addPart(Boolean.TRUE);
        Query query2 = filterController.createQuery(partitionFilter);
        GraphView view2 = filterController.filter(query2);
        graphModel.setVisibleView(view2);    //Set the filter result as the visible view
        DirectedGraph graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());
        previewController.refreshPreview();*/
        
    }
	

	public void loadMatrix() {
		//Charge un graphe contenu dans un fichier .gexf et le place dans this.directedGraph
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
	    pc.newProject();
	    Workspace workspace = pc.getCurrentWorkspace();
	    
		ImportController importController = Lookup.getDefault().lookup(ImportController.class);
	    Container container;
	    try {
	    	File file = new File(getClass().getResource("graph_base.gexf").toURI());
	        container = importController.importFile(file);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return;
	    }

	    importController.process(container, new DefaultProcessor(), workspace);
	    GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
	    
	    //Rajout d'un attribut isSelected à chaque noeud
	    Column col = graphModel.getNodeTable().addColumn("isSelected", Boolean.class);
	    for (Node n : graphModel.getGraph().getNodes()) {
            n.setAttribute(col, Boolean.FALSE);
        }
	    
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
		
		//article déjà lu ou non
		//pdf disponible ou non
		
		int i =1;
	    for(Node n : this.directedGraph.getNodes()) {
	    	if (i>3) {
	    		n.setColor(Color.BLUE);
	    	}
	    	else {
	    		i++;
	    		n.setColor(Color.RED);
	    		//n.setSize();
	    	}
	    }	  
	}

	/*public static void filtre() {
		 GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
	        AppearanceModel appearanceModel = Lookup.getDefault().lookup(AppearanceController.class).getModel();
	        FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
	        NodePartitionFilter partitionFilter = new NodePartitionFilter(graphModel.getNodeTable().getColumn("isSelected"), appearanceModel);
	        partitionFilter.unselectAll();
	        partitionFilter.addPart(Boolean.TRUE);
	        Query query2 = filterController.createQuery(partitionFilter);
	        GraphView view2 = filterController.filter(query2);
	        graphModel.setVisibleView(view2);    //Set the filter result as the visible view
	        DirectedGraph graph = graphModel.getDirectedGraphVisible();
	        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());
	        PreviewController previewController = Lookup.getDefault().lookup(PreviewController.class);
	        previewController.refreshPreview();
	}*/
	public void saveGexf() {
		//sauvegarde le graphe dans un fichier gexf
		ExportController ec = Lookup.getDefault().lookup(ExportController.class);
	    try {
	        ec.exportFile(new File("bin/graphGephi/graph.gexf"));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return;
	    }
	}
	

	
	public static void main(String[] args) {
		displayGraph d = new displayGraph();
		d.loadMatrix();
		d.layoutGraph();
		d.changeColor();
		d.saveGexf();
		d.displayGexf();
	}
}
