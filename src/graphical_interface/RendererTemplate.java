package graphical_interface;

import java.awt.Color;
import java.awt.Graphics2D;
import org.gephi.graph.api.Node;
import org.gephi.preview.api.CanvasSize;
import org.gephi.preview.api.G2DTarget;
import org.gephi.preview.api.Item;
import org.gephi.preview.api.PDFTarget;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperties;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.api.RenderTarget;
import org.gephi.preview.api.SVGTarget;
import org.gephi.preview.spi.ItemBuilder;
import org.gephi.preview.spi.MouseResponsiveRenderer;
import org.gephi.preview.spi.PreviewMouseListener;
import org.gephi.preview.spi.Renderer;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = Renderer.class)
public class RendererTemplate implements Renderer, MouseResponsiveRenderer {

    @Override
    public String getDisplayName() {
        return "Some name";
    }

    @Override
    public void preProcess(PreviewModel previewModel) {
    }

    @Override
    public void render(Item item, RenderTarget target, PreviewProperties properties) {
        //Retrieve clicked node for the label:
        LabelItem label = (LabelItem) item;
        Node node = label.node;

        //Finally draw your graphics for the node label in each target
        if (target instanceof G2DTarget) {
            Graphics2D g = ((G2DTarget) target).getGraphics();

            g.setColor(Color.BLACK);
            g.fillOval((int) node.x(), (int) -node.y(), 5, 5);//Note that y axis is inverse for node coordinates
        } else if (target instanceof PDFTarget) {
        } else if (target instanceof SVGTarget) {
        }
    }

    @Override
    public PreviewProperty[] getProperties() {
        return new PreviewProperty[0];
    }

    @Override
    public boolean isRendererForitem(Item item, PreviewProperties properties) {
        return item instanceof LabelItem;
    }

    @Override
    public boolean needsItemBuilder(ItemBuilder itemBuilder, PreviewProperties properties) {
        return itemBuilder instanceof ItemBuilderTemplate;
    }

    @Override
    public boolean needsPreviewMouseListener(PreviewMouseListener pl) {
        return pl instanceof MouseListenerTemplate;
    }

    public CanvasSize getCanvasSize(Item item, PreviewProperties properties) {
        return new CanvasSize();
    }
}
