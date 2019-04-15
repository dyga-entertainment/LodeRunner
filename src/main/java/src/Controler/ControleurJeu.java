package Controler;

import Model.ViewType;
import Utils.exceptions.BlocNonCreusableException;
import Utils.helper.ResourcesPaths;
import Model.ModeleJeu;
import View.Buttons.ContextTransitionButton;
import View.ViewManager;

import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleurJeu implements KeyListener, MouseListener, ActionListener  {

	private ViewManager view;		// TODO: Should be a list of Observer !

	private ModeleJeu model;
	private boolean actionCreuser;

	private List<ActionListener> actionsListener = new LinkedList<>();

	public ControleurJeu(ModeleJeu m) {
		this.model = m;
	}

	public void addView(ViewManager view) {
		this.view = view;
	}

	public void notifyView() {
		// TODO
		//this.view.notifyChanges();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			this.model.setUp(false);
			this.model.setLeft(true);
			this.model.setRight(false);
			this.model.setDown(false);
			break;
		case 38:
			this.model.setUp(true);
			this.model.setLeft(false);
			this.model.setRight(false);
			this.model.setDown(false);
			break;
		case 39:
			this.model.setUp(false);
			this.model.setLeft(false);
			this.model.setRight(true);
			this.model.setDown(false);
			break;
		case 40:
			this.model.setUp(false);
			this.model.setLeft(false);
			this.model.setRight(false);
			this.model.setDown(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 37:
			this.model.setLeft(false);
			break;
		case 38:
			this.model.setUp(false);
			break;
		case 39:
			this.model.setRight(false);
			break;
		case 40:
			this.model.setDown(false);
			break;
		}
		//System.out.println("arret");
		/* On recupere le heros et on le met a l'arret */
		// for (Personnage courant : this.model.getListPerso()) {
		if (this.actionCreuser) {
			this.actionCreuser = false;
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(ControleurJeu.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (this.model.getHeros().estVivant()) {
			this.model.getHeros().setMouvement(false);
			if (this.model.getHeros().isRegardeAGauche()) {
				this.model.getHeros().setSprite(ResourcesPaths.ANIM_PLAYER_IDLE_PATH + "hero_fry_stop_left.png");
			} else {
				this.model.getHeros().setSprite(ResourcesPaths.ANIM_PLAYER_IDLE_PATH + "hero_fry_stop_right.png");
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
				this.model.creuser();
				this.actionCreuser = true;
			} catch (BlocNonCreusableException e) {
				System.out.println(e);
			}
			break;
		case 'r':
			/* Recommencer un niveau */
			this.model.rebootNiveau();
			this.model.chargerNiveau();
			break;
		case 'v':
			/* Ramasser un gadget */
			this.model.ramasserGadget();
		case 'g':
		case 'p':
		case 'x':
			/* Utilser un gadget */
			this.model.utiliserGadget(arg0.getKeyChar());
		default:
			//System.out.println("rien");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.toString());
		System.out.println("mouseClicked " + e.getButton() + ", " + e.getComponent() + " !");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed " + e.getButton() + " !");
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered " + e.toString() + " !");
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		test.get(((ContextTransitionButton)e.getSource()).getButtonName()).actionPerformed(e);
	}

	/**####################### Menu Function #######################*/

	private Dictionary<String, ActionListener> test = new Hashtable<>();

	public void addContextTransitionActionListener(ContextTransitionButton button) {
		// Add it in a dict
		test.put(button.getButtonName(), actionEvent -> ChangeView((ContextTransitionButton) actionEvent.getSource()));
	}

	public void addReturnContextActionListener(ContextTransitionButton button) {
		// Add it in a dict
		test.put(button.getButtonName(), actionEvent -> ReturnLastView());
	}

	public void ChangeView(ContextTransitionButton button) {
		ChangeView(ViewType.values()[button.getNextView().ordinal()]);
	}

	public void ChangeView(ViewType newView) {
		ChangeView(newView, false);
	}

	public void ChangeView(ViewType newView, boolean isBackButton) {
		System.out.println("[Context transition] NewView = " + newView);
		// Update the model
		this.model.ChangeView(newView, isBackButton);

		// Notify the view that it needs to update
		this.view.refreshView();
	}

	public void ReturnLastView() {
		System.out.println("[Context transition] Return to last view");
		// Update the model
		this.model.ReturnLastView();

		// Notify the view that it needs to update
		this.view.refreshView();
	}

	/**####################### End Menu Function #######################*/
}
