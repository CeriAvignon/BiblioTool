package graphical_interface;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.gephi.graph.api.Node;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.preview.ExporterBuilderPDF;
import org.gephi.io.exporter.preview.PDFExporter;
import org.gephi.io.exporter.spi.GraphExporter;
import org.gephi.io.exporter.spi.GraphFileExporterBuilder;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.graph.DegreeRangeBuilder.DegreeRangeFilter;
import org.gephi.filters.spi.NodeFilter;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.preview.api.G2DTarget;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewMouseEvent;
import org.gephi.preview.api.PreviewProperties;
import org.gephi.preview.api.Vector;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import com.itextpdf.text.PageSize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author mbastian
 */

public class PreviewSketch extends JPanel implements MouseListener, MouseWheelListener, MouseMotionListener {

    private static final int WHEEL_TIMER = 500;
    //Data
    private final PreviewController previewController;
    private final G2DTarget target;
    //Geometry
    private final Vector ref = new Vector();
    private final Vector lastMove = new Vector();
    //Utils
    private final RefreshLoop refreshLoop = new RefreshLoop();
    private Timer wheelTimer;
    private boolean inited;
    private final boolean isRetina;
    private Map<Node,ArrayList<Edge>> hideNodeEdge = new HashMap<Node,ArrayList<Edge>>();
    private Node nodeHideSave = null;
    private Query saveQuery = null;
    
    
    public PreviewSketch(G2DTarget target) {
        this.target = target;
        previewController = Lookup.getDefault().lookup(PreviewController.class);
        isRetina = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!inited) {
            //Listeners
            addMouseListener(this);
            addMouseMotionListener(this);
            addMouseWheelListener(this);
            inited = true;
        }

        int width = (int) (getWidth() * (isRetina ? 2.0 : 1.0));
        int height = (int) (getHeight() * (isRetina ? 2.0 : 1.0));

        if (target.getWidth() != width || target.getHeight() != height) {
            target.resize(width, height);
        }

