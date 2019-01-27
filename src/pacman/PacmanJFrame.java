package pacman;

import javax.swing.JFrame;

public class PacmanJFrame {
	private JFrame jf = new JFrame();
	private PacmanEngine pacman = new PacmanEngine();
	
	public PacmanJFrame(){
	jf.setTitle("PACMAN");
	jf.add(pacman);
	jf.setVisible(true);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jf.setSize(820, 840);
	jf.setResizable(false);
	}
}

