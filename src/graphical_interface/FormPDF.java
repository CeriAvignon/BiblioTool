package graphical_interface;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import org.gephi.graph.api.DirectedGraph;

import java.awt.FlowLayout;
import java.nio.file.*;
import java.awt.TextField;


public class FormPDF extends JPanel {
	
	public FormPDF(){
		setBounds(0,0,950,700);
		setVisible(true);
		setLayout(null);
		
		JButton openFile = new JButton("Choisir un pdf...");
		
		
		openFile.setBounds(358, 300, 180, 29);
		add(openFile);
		
		JButton btnSubmitResearch = new JButton("Submit");
		btnSubmitResearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGraph();
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
		
		TextField textField = new TextField();
		textField.setBounds(362, 339, 200, 22);
		textField.setVisible(true);
		add(textField);
		
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File file= fc.getSelectedFile();
				String fileName = file.getName();
				textField.setText(fileName);
				System.out.println(fc.getSelectedFile().getAbsolutePath());
			}
		});
	}
	
	public DirectedGraph getGraph(){
    	MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.ListeReference());
		DirectedGraph dg = MyGraph.createDirectedGraph();
		return dg;
	}
}
