package View;

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

		// Set the View of the game
		new ViewManager(this);

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