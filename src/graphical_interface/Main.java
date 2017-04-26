package graphical_interface;
import textmining.*;

public class Main {
 public static void main(String[] args) {
	//Window windowBibliotool = new Window();
	 
	 AnalyseReferences test = new AnalyseReferences("refAvecAccolade.pdf");
	 
	 test.getReferences();
 }
}
