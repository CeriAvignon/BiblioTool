/*
Copyright 2008-2010 Gephi
Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphe;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.appearance.api.Function;
import org.gephi.appearance.api.Partition;
import org.gephi.appearance.api.PartitionFunction;
import org.gephi.appearance.plugin.PartitionElementColorTransformer;
import org.gephi.appearance.plugin.palette.Palette;
import org.gephi.appearance.plugin.palette.PaletteManager;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.Modularity;
import org.openide.util.Lookup;

public class PartitionGraph {

    public void script() {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class);
        AppearanceModel appearanceModel = appearanceController.getModel();

        //Import file
        Container container;
        try {
            File file = new File(getClass().getResource("polblogs.gml").toURI());
            container = importController.importFile(file);
            container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED);   //Force DIRECTED
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);

        //See if graph is well imported
        DirectedGraph graph = graphModel.getDirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());

        //Partition with 'source' column, which is in the data
//        Column column = graphModel.getNodeTable().getColumn("source");
//        Function func = appearanceModel.getNodeFunction(graph, column, PartitionElementColorTransformer.class);
//        Partition partition = ((PartitionFunction) func).getPartition();
//        Palette palette = PaletteManager.getInstance().generatePalette(partition.size());
//        partition.setColors(palette.getColors());
//        appearanceController.transform(func);
//
//        //Export
//        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
//        try {
//            ec.exportFile(new File("partition1.pdf"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return;
//        }
//
//        //Run modularity algorithm - community detection
//        Modularity modularity = new Modularity();
//        modularity.execute(graphModel);
//
//        //Partition with 'modularity_class', just created by Modularity algorithm
//        Column modColumn = graphModel.getNodeTable().getColumn(Modularity.MODULARITY_CLASS);
//        Function func2 = appearanceModel.getNodeFunction(graph, modColumn, PartitionElementColorTransformer.class);
//        Partition partition2 = ((PartitionFunction) func2).getPartition();
//        System.out.println(partition2.size() + " partitions found");
//        Palette palette2 = PaletteManager.getInstance().randomPalette(partition2.size());
//        partition2.setColors(palette2.getColors());
//        appearanceController.transform(func2);

        partitionGraph(graph, "source");
        partitionGraph(graph);
        
        
    }
    
    public void partitionGraph(DirectedGraph graph){
		AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class);
        AppearanceModel appearanceModel = appearanceController.getModel();
        
        Modularity modularity = new Modularity();
        modularity.execute(graph.getModel());
        
        Column modColumn = graph.getModel().getNodeTable().getColumn(Modularity.MODULARITY_CLASS);
        Function func2 = appearanceModel.getNodeFunction(graph, modColumn, PartitionElementColorTransformer.class);
        Partition partition2 = ((PartitionFunction) func2).getPartition();
        System.out.println(partition2.size() + " partitions found");
        Palette palette2 = PaletteManager.getInstance().randomPalette(partition2.size());
        partition2.setColors(palette2.getColors());
        appearanceController.transform(func2);
        
      //Export
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        try {
            ec.exportFile(new File("partition1.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
	}
    
    public void partitionGraph(DirectedGraph graph, String string){
		AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class);
        AppearanceModel appearanceModel = appearanceController.getModel();
		
		Column column = graph.getModel().getNodeTable().getColumn(string);
		Function func = appearanceModel.getNodeFunction(graph, column, PartitionElementColorTransformer.class);
		Partition partition = ((PartitionFunction) func).getPartition();
        Palette palette = PaletteManager.getInstance().generatePalette(partition.size());
        partition.setColors(palette.getColors());
        appearanceController.transform(func);
		
      //Export
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        try {
            ec.exportFile(new File("partition2.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
		
	}
}
