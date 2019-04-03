package IHM;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.JPanel;

import audio.SoundSystem;
import controleur.ControleurJeu;
import controleur.ControleurMenus;
import jeu.ModeleJeu;

public class VueMenus extends JPanel  {
	
    private JPanel[] IHMaffichage;
    private Bandeau[] IHMBouton;
    private int longueur;
    private TestVue IHMCourante;
    private levels.ProfilData profils;
    private ControleurMenus controleur;
    private int numIHMCourante;

    /** Surcharge de la fonction paint() pour afficher notre image */ 
    public void paint(Graphics g) { 
    	//g.drawImage(bg,0,0,getSize().width, getSize().height, this); 
    	this.IHMCourante.repaint();
    	//this.IHMBouton[this.numIHMCourante].repaint();
    }

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
    
	public VueMenus(FenetrePrincipale fenetre) {
        //launchJukebox();

        //loadProfil();

        //this.longueur = 8;
        //this.IHMaffichage = new JPanel[this.longueur];
        //this.IHMBouton = new Bandeau[this.longueur];

        //pistes.retirerPiste(numeroPisteAmbiance)

        ModeleJeu modele = new ModeleJeu();
        modele.chargerNiveau(0, 0);

        this.IHMCourante = new TestVue(modele);
        this.setLayout(new BorderLayout());
        this.add(this.IHMCourante, BorderLayout.CENTER);
        modele.addObserver(this.IHMCourante);
        ControleurJeu c = new ControleurJeu(this.IHMCourante, modele);
        fenetre.setControleur(c);
        jouerJeu(this.IHMCourante);

        //this.removeAll();
        validate();
        repaint();
        this.setOpaque(false);

/*
        // COUPLER CHAQUE ACTIONS AVEC SA CLASSE.. VU QUE LES CLASSES EXISTENT DEJA jeu.bloc.C EST WORTH
            this.IHMaffichage = new JPanel[] {new IHMMenu(), new IHMCredit(), new IHMProfil(), new IHMOption(), new IHMMonde()};
            this.IHMBouton = new Bandeau[] {new IHMB2(), new IHMB3(), new IHMB4(), new IHMB5(), new IHMB6(), new IHMB7(), new IHMB8(), new IHMB9()};
            for(int i = 0 ; i < this.longueur; i++) {
                    //this.IHMaffichage[i].add(this.IHMBouton[i], BorderLayout.SOUTH);
                    for(int j = 0 ; j < this.IHMBouton[i].getNbButton(); j++) {
                            JPButton currentJButton = this.IHMBouton[i].getButton(j);
                            currentJButton.addActionListener(new ActionListener() {
                                    int panelNumber;
                                    public void actionPerformed(ActionEvent e) {

                                            IHMProfil ihm4 = (IHMProfil) IHMaffichage[3];
                                            IHM6 ihm6 = (IHM6) IHMaffichage[5];
                                            IHMB6 ihmb6 = (IHMB6) IHMBouton[5];
                                            IHMMonde ihm7 = (IHMMonde) IHMaffichage[6];

                                            switch(currentJButton.getTextO()) {
                                            case "Solo" :
                                                    if (profils.getNbJ() == 0) {
                                                        panelNumber = 3;
                                                    } else {
                                                        panelNumber = 5;
                                                    }
                                                    break;
                                            case "Coop" :
                                                    if (profils.getNbJ() == 0) {
                                                        panelNumber = 3;
                                                    } else {
                                                        panelNumber = 5;
                                                    }
                                                    break;
                                            case "Editeur" :
                                                    // Editeur
                                                    break;
                                            case "Options" :
                                                panelNumber = 4;
                                                    break;
                                            case "Credits" :
                                                panelNumber = 2;
                                                    break;
                                            case "Retour" :
                                                panelNumber = retourIHM();
                                                    break;
                                            case "Appliquer" :
                                                panelNumber = retourIHM();
                                                    break;
                                            case "Valider Profil" :
                                                    ihm7.setProfilCourant(profils.getJ(ihm6.getProfilChoisi()));
                                                    ihm7.majIHM(profils.getJ(ihm6.getProfilChoisi()));
                                                    ihmb6.majIHM(profils);
                                                panelNumber = 6;
                                                    break;
                                            case "Supprimer Profil" :
                                                    if ( profils.getNbJ() == 1){
                                                        panelNumber = 3;
                                                    } else {
                                                        panelNumber = 5;
                                                    }
                                                    profils.supprJ(ihm6.getProfilChoisi());
                                                    System.out.println("Profil numero "+(ihm6.getProfilChoisi()+1)+" suprime");
                                                    majFichierProfils(profils);
                                                    ihm6.majIHM(profils);
                                                    ihmb6.majIHM(profils);
                                                    break;
                                            case "Creer Profil" :
                                                panelNumber = 3;
                                                    break;
                                            case "Valider jeu.Monde" :
                                                panelNumber = 7;
                                                    break;
                                            case "Jouer" :
                                                    pistes.retirerPiste(numeroPisteAmbiance);
                                                    // Creation du modele a partir du jeu.Niveau

                                                    jeu.ModeleJeu modele = new jeu.ModeleJeu();
                                                    modele.chargerNiveau(0, 0);
                                                    // Creation de la vue

                                                    TestVue vue = new TestVue(modele);
                                                    modele.addObserver(vue);
                                                    ControleurJeu c = new ControleurJeu(vue, modele);
                                                    fenetre.setControleur(c);
                                                    jouerJeu(vue);						
                                                    break;
                                            case "Valider Creation" :
                                                    profils.addJ(new Joueur(ihm4.getNomChoisi(), ihm4.getImageChoisie()));
                                                    System.out.println("Nouveau profil cree");
                                                    majFichierProfils(profils);
                                                    ihm7.setProfilCourant(profils.getJ(profils.getNbJ()-1));
                                                    ihm7.majIHM(profils.getJ(profils.getNbJ()-1));
                                                    ihmb6.majIHM(profils);
                                                    ihm6.majIHM(profils);
                                                panelNumber = 6;
                                                    break;
                                            default :// case "Configuration des touches" :
                                                panelNumber = 8;
                                                    break;
                                            }
                                            if(currentJButton.getTextO() != "Jouer") {
                                                    modifierIHM(IHMaffichage[panelNumber]);

                                            }
                                            validate();
                                            repaint();	
                                    }
                            });
                    };

            }
            this.setLayout(new BorderLayout());
            //this.IHMBouton[0].repaint();
            this.IHMCourante = new IHMMenu();
            this.add(IHMCourante, BorderLayout.CENTER);
            */
            //this.add(new IHMB1(), BorderLayout.SOUTH);
            //this.repaint();
            //this.setOpaque(false);
	}
	
	public Bandeau recupererIHMB(int numero) {
		return this.IHMBouton[numero];
	}
	
	public JPanel recupererIHMA(int numero) {
		return this.IHMaffichage[numero];
	}
	
	public void jouerJeu(JPanel vue) {
		this.repaint();
		//this.removeAll();
		
		this.setLayout(new BorderLayout());
		this.setLayout(new GridLayout(1,1));
		this.add(vue);
	}
	
	public void modifierIHM(JPanel ihm) {
            this.removeAll();

            this.setLayout(new BorderLayout());
            this.add(ihm, BorderLayout.CENTER);
            //this.IHMCourante = ihm;
	}
	
	public int retourIHM() {
		int i;
		switch (this.numIHMCourante) {
            case 6 :
                if (profils.getNbJ() == 0) {
                    i = 3;
                } else {
                    i = 5;
                }
                break;
            case 7 :
                i = 6;
                break;
            case 9 :
                i = 4;
                break;
            case 8 :
                i = 4;
                break;
            default :
                i = 0;
                break;
		}
		return i;
	}
	
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
}
