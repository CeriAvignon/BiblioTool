package graphical_interface;

import java.awt.Color;

import org.gephi.filters.api.FilterController;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualStringFilter;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewMouseEvent;
import org.gephi.preview.api.PreviewProperties;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.spi.PreviewMouseListener;
import org.gephi.preview.types.DependantColor;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = PreviewMouseListener.class)
public class MouseListenerTemplate implements PreviewMouseListener {

    @Override
    public void mouseClicked(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace) {
    	for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes()) {
            if (clickingInNode(node, event)) {
                properties.putValue("display-label.node.id", node.getId());                
                if (node.getAttribute("isSelected") == Boolean.TRUE){
                	node.setColor(Color.RED);
                	node.setAttribute("isSelected", Boolean.FALSE);
                	//Edge[] e = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getEdges(node).toArray();
                	//Node[] neighbors = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNeighbors(node).toArray();
                    //System.out.println(node.getLabel() + " has " + neighbors.length + " neighbors");
                }
                else if (node.getAttribute("isSelected") == Boolean.FALSE){
                	node.setColor(Color.ORANGE);
                	node.setAttribute("isSelected", Boolean.TRUE);
                }
                
                PreviewController previewController = Lookup.getDefault().lookup(PreviewController.class);
                previewController.refreshPreview();
                //display.filtre();
                //PreviewModel previewModel = previewController.getModel();
                //previewModel.getProperties().putValue(PreviewProperty.NODE_BORDER_COLOR, new DependantColor(Color.BLUE));
                
                //event.setConsumed(true);//So the renderer is executed and the graph repainted
                return;
            }
        }

        //properties.removeSimpleValue("display-label.node.id");
        //event.setConsumed(true);//So the renderer is executed and the graph repainted
    }

    @Override
    public void mousePressed(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace) {
    }

    @Override
    public void mouseDragged(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace) {
    }

    @Override
    public void mouseReleased(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace) {
    }

    private boolean clickingInNode(Node node, PreviewMouseEvent event) {
        float xdiff = node.x() - event.x;
        float ydiff = -node.y() - event.y;//Note that y axis is inverse for node coordinates
        float radius = node.size();

        return xdiff * xdiff + ydiff * ydiff < radius * radius;
    }
}