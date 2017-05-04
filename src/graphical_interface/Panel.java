package graphical_interface;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel {
	
	public Panel() {
	}	

	public void paintComponent(Graphics g) {
		displayInfoGraph(g);
	}
	
	public void displayInfoGraph(Graphics g) {
		String search = "Comment gerer un projet IL?"; 	//	this string are
		String number_nodes = "95";					   	// 	made for the
		String number_nodes_displayed = "21";			//	test
		String range = "02";
		int heigth_rect = this.getHeight()*46/100;
		int width_rect = this.getWidth()*22/100;
		int coord_x_rect = this.getWidth()-width_rect-this.getWidth()/100;
		int coord_y_rect = this.getHeight()/20;
		int coord_x_text = coord_x_rect+width_rect/20;
		int coord_y_text = coord_y_rect+heigth_rect/10;
		int police = width_rect/16;
		Font font = new Font("Courier", Font.BOLD, police);
		g.setFont(font);
		g.drawString("Information du Graphe", coord_x_text, coord_y_text);
		coord_y_text = coord_y_text+police*2;
		police = width_rect/20;
		font = new Font("Courier", Font.BOLD, police);
		g.setFont(font);
		g.drawString("Recherche effectuee : ", coord_x_text, coord_y_text);
		coord_y_text = coord_y_text+police;
		g.drawString("	- "+search, coord_x_text, coord_y_text);
		coord_y_text = coord_y_text+police*2;
		g.drawString("Nombre de noeud : "+number_nodes, coord_x_text, coord_y_text);
		coord_y_text = coord_y_text+police*2;
		g.drawString("Nombre de noeuds afficher : "+number_nodes_displayed, coord_x_text, coord_y_text);
		coord_y_text = coord_y_text+police*2;
		g.drawString("Distance d'affichage : "+range, coord_x_text, coord_y_text);
		g.drawRect(this.getWidth()-width_rect-this.getWidth()/100, this.getHeight()/20, width_rect, heigth_rect);
	}
}