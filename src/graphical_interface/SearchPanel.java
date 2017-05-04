package graphical_interface;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SearchPanel extends JPanel {
	//
	public SearchPanel(){
		JFrame frame = (JFrame) this.getTopLevelAncestor();
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new FormMetadata());
		
		frame.repaint();
        frame.validate();
        frame.setVisible(true);
	}
}
