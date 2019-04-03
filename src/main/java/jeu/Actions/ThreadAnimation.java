/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.Actions;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.AnimationNonReconnueException;
import jeu.Carte;
import jeu.Coordonnees;
import jeu.Positionnable;
import jeu.bloc.Bloc;
import jeu.bloc.BlocPortailFinNiveau;
import jeu.bloc.BlocTerre;
import jeu.item.PetiteBombe;
import ressources.Images;

/**
 *
 * @author jarvis
 */
public class ThreadAnimation extends Thread implements Observer {
    
    private boolean fini;
    private Carte matrice;
    private List<Queue<BufferedImage>> animations;
    private List<Positionnable> elements;
    //private Jukebox audioTracksAnimations;
    
    //private BlocTerre bloc;
    
    //private BufferedImage[] animation;
    
    public ThreadAnimation() {
        this.elements = new ArrayList<>();
        this.animations = new ArrayList<>();
        fini = false;
        //this.audioTracksAnimations = new Jukebox();
    }
    
    public void setMatrice(Carte c) {
        this.matrice = c;
    }
    
    public void ajouterPositionnableAnime(Positionnable p, BufferedImage[] a) {
        this.elements.add(p);
        this.animations.add(new LinkedList(Arrays.asList(a)));
    } 

    public void finirAnimation() {
        this.fini = true;
    }

    public void activerAnimation() {
        BufferedImage img;
        /* Pour toutes les animations a faire dans un instant t */
        for(int i = 0; i < animations.size() ; i++) {
            /* Cas ou l'animation est creuser un bloc */
            Positionnable p = elements.get(i);
            /* On recupere la k image ou null */
            /* Si ce n'est pas la fin de l'animation */
            if((img = this.animations.get(i).poll()) != null) {
                /* On joue une frame de l'animation */
                p.getSprite().modifierImage(img);
//                	System.out.println(p.getClass().toString());
                /* On fait une pause qui depent de son type */
                switch(p.getClass().toString()) {
                    case "class jeu.bloc.BlocTerre":
                        try {
                            Thread.sleep(200 - animations.size()*5);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "class jeu.item.PetiteBombe":
                        /* Si la bombe est actif (animation meche) */
                        if(((PetiteBombe) p).isActif()) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        /* sinon explosion */
                        } else {
                            try {
                                Thread.sleep(80);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            this.exploserEnvironnement(p, i);
                        }
                        break;
                    case "class jeu.item.Gaz":
                        try {
//                                System.out.println("Pause");
                            Thread.sleep(300 - animations.size()*40);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "class jeu.personnage.Heros":
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "class jeu.bloc.BlocPortailFinNiveau":
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    default:
                        System.out.println("Animation non controlée");
                        break;
                }
            /* Si on est arrivé à la fin on lance un timer */
            /* ce timer depent de l'objet */
            } else {
                try {
                    Timer t = null;	/* Timer */
                    switch(p.getClass().toString()) {
                        /* A besoin de faire transition destruire => reconstruire */
                        case "class jeu.bloc.BlocTerre":
                            /* On fourni le temps et le bloc pour savoir le reconstruire */
                            t = new Timer(8000, p);
                            break;
                        /* A besoin de faire transition meche => explosion */
                        case "class jeu.item.PetiteBombe":
                            if(((PetiteBombe)p).isActif()) {
                                t = new Timer(500, p);
                            } else {
                                t = new Timer(50, p);
                            }
                            break;
                        /* Cas des classes qui n'ont pas besoin de notifier */
                        case "class jeu.item.Gaz":
                        case "class jeu.personnage.Heros":
                        case "class jeu.bloc.BlocPortailFinNiveau":
                            /* pas necessaire */
                            t = new Timer(1, p);
                            break;
                        default :
                            throw new AnimationNonReconnueException("Animation non gérée");
                    }
                    t.addObserver(this);
                    new Thread(t).start();
                    this.elements.remove(p);
                    this.animations.remove(i);
                } catch (AnimationNonReconnueException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        /* Petite Pause */
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bloc.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(this.animations.size());
//        System.out.println("THE END");
        //this.animations = null;
        //this.elements = null;
        /*
        for(Queue<BufferedImage> q : this.animations) {
            q.removeAll(q);
        }*/
    }
    
    private void exploserEnvironnement(Positionnable p, int indiceCourant) {
    	/* Si c'est la premiere image on fait exploser les blocs */
        if(this.animations.get(indiceCourant).size() == Images.explosion.length-1) {
            PetiteBombe bombe = (PetiteBombe) p;
            /* destruction des blocs environnents */
            for(Coordonnees c : bombe.getBlocsExploses()) {
                try {
                	if(this.matrice.getBloc(c) instanceof BlocTerre) {
                		this.matrice.getBloc(c).destruction();
                	}
                } catch(NullPointerException | ArrayIndexOutOfBoundsException e) {}
            }
        }
	}

	/**
     * On reçoit l'objet a mettre a jour pour finir son animation
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
//        System.out.println("UPDATE");
        Positionnable p = (Positionnable) arg;
        if(p instanceof BlocTerre) {
            /* On reconstruit le bloc de terre */
            BlocTerre blocT = (BlocTerre) arg;
            blocT.reconstruction();
//            System.out.println("Fin");
            blocT.setConcret(true);
        } else if(p instanceof PetiteBombe) {
//            System.out.println("Bombe");
            PetiteBombe bombe = (PetiteBombe) p;
            /* Dans le cas ou on arrive au bout de l'animation meche */
            if(bombe.isActif()) {
                /* On passe a l'explosion */
                Coordonnees c = new Coordonnees(bombe.getPixels().getX()-64, bombe.getPixels().getY() - 64);
                bombe.setActif(false);
                bombe.getSprite().modifierImage("/ressources/Images/explosion1.png");
                //audioTracksAnimations.ajouterPiste(0, "/ressources/Musiques/explosion.wav");
                bombe.getSprite().setPosition(c.getX(), c.getY());
                
                this.ajouterPositionnableAnime(p, Images.explosion);
            }
            /* Sinon */
        } else if(p instanceof BlocPortailFinNiveau) {
        	/* On relance l'animation */
        	this.ajouterPositionnableAnime(p, Images.gate);
        }else {
        	/* On ne fait rien */
        }
    }
    
}
