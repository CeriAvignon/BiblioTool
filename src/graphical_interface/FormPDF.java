package graphical_interface;

import graphe.*;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.gephi.graph.api.DirectedGraph;


public class FormPDF extends JPanel {
	String fileName;
	
	public FormPDF(){
		setBounds(0,0,950,700);
		setVisible(true);
		setLayout(null);
		
		JButton openFile = new JButton("Choisir un pdf...");
		
		
		openFile.setBounds(358, 300, 180, 29);
		add(openFile);
		

		
		TextField textField = new TextField();
		textField.setBounds(362, 339, 200, 22);
		textField.setVisible(true);
		add(textField);
		
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File file= fc.getSelectedFile();
				fileName = file.getName();
				textField.setText(fileName);
				System.out.println(fc.getSelectedFile().getAbsolutePath());
			}
		});
		JButton btnSubmitResearch = new JButton("Submit");
		btnSubmitResearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Article article = TextMining.processPdf(fileName);
				
				setVisible(false);
			}
		});
		btnSubmitResearch.setBounds(487, 389, 117, 29);
		add(btnSubmitResearch);
		
		
		JButton btnCancel = new JButton("Annuler");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(358, 389, 117, 29);
		add(btnCancel);
	}
}