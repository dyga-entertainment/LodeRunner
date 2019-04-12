package controleur;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import IHM.Buttons.ContextTransitionButton;
import IHM.FenetrePrincipale;
import IHM.Views.*;
import audio.SoundSystem;

public class VueManager {

    public enum ViewType { None, HomeMenu, Credits, Profils, Settings, WorldSelection, LevelSelection }

    private JFrame windowFrame;

    private static View[] views;
    private static ViewType currentView;
    private static ViewType lastVisitedView;

	public VueManager(FenetrePrincipale fenetre) {
        this.windowFrame = fenetre;

        // Create and store all the possible views in the array
        this.views = new View[] {
                null,
                new HomeMenuView(this),
                new CreditView(this),
                new ProfilsView(this),
                new SettingsView(this),
                new WorldsView(this),
                new LevelsView(this),
        };
        this.currentView = ViewType.None;

        ChangeView(ViewType.HomeMenu);

        // Basic layout


        // Not right now.
        //launchJukebox();

        // Useful later
        //loadProfil();

        //pistes.retirerPiste(numeroPisteAmbiance)

        // AFTER
        //ModeleJeu modele = new ModeleJeu();
        //modele.chargerNiveau(0, 0);

        //this.IHMCourante = new TestVue(modele);
        //this.setLayout(new BorderLayout());
        //this.add(this.IHMCourante, BorderLayout.CENTER);
        //modele.addObserver(this.IHMCourante);
        //ControleurJeu c = new ControleurJeu(this.IHMCourante, modele);
        //fenetre.setControleur(c);
        //jouerJeu(this.IHMCourante);

        //this.removeAll();
        //validate();
        //repaint();
        //this.setOpaque(false);

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
        ChangeView(ViewType.values()[button.getNextView()]);
    }

	public void ChangeView(ViewType newView) {
        System.out.println("[Context Changement] from " + currentView.toString() + " to " + newView.toString());

        this.lastVisitedView = currentView;
        this.currentView = newView;

        // Clean up everything
        this.windowFrame.getContentPane().removeAll();

        // Prepare the next view to be printed
        View nextView = getNextView();

        nextView.setup(FenetrePrincipale.WINDOW_WIDTH, FenetrePrincipale.WINDOW_HEIGHT, FenetrePrincipale.CONTENT_PANEL_WIDTH,
                FenetrePrincipale.CONTENT_PANEL_HEIGHT);

        // Could make a transition here ?

        // Finally, Add the VueManager to the windows
        //this.windowFrame.setContentPane(new Container());

        this.windowFrame.getContentPane().add(nextView);
        this.windowFrame.repaint();
        this.windowFrame.pack();
    }

    public void ReturnLastView() {
        ChangeView(this.lastVisitedView);
    }

    public View getNextView() {
        return this.views[currentView.ordinal()];
    }

    /*
	public void jouerJeu(JPanel vue) {
		this.repaint();
		//this.removeAll();  Useful ?
		
		this.setLayout(new BorderLayout());
		this.setLayout(new GridLayout(1,1));
		this.add(vue);
	}
	*/

	public void majFichierProfils(levels.ProfilData p) {
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


    private levels.ProfilData profils;
    private ControleurMenus controleur;
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
        this.profils = new levels.ProfilData(ligneLue.split(" "));
    }
}
