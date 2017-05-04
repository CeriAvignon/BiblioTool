package graphical_interface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogPanel extends JPanel {
	
	JButton LogInButton;
	
	//constructor to be change by Log Form
	public LogPanel(){
		LogInButton = new JButton("Log in");
		LogInButton.setBounds(750, 250, 40, 10);
		LogInButton.addActionListener(new LogToProfile());
		
		this.add(new JLabel("Log in form is under construction"));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(LogInButton);
	}
	
	class LogToProfile implements ActionListener {
		public void actionPerformed(ActionEvent event){
			JFrame frame = (JFrame) LogPanel.this.getTopLevelAncestor(); // Confirm modifications and asks to the window to be redraw
            frame.getContentPane().removeAll(); // Clean all components

            frame.getContentPane().add(new ProfilePanel()); // Puts back the appropriate container
             
            // Confirm the modifications and asks has the window to redraw
            frame.repaint();
            frame.validate();
            frame.setVisible(true);
	       } 
	}
}

	 