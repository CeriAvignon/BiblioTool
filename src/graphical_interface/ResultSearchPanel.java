package graphical_interface;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ResultSearchPanel extends JPanel {
	String doc = "GEXF_file/LesMiserables.gexf";
	
	public ResultSearchPanel(){
		//to be change by the result of the graph  by text mining or web mining
		/* displayGraph previewJFrame = new displayGraph();
		previewJFrame.script(doc);
		this.add(previewJFrame);*/
		
		display d = new display(); 
		d.loadMatrix();
		d.layoutGraph();
		d.changeColor();
		d.saveGexf();
		//d.displayGexf();
		this.setLayout(new BorderLayout());
		this.add(d.displayGexf(), BorderLayout.CENTER);
		this.revalidate();
		
		
		//previewJFrame.displayGexf( .);
		//this.add(previewJFrame);
		
		//appel du code de david
		Panel info_graph = new Panel();
		this.add(info_graph, BorderLayout.SOUTH);
		this.revalidate();
		this.setVisible(true);
	}
	
	/**
	 * @author udrey
	 * @param doc
	 */
	public ResultSearchPanel(String doc){
		//to be change by the result of the graph  by text mining or web mining
		displayGraph previewJFrame = new displayGraph();
		previewJFrame.script(doc);
		this.add(previewJFrame);
		
		//appel du code de david
		Panel info_graph = new Panel();
		this.add(info_graph);
		this.revalidate();
	}
	
}
