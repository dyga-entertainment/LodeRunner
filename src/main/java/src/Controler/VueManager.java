package Controler;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Stack;
import javax.swing.*;
import View.Buttons.ContextTransitionButton;
import View.FenetrePrincipale;
import View.GameViews.GameView;
import View.MenuViews.LoadingView;
import View.MenuViews.*;
import Data.audio.SoundSystem;
import Model.jeu.ModeleJeu;

public class VueManager {

    public enum ViewType { None, HomeMenu, Credits, Profils, Settings, WorldSelection, LevelSelection, Loading }

    private JFrame windowFrame;

    private static View[] views;
    private static ViewType currentView;
    private static Stack<ViewType> lastVisitedViews;

	public VueManager(FenetrePrincipale fenetre) {
        this.windowFrame = fenetre;

        // Create and store all the possible views in the array
        this.views = new View[] {
            null,
            new HomeMenuView(this),
            new CreditView(this),
            new ProfilsView(this),
            new SettingsView(this),
            new WorldSelectionView(this),
            new LevelSelectionView(this),
            new LoadingView(this),
        };
        this.currentView = ViewType.None;
        this.lastVisitedViews = new Stack<>();

        ChangeView(ViewType.HomeMenu);

        // Basic layout


        // Not right now.
        //launchJukebox();

        // Useful later
        //loadProfil();

        //pistes.retirerPiste(numeroPisteAmbiance)
	}

    public ActionListener getContextTransitionActionListener() {
        // Create an uniform actionListener that will just change the context
        return actionEvent -> ChangeView((ContextTransitionButton) actionEvent.getSource());
    }

    public ActionListener getReturnContextActionListener() {
        // Create an uniform actionListener that will just change the context
        return actionEvent -> ReturnLastView();
    }

    public void ChangeView(ContextTransitionButton button) {
        ChangeView(ViewType.values()[button.getNextView().ordinal()]);
    }

    public void ChangeView(ViewType newView) {
        ChangeView(newView, false);
    }

	public void ChangeView(ViewType newView, boolean isBackButton) {
        System.out.println("[Context Changement] from " + currentView.toString() + " to " + newView.toString());

        // Retrive possible parameters from the current view.
        Dictionary<String, Object> parameters = new Hashtable<>();
        if(this.views[currentView.ordinal()] != null) {
            parameters = this.views[currentView.ordinal()].getParameters();
        }

        if (!isBackButton) {
            this.lastVisitedViews.push(currentView);
        }
        this.currentView = newView;

        // Clean up everything
        this.windowFrame.getContentPane().removeAll();

        // Prepare the next view to be printed
        View nextView = getNextView();


        // Give the parameters to the next view if needed.
        nextView.setParameters(parameters);

        // Setup the view to be display
        nextView.setup(FenetrePrincipale.WINDOW_WIDTH, FenetrePrincipale.WINDOW_HEIGHT, FenetrePrincipale.CONTENT_PANEL_WIDTH,
                FenetrePrincipale.CONTENT_PANEL_HEIGHT);

        //
        // Could make a transition here ?
        //

        // Finally, Add the VueManager to the windows
        this.windowFrame.getContentPane().add(nextView);
        this.windowFrame.repaint();
        this.windowFrame.pack();
    }

    public void ReturnLastView() {
        ChangeView(this.lastVisitedViews.pop(), true);
    }

    public View getNextView() {
        return this.views[currentView.ordinal()];
    }

    public JFrame getFrame() {
	    return this.windowFrame;
    }

    public void launchGame(ModeleJeu modele) {
        GameView gameVue = new GameView(modele);

        // Should call ChangeView here with the view created... ?

        modele.addObserver(gameVue);
        ControleurJeu c = new ControleurJeu(gameVue, modele);

        this.windowFrame.addKeyListener(c);
        this.windowFrame.requestFocus(); // Needed ?

        // Clean up everything
        this.windowFrame.getContentPane().removeAll();

        // Finally add the gameVue to the windows itself
        this.windowFrame.getContentPane().setLayout(new GridLayout(1,1));
        this.windowFrame.getContentPane().add(gameVue);
        this.windowFrame.repaint();
        this.windowFrame.pack();
    }

    ///////////////// OLD STUFFS /////////////////

	public void majFichierProfils(Data.levels.ProfilData p) {
		/** Re-ecriture dans le fichier apres modif */
		FileWriter fw2 = null;
		try {
			fw2 = new FileWriter("./ressources/gamedata/Profils.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}     // ecrase le fichier s'il existe deja
		BufferedWriter bw2 = new BufferedWriter(fw2);
		try {
			bw2.write(p.inv());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			bw2.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

    //private JPanel[] IHMaffichage;
    //private Bandeau[] IHMBouton;
    //private IHMMenu IHMCourante;


    private Data.levels.ProfilData profils;
    private int numIHMCourante;

    /** Surcharge de la fonction paint() pour afficher notre image */
    /*
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Repaint VueManager called !");
    	//g.drawImage(bg,0,0,getSize().width, getSize().height, this);
    	this.views[this.currentView.ordinal()].repaint();

    	//this.IHMBouton[this.numIHMCourante].repaint();
    }*/

    private void launchJukebox() {
        SoundSystem pistes = new SoundSystem();
        int numeroPisteAmbiance = pistes.ajouterPiste(-1, "/ressources/Musiques/sons_of_skyrim.wav");
    }

    private void loadProfil() {
        String ligneLue = "";
        BufferedReader fluxEntree=null;
        try {
            URL url = getClass().getResource("/ressources/gamedata/Profils.txt");
            // Creation du flux vers le fichier texte
            fluxEntree = new BufferedReader(new FileReader(url.getPath()));

            // Lecture d'une ligne
            ligneLue = fluxEntree.readLine();
        }
        catch(IOException exc){
            exc.printStackTrace();
        }
        finally{
            try{
                if(fluxEntree!=null){
                    // Fermeture du flux vers le fichier
                    fluxEntree.close();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        this.profils = new Data.levels.ProfilData(ligneLue.split(" "));
    }
}
