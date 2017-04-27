package graphical_interface;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import org.gephi.graph.api.DirectedGraph;

import javax.swing.*;
import java.awt.FlowLayout;
import java.nio.file.*;


/**
 * This is a form class which extends the class JPanel,
 * and it will be insert in the main window.
 * This class will communicate with the functionality of
 * the group Research.
 * @author Yang shuai
 *
 */
public class FormMetadata extends JPanel{
	private JTextField textFieldKeyword;
	private JTextField textFieldAuthor;
	private JTextField textFieldYear;
	private JTextField textFieldTitle;
	private String Keyword;
	private String Author;
	private String Year;
	private String Title;


	public FormMetadata(){
		setBounds(0,0,950,700);
		setVisible(true);
		setLayout(null);
		setBackground(Color.red);
		
		
		JButton btnSubmitResearch = new JButton("Submit");
		btnSubmitResearch.setBounds(487, 389, 117, 29);
		add(btnSubmitResearch);
		
		
		JButton btnCancel = new JButton("Annuler");
		btnCancel.setBounds(358, 389, 117, 29);
		add(btnCancel);
		
		textFieldKeyword = new JTextField();
		textFieldKeyword.setBounds(454, 145, 130, 26);
		add(textFieldKeyword);
		textFieldKeyword.setColumns(10);

		
		textFieldTitle = new JTextField();
		textFieldTitle.setColumns(10);
		textFieldTitle.setBounds(454, 195, 130, 26);
		add(textFieldTitle);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setColumns(10);
		textFieldAuthor.setBounds(454, 254, 130, 26);
		add(textFieldAuthor);
		
		JLabel lblTitle = new JLabel("titre");
		lblTitle.setBounds(358, 150, 61, 16);
		add(lblTitle);
		
		JLabel lblAuthor = new JLabel("auteur");
		lblAuthor.setBounds(358, 200, 61, 16);
		add(lblAuthor);
		
		JLabel lblYear = new JLabel("année");
		lblYear.setBounds(358, 259, 61, 16);
		add(lblYear);
		
		JLabel lblKeyword = new JLabel("mot-clé");
		lblKeyword.setBounds(358, 100, 61, 16);
		add(lblKeyword);
		
		textFieldYear = new JTextField();
		textFieldYear.setColumns(10);
		textFieldYear.setBounds(454, 95, 130, 26);
		add(textFieldYear);
		
		JCheckBox chckbx_IEEE = new JCheckBox("IEEE");
		chckbx_IEEE.setBounds(287, 315, 128, 23);
		add(chckbx_IEEE);
		
		JCheckBox checkDBLP = new JCheckBox("DBLP");
		checkDBLP.setBounds(472, 315, 99, 23);
		add(checkDBLP);
		
		JCheckBox checkCiteSeerX = new JCheckBox("CiteSeerX");
		checkCiteSeerX.setBounds(610, 315, 128, 23);
		add(checkCiteSeerX);
		
		/*
		 * This is the action of the button submit
		 * which sends the input of the form then 
		 * it will call the research methods from the group Research
		 * */
		btnSubmitResearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Keyword=textFieldKeyword.getText();
				Title=textFieldTitle.getText();
				Author=textFieldAuthor.getText();
				Year=textFieldYear.getText();
				
				if(chckbx_IEEE.isSelected()){
					//call function research google
				}
				if(checkDBLP.isSelected()){
					//call function research dblp
				}
				if(checkCiteSeerX.isSelected()){
					//call function research citeseerx
				}
				textFieldKeyword.setText("");
				textFieldTitle.setText("");
				textFieldAuthor.setText("");
				textFieldYear.setText("");
				setVisible(false);
				//close panel
			}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}
	
	public DirectedGraph getGraph(){
    	MyGraph.setArticles(MyGraph.retournerListeArticles());
		MyGraph.setReferences(MyGraph.ListeReference());
		DirectedGraph dg = MyGraph.createDirectedGraph();
		return dg;
	}
	
	// getters
	public String getKeyword(){
		return this.Keyword; 
	}
	public String getAuthor(){
		return this.Author; 
	}
	public String getYear(){
		return this.Year; 
	}
	public String getTitle(){
		return this.Title; 
	}
}