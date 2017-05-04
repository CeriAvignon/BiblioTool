package graphical_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignPanel extends JPanel {
	//constructor to be change by Sign Form
	public SignPanel(){
		JButton SignButton = new JButton("sign up");
		SignButton.setBounds(750, 250, 40, 10);
		SignButton.addActionListener(new SignProfile());

		this.add(new JLabel("Sign up form is under construction"));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(SignButton);
	}
	
	class SignProfile implements ActionListener {
		public void actionPerformed(ActionEvent event){
			JFrame frame = (JFrame) SignPanel.this.getTopLevelAncestor(); //Tu fais reference a la fenetre utilisée
            frame.getContentPane().removeAll(); // tu nettoie tous ses composants

            frame.getContentPane().add(new ProfilePanel()); // TU remet le container approprié
             
            // Tu valide les modifications et tu demande a la fenetre de se redessiner
            frame.repaint();
            frame.validate();
            frame.setVisible(true);
	       } 
	}
}