        g.drawImage(target.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public void setMoving(boolean moving) {
        target.setMoving(moving);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PreviewMouseEvent tmpPME=buildPreviewMouseEvent(e, PreviewMouseEvent.Type.CLICKED);
        MouseListenerTemplate test=new MouseListenerTemplate();
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        Workspace workspace = pc.getCurrentWorkspace();
        for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes()) {
        	if(test.clickingInNode(node,tmpPME)){
	        	if(SwingUtilities.isRightMouseButton(e)) // check right click 
	        	{
        			// if we click on a noeud then it show the popup menu
		    		JPopupMenu popup = new JPopupMenu();
		    		JMenuItem mntmHide = new JMenuItem("Masquer ce noeud");
		    		popup.add(mntmHide);
		            popup.show(e.getComponent(), e.getX(), e.getY());
		    		mntmHide.addActionListener( new ActionListener()
		    		{
		    		    public void actionPerformed(ActionEvent e)
		    		    {
		    		    	hideNode(node);
		    	        	tmpPME.setConsumed(true);
		    	        	return;
		    		    }
		    		});

		        	System.out.println("on the node " + node.getLabel());
	        	}
        	} 
        	else 
        	{
        		if(SwingUtilities.isRightMouseButton(e)) // check right click 
        		{
    	    		JPopupMenu popup = new JPopupMenu();
    	    		JMenu mntmSave = new JMenu("Sauvegarder sous...");
		    		JMenuItem mntmSavePdf = new JMenuItem("PDF");
		    		JMenu mnSaveGexf = new JMenu("GEXF");
		    		JMenuItem mntmFullGraphe = new JMenuItem("Le graphe entier");
		    		JMenuItem mntmFilteredGraphe = new JMenuItem("Le graphe filtré");
		    		mnSaveGexf.add(mntmFullGraphe);
		    		mnSaveGexf.add(mntmFilteredGraphe);
		    		mntmSave.add(mntmSavePdf);
		    		mntmSave.add(mnSaveGexf);
    	    		popup.add(mntmSave);
    	            popup.show(e.getComponent(), e.getX(), e.getY());
    	            mntmSavePdf.addActionListener( new ActionListener()
    	    		{
    	    		    public void actionPerformed(ActionEvent e)
    	    		    {
    	    		    	savePdf();
    	    	        	tmpPME.setConsumed(true);
    	    	        	return;
    	    		    }
    	    		});
    	            mntmFilteredGraphe.addActionListener( new ActionListener()
    	    		{
    	    		    public void actionPerformed(ActionEvent e)
    	    		    {
    	    		    	saveFilteredGexf();
    	    	        	tmpPME.setConsumed(true);
    	    	        	return;
    	    		    }
    	    		});
    	            mntmFullGraphe.addActionListener( new ActionListener()
    	    		{
    	    		    public void actionPerformed(ActionEvent e)
    	    		    {
    	    		    	saveFullGexf();
    	    	        	tmpPME.setConsumed(true);
    	    	        	return;
    	    		    }
    	    		});
    	            
            	}
        	}
        }

        if (previewController.sendMouseEvent(tmpPME)) {
            refreshLoop.refreshSketch();
        }
    }
    /*
     * Export the graph into a file pdf
     * */
    public void savePdf()
    {
    	JFileChooser fs = new JFileChooser();
    	fs.setDialogTitle("Sauvegarde en PDF");
    	fs.setFileFilter(new FileTypeFilter(".pdf","PDF"));
     	int result = fs.showSaveDialog(null);
    	if(result == JFileChooser.APPROVE_OPTION){
    		File file=fs.getSelectedFile();
    		String filePath = file.getPath()+".pdf";
    		ExportController ec = Lookup.getDefault().lookup(ExportController.class);
    		//export
    		try {
    		   ec.exportFile(new File(filePath));
    		} catch (IOException ex) {
    		   ex.printStackTrace();
    		   return;
    		}
    		System.out.println(file.getPath());
    	}
    }
    /*
     * Export the filtered graph into a file Gexf
     * */
    public void saveFilteredGexf()
    {
    	JFileChooser fs = new JFileChooser();
    	fs.setDialogTitle("Sauvegarde en Gexf");
    	fs.setFileFilter(new FileTypeFilter(".gexf","GEXF"));
     	int result = fs.showSaveDialog(null);
    	if(result == JFileChooser.APPROVE_OPTION){
    		File file=fs.getSelectedFile();
    		String filePath = file.getPath()+".gexf";
    		ExportController ec = Lookup.getDefault().lookup(ExportController.class);
    		////Only exports the visible (filtered) graph
    		GraphExporter exporter = (GraphExporter) ec.getExporter("gexf");     //Get GEXF exporter
    		exporter.setExportVisible(true);  //Only exports the visible (filtered) graph
            ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
    		Workspace workspace = pc.getCurrentWorkspace();
    		exporter.setWorkspace(workspace);
    		try {
    		    ec.exportFile(new File(filePath), exporter);
    		} catch (IOException ex) {
    		    ex.printStackTrace();
    		    return;
    		}
    		System.out.println(file.getPath());
    	}
    }
    /*
     * Export the full graph into a file Gexf
     * */
    public void saveFullGexf()
    {
    	JFileChooser fs = new JFileChooser();
    	fs.setDialogTitle("Sauvegarde en Gexf");
    	fs.setFileFilter(new FileTypeFilter(".gexf","GEXF"));
     	int result = fs.showSaveDialog(null);
    	if(result == JFileChooser.APPROVE_OPTION){
    		File file=fs.getSelectedFile();
    		String filePath = file.getPath()+".gexf";
    		// export full graph
    		ExportController ec = Lookup.getDefault().lookup(ExportController.class);
    		try {
    		    ec.exportFile(new File(filePath));
    		} catch (IOException ex) {
    		    ex.printStackTrace();
    		    return;
    		}
    		System.out.println(file.getPath());
    	}
    }
    @Override
    public void mousePressed(MouseEvent e) {
    	
        previewController.sendMouseEvent(buildPreviewMouseEvent(e, PreviewMouseEvent.Type.PRESSED));
        ref.set(e.getX(), e.getY());
        lastMove.set(target.getTranslate());

        refreshLoop.refreshSketch();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PreviewMouseEvent tmpPME=buildPreviewMouseEvent(e, PreviewMouseEvent.Type.RELEASED);
        if (!previewController.sendMouseEvent(tmpPME)) {
            setMoving(false);
        }

        refreshLoop.refreshSketch();
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getUnitsToScroll() == 0) {
            return;
        }
        float way = -e.getUnitsToScroll() / Math.abs(e.getUnitsToScroll());
        target.setScaling(target.getScaling() * (way > 0 ? 2f : 0.5f));
        setMoving(true);
        if (wheelTimer != null) {
            wheelTimer.cancel();
            wheelTimer = null;
        }
        wheelTimer = new Timer();
        wheelTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                setMoving(false);
                refreshLoop.refreshSketch();
                wheelTimer = null;
            }
        }, WHEEL_TIMER);
        refreshLoop.refreshSketch();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!previewController.sendMouseEvent(buildPreviewMouseEvent(e, PreviewMouseEvent.Type.DRAGGED))) {
            setMoving(true);
            Vector trans = target.getTranslate();
            trans.set(e.getX(), e.getY());
            trans.sub(ref);
            trans.mult(isRetina ? 2f : 1f);
            trans.div(target.getScaling()); // ensure const. moving speed whatever the zoom is
            trans.add(lastMove);

            refreshLoop.refreshSketch();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void zoomPlus() {
        target.setScaling(target.getScaling() * 2f);
        refreshLoop.refreshSketch();
    }

    public void zoomMinus() {
        target.setScaling(target.getScaling() / 2f);
        refreshLoop.refreshSketch();
    }

    public void resetZoom() {
        target.reset();
        refreshLoop.refreshSketch();
    }

    private Vector screenPositionToModelPosition(Vector screenPos) {
        Vector center = new Vector(getWidth() / 2f, getHeight() / 2f);
        Vector scaledCenter = Vector.mult(center, target.getScaling());
        Vector scaledTrans = Vector.sub(center, scaledCenter);

        Vector modelPos = new Vector(screenPos.x, screenPos.y);
        modelPos.sub(scaledTrans);
        modelPos.div(target.getScaling());
        modelPos.sub(target.getTranslate());
        return modelPos;
    }

    private PreviewMouseEvent buildPreviewMouseEvent(MouseEvent evt, PreviewMouseEvent.Type type) {
        int mouseX = evt.getX();
        int mouseY = evt.getY();
        PreviewMouseEvent.Button button = PreviewMouseEvent.Button.LEFT;
        if (SwingUtilities.isMiddleMouseButton(evt)) {
            button = PreviewMouseEvent.Button.MIDDLE;
        } else if (SwingUtilities.isLeftMouseButton(evt)) {
            button = PreviewMouseEvent.Button.LEFT;
        } else if (SwingUtilities.isRightMouseButton(evt)) {
            button = PreviewMouseEvent.Button.RIGHT;
        }

        Vector pos = screenPositionToModelPosition(new Vector(mouseX, mouseY));

        return new PreviewMouseEvent((int) pos.x, (int) pos.y, type, button, null);
    }

    /**
     * @author Yang Shuai
     * This function allow to hide a node and those edges.
     * can be used for the function redisplayHideNode(Node node);
     */
    
    public void hideNode(Node node)
    {
    	try
    	{
    		// loading data
  	  	  ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
	      Workspace workspace = pc.getCurrentWorkspace();
          GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);
    	  DirectedGraph directedGraph = graphModel.getDirectedGraph();
    	  PreviewController previewController = Lookup.getDefault().lookup(PreviewController.class);
    	  
	      for (Node n : graphModel.getGraph().getNodes().toArray()) {   
	    	  if(n.getLabel().equals(node.getLabel())) // if its node is matched
	    	  {  
	    		  ArrayList<Edge> edgeList = new ArrayList<Edge>();
	    		  for (Edge edge : graphModel.getGraph().getEdges()) 
	    		  {   
	    			  if(edge.getSource().getId() == n.getId() || edge.getTarget().getId() ==n.getId() )
		   	    	  {
		    			  edgeList.add(edge);
		   	    	  }
	    		  }
	    		  this.hideNodeEdge.put(node, edgeList);
	    		  afficherList();
	    		  System.out.println("removing.");
	    		  Filtering filter=new Filtering();
	    		  saveQuery = filter.script(node,false, this.saveQuery);
	    		  nodeHideSave = node;
	    		  /*FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
	    	        DegreeRangeFilter degreeFilter = new DegreeRangeFilter();
	    	        degreeFilter.init(graphModel.getGraph());
	    	        degreeFilter.setRange(new Range(2, Integer.MAX_VALUE));    //Remove nodes with degree < 10
	    	        Query query = filterController.createQuery(degreeFilter);
	    	        GraphView view = filterController.filter(query);
	    	        graphModel.setVisibleView(view);    //Set the filter result as the visible view
	    	        */
	    		  //directedGraph.removeNode(n); // removing a nodes
	    		  previewController.refreshPreview(); 
	    		  refreshLoop.refreshSketch();
	    	  }
	      }
    	}catch(Exception ex){
    		System.out.println("Cannot find this node.");
    	}
	
    }
    
    // Debug of collection
    public void afficherList()
    {
        System.out.println("Parcours de l'objet HashMap : ");
        Set<Entry<Node, ArrayList<Edge>>> setHm = this.hideNodeEdge.entrySet();
        Iterator<Entry<Node, ArrayList<Edge>>> it = setHm.iterator();
        while(it.hasNext()){
        	Entry<Node, ArrayList<Edge>> e = it.next();
        	for(int i = 0;i<e.getValue().size();i++)
        	{
        		System.out.println(e.getKey().getLabel() + " : " + e.getValue().get(i).getSource().getLabel()+" -> "+e.getValue().get(i).getTarget().getLabel());
        	}
           
        }
    }
    
    private class RefreshLoop {

        private final long DELAY = 100;
        private final AtomicBoolean running = new AtomicBoolean();
        private final AtomicBoolean refresh = new AtomicBoolean();
        //Timer
        private long timeout = DELAY * 10;
        private Timer timer;

        public RefreshLoop() {
            super();
        }

        public void refreshSketch() {
            refresh.set(true);
            if (!running.getAndSet(true)) {
                startTimer();
            }
        }

        private void startTimer() {
            timer = new Timer("PreviewRefreshLoop", true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (refresh.getAndSet(false)) {
                        target.refresh();
                        repaint();
                    } else if (timeout == 0) {
                        timeout = DELAY * 10;
                        stopTimer();
                    } else {
                        timeout -= DELAY;
                    }
                }
            }, 0, DELAY);
        }

        private void stopTimer() {
            timer.cancel();
            running.set(false);
        }
    }
}