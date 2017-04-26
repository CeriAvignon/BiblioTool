package graphe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.preview.PDFExporter;
import org.gephi.io.exporter.spi.CharacterExporter;
import org.gephi.io.exporter.spi.Exporter;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import com.itextpdf.text.PageSize;

public class exportGraph {

	public static void main(String[] args) {
		MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.listeReference());
		exportGraph();

	}
	 public static void exportGraph(){
			 //Export full graph gexf
		 ExportController ec = Lookup.getDefault().lookup(ExportController.class);
		 try {
		     ec.exportFile(new File("graphe.gexf"));
		     System.out.println("graphe a bien été exporté");
		 } catch (IOException ex) {
		     ex.printStackTrace();
		     return;
		 }
		 
		}
	  
	  public static void exportGraph(String extension){
			 //Export full graph gexf
		  if(extension=="gexf"){
			  ExportController ec = Lookup.getDefault().lookup(ExportController.class);
			   try {
			       ec.exportFile(new File("graphe.gexf"));
			   } catch (IOException ex) {
			       ex.printStackTrace();
			       return;
			   }
			  
		  }
		  else{
			  ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
			   Workspace workspace = pc.getCurrentWorkspace();
			   ExportController ec = Lookup.getDefault().lookup(ExportController.class);
			   //Export GML
		        Exporter exporterGraphML = ec.getExporter("graphml");     //Get GraphML exporter
		        exporterGraphML.setWorkspace(workspace);
		        StringWriter stringWriter = new StringWriter();
		        ec.exportWriter(stringWriter, (CharacterExporter) exporterGraphML);

		        FileWriter fw = null;
		        try {
		            fw = new FileWriter("graphe.graphml");
		            fw.write(stringWriter.toString());
		            fw.close();
		            System.out.println("done");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			  
		  }
			  
		   }

}
