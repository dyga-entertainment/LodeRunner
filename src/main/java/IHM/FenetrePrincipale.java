package IHM;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controleur.ControleurJeu;
import tests.TestModeleJeu;

public class FenetrePrincipale extends JFrame {

	public FenetrePrincipale() {
		this.setTitle("Lode Runner");
		VueMenus vue = new VueMenus(this);
		this.setContentPane(vue);
		this.setSize(960, 667);
		//this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				requestFocus();
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setControleur(ControleurJeu c) {
		this.addKeyListener(c);
		requestFocus();
		this.setSize(960, 730);
	}
}