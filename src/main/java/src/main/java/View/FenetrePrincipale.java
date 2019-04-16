package View;

import Controler.ControleurJeu;
import Model.MainModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame {

	public static final int WINDOW_WIDTH = 950;
	public static final int WINDOW_HEIGHT = 800;

	public static final int CONTENT_PANEL_WIDTH = 800;
	public static final int CONTENT_PANEL_HEIGHT = 800;

	public FenetrePrincipale() {
		setupWindow();

		// Init the modele of the game
		MainModel modele = new MainModel();

		// Init the controller of the game
		// Init the link Controller -> Model in order to update the model if an input has been made.
		ControleurJeu gameControler = new ControleurJeu(modele);

		// Add the link View -> Controller when the user use inputs.
		this.addKeyListener(gameControler);
		this.addMouseListener(gameControler);
		this.requestFocus(); // Needed ?

		// Init the view of the game with the windows (??)
		// and the controller to be able to add him on buttons for example
		ViewManager gameVue = new ViewManager(this, modele, gameControler);

		// Add the link Controler -> View to notify the view an input as been made and that, maybe the model changed.
		gameControler.addView(gameVue);

		// useless ??
		//modele.addObserver(gameVue);

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
}