package Controler;

import View.GameViews.GameView;
import Utils.exceptions.BlocNonCreusableException;
import Utils.helper.ResourcesPaths;
import Model.jeu.ModeleJeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleurJeu implements KeyListener {

	private GameView vue;
	private ModeleJeu modele;
	private boolean actionCreuser;

	public ControleurJeu(GameView v, ModeleJeu m) {
		this.vue = v;
		this.modele = m;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			this.modele.setUp(false);
			this.modele.setLeft(true);
			this.modele.setRight(false);
			this.modele.setDown(false);
			break;
		case 38:
			this.modele.setUp(true);
			this.modele.setLeft(false);
			this.modele.setRight(false);
			this.modele.setDown(false);
			break;
		case 39:
			this.modele.setUp(false);
			this.modele.setLeft(false);
			this.modele.setRight(true);
			this.modele.setDown(false);
			break;
		case 40:
			this.modele.setUp(false);
			this.modele.setLeft(false);
			this.modele.setRight(false);
			this.modele.setDown(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			this.modele.setLeft(false);
			break;
		case 38:
			this.modele.setUp(false);
			break;
		case 39:
			this.modele.setRight(false);
			break;
		case 40:
			this.modele.setDown(false);
			break;
		}
		//System.out.println("arret");
		/* On recupere le heros et on le met a l'arret */
		// for (Personnage courant : this.modele.getListPerso()) {
		if (this.actionCreuser) {
			this.actionCreuser = false;
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(ControleurJeu.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (this.modele.getHeros().estVivant()) {
			this.modele.getHeros().setMouvement(false);
			if (this.modele.getHeros().isRegardeAGauche()) {
				this.modele.getHeros().setSprite(ResourcesPaths.ANIM_PLAYER_IDLE_PATH + "hero_fry_stop_left.png");
			} else {
				this.modele.getHeros().setSprite(ResourcesPaths.ANIM_PLAYER_IDLE_PATH + "hero_fry_stop_right.png");
			}
		}
		// this.vue.getPerso().getSprite().repaint();
		// this.vueJeu.getPerso().repaint();
		/* force la remise a niveau du sprite principal */
		// this.vueJeu.getPerso().getSprite().playSequence(Images.explosion,
		// false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		switch (arg0.getKeyChar()) {
		case 'c':
			/* Creuser un bloc du niveau */
			try {
				this.modele.creuser();
				this.actionCreuser = true;
			} catch (BlocNonCreusableException e) {
				System.out.println(e);
			}
			break;
		case 'r':
			/* Recommencer un niveau */
			this.modele.rebootNiveau();
			this.modele.chargerNiveau();
			break;
		case 'v':
			/* Ramasser un gadget */
			this.modele.ramasserGadget();
		case 'g':
		case 'p':
		case 'x':
			/* Utilser un gadget */
			this.modele.utiliserGadget(arg0.getKeyChar());
		default:
			//System.out.println("rien");
		}

	}

}
