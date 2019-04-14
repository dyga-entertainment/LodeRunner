package IHM;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controleur.ControleurJeu;
import controleur.VueManager;

public class FenetrePrincipale extends JFrame {

	public static final int WINDOW_WIDTH = 950;
	public static final int WINDOW_HEIGHT = 800;

	public static final int CONTENT_PANEL_WIDTH = 800;
	public static final int CONTENT_PANEL_HEIGHT = 800;

	public FenetrePrincipale() {
		setupWindow();

		// Set the View of the game
		new VueManager(this);

		launchWindow();
	}

	public void setupWindow() {
		this.setTitle("Lode Runner");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void launchWindow() {
		//this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				requestFocus();
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// hummmmm....
	public void setControleur(ControleurJeu c) {
		this.addKeyListener(c);
		requestFocus();
		this.setSize(960, 730);
		this.repaint();
	}
}