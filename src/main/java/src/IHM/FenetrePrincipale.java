package IHM;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controleur.ControleurJeu;
import tests.TestModeleJeu;

public class FenetrePrincipale extends JFrame {

	public FenetrePrincipale() {
		setupWindows();
		// Set the View of the game
		VueMenus vue = new VueMenus(this);
		this.setContentPane(vue);
	}

	public void setupWindows() {
		this.setTitle("Lode Runner");
		this.setSize(980, 720);
		//this.setResizable(false);
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
		this.repaint();
	}
}