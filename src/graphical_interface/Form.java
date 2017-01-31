package graphical_interface;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;

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
public class Form extends JPanel{
	private JTextField textField_keyword;
	private JTextField textField_author;
	private JTextField textField_year;
	private JTextField textField_title;
	private String Keyword;
	private String Author;
	private String Year;
	private String Title;


	public Form(){
		setBounds(0,0,950,700);
		setVisible(true);
		setLayout(null);
		
		
		JButton btnSubmitResearch1 = new JButton("Submit");
		btnSubmitResearch1.setBounds(487, 389, 117, 29);
		add(btnSubmitResearch1);
		
		
		JButton btnCancel = new JButton("Annuler");
		btnCancel.setBounds(358, 389, 117, 29);
		add(btnCancel);
		
		textField_keyword = new JTextField();
		textField_keyword.setBounds(454, 145, 130, 26);
		add(textField_keyword);
		textField_keyword.setColumns(10);

		
		textField_title = new JTextField();
		textField_title.setColumns(10);
		textField_title.setBounds(454, 195, 130, 26);
		add(textField_title);
		
		textField_author = new JTextField();
		textField_author.setColumns(10);
		textField_author.setBounds(454, 254, 130, 26);
		add(textField_author);
		
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
		
		textField_year = new JTextField();
		textField_year.setColumns(10);
		textField_year.setBounds(454, 95, 130, 26);
		add(textField_year);
		
		JCheckBox chckbx_google = new JCheckBox("Google scolar");
		chckbx_google.setBounds(287, 315, 128, 23);
		add(chckbx_google);
		
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
		btnSubmitResearch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Keyword=textField_keyword.getText();
				Title=textField_keyword.getText();
				Author=textField_keyword.getText();
				Year=textField_keyword.getText();
				
				if(chckbx_google.isSelected()){
					//call function research google
				}
				if(checkDBLP.isSelected()){
					//call function research dblp
				}
				if(checkCiteSeerX.isSelected()){
					//call function research citeseerx
				}
				//close panel
			}
		});
		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

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