package graphical_interface;

import org.gephi.graph.api.Node;
import org.gephi.preview.api.Item;

/**
 * Basic item without properties but a node.
 */
public class LabelItem implements Item {

    Node node;

    public LabelItem(Node node) {
        this.node = node;
    }

    @Override
    public Object getSource() {
        return node;
    }

    @Override
    public String getType() {
        return "label.sometype";
    }

    @Override
    public <D> D getData(String key) {
        return null;
    }

    @Override
    public void setData(String key, Object value) {
    }

    @Override
    public String[] getKeys() {
        return new String[0];
    }

}