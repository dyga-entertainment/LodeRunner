package tests;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import IHM.TestVue;
import jeu.ModeleJeu;

public class TestModeleJeu {
	
	static JFrame f;

	public static void main(String[] args) {
		
		TestModeleJeu.f = new JFrame();
		
		//Jukebox pistes = new Jukebox();
		//pistes.ajouterPiste(-1, "/ressources/Musiques/spooky_scary_skeletons.wav");

		/* Creation du modele a partir du jeu.Niveau */
		ModeleJeu modele = new ModeleJeu();
		modele.chargerNiveau(0, 0);
		/* Creation de la vue */
		TestVue vue = new TestVue(modele);
		
		modele.addObserver(vue);
		/* Creation du controleur */
		//ControleurJeu c = new ControleurJeu(vue, modele);
		
		f.setContentPane(vue);
		//f.addKeyListener(c);
		f.setSize(960, 667);
		f.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				TestModeleJeu.f.requestFocus();
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
