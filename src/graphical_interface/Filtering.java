package graphical_interface;

import java.io.File;

import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.graph.DegreeRangeBuilder.DegreeRangeFilter;
import org.gephi.filters.plugin.graph.EgoBuilder.EgoFilter;
import org.gephi.filters.plugin.operator.INTERSECTIONBuilder.IntersectionOperator;
import org.gephi.filters.plugin.partition.PartitionBuilder.NodePartitionFilter;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.gephi.graph.api.Node;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

public class Filtering {

    public void script() {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        AppearanceModel appearanceModel = Lookup.getDefault().lookup(AppearanceController.class).getModel();

        //Import file
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

        //Filter, remove degree < 10
        FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
        DegreeRangeFilter degreeFilter = new DegreeRangeFilter();
        degreeFilter.init(graphModel.getGraph());
        degreeFilter.setRange(new Range(2, Integer.MAX_VALUE));     //Remove nodes with degree < 10
        Query query = filterController.createQuery(degreeFilter);
        GraphView view = filterController.filter(query);
        graphModel.setVisibleView(view);    //Set the filter result as the visible view
        
        //Count nodes and edges on filtered graph
        DirectedGraph graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());
	    for (Node node : graph.getNodes()) {
	    	System.out.println("Node: "+node.getLabel() );
	    	for (Edge edge : graph.getEdges()) {   
	    		if(edge.getSource().getId() == node.getId() || edge.getTarget().getId() ==node.getId() )
	    		{
	    			System.out.println("Edge: "+edge.getSource().getLabel() + " " +edge.getTarget().getLabel());
	    		}
	    	}
	    }
        /*
        //Filter, keep partition 'Blogarama'. Build partition with 'source' column in the data
        NodePartitionFilter partitionFilter = new NodePartitionFilter(graphModel.getNodeTable().getColumn("source"), appearanceModel);
        partitionFilter.unselectAll();
        partitionFilter.addPart("Blogarama");
        Query query2 = filterController.createQuery(partitionFilter);
        GraphView view2 = filterController.filter(query2);
        graphModel.setVisibleView(view2);    //Set the filter result as the visible view

        //Count nodes and edges on filtered graph
        graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());

        //Combine two filters with AND - Set query and query2 as sub-query of AND
        IntersectionOperator intersectionOperator = new IntersectionOperator();
        Query query3 = filterController.createQuery(intersectionOperator);
        filterController.setSubQuery(query3, query);
        filterController.setSubQuery(query3, query2);
        GraphView view3 = filterController.filter(query3);
        graphModel.setVisibleView(view3);    //Set the filter result as the visible view

        //Count nodes and edges on filtered graph
        graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());

        //Ego filter
        EgoFilter egoFilter = new EgoFilter();
        egoFilter.setPattern("obamablog.com"); //Regex accepted
        egoFilter.setDepth(1);
        Query queryEgo = filterController.createQuery(egoFilter);
        GraphView viewEgo = filterController.filter(queryEgo);
        graphModel.setVisibleView(viewEgo);    //Set the filter result as the visible view

        //Count nodes and edges on filtered graph
        graph = graphModel.getDirectedGraphVisible();
        System.out.println("Nodes: " + graph.getNodeCount() + " Edges: " + graph.getEdgeCount());*/
    }
}