package jeu;

import IHM.MenuViews.ImagePanel;
import jeu.bloc.Bloc;
import jeu.bloc.BlocTerre;
import jeu.bloc.C;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/** Classe jeu.Niveau
 * Contient toutes les informations necessaires a la
 * construction d'un niveau.
 * 
 * @author Yann Tireau, Axel Grau, Grégoire Boiron
 * @version 2.0
 *
 */
public class Niveau {

	/** liste des blocs du niveau */
	private Queue<Bloc> blocs;
	/** liste des items du niveau */
	private Queue<item.Item> items;
	/** liste des coordonnes des MadMonks */
	private Queue<jeu.Coordonnees> originesMadMonks;
	/** coordonnees du heros */
	private jeu.Coordonnees origineHeros;
	/** image de fond */
	private String theme;
	/** score a atteindre pour finir le niveau */
	private int scoreFinal;
	/** bloc de fin de niveau */
	private bloc.BlocPortailFinNiveau theEnd;
	/** chemin de fichier audio */
	private String cheminFichierAudioAmbiance;
	// private boolean estEditable;
	private String imageUrlPreview;

	public Niveau(boolean estEditable) {
		this.blocs = new LinkedList<Bloc>();
		this.items = new LinkedList<item.Item>();
		this.originesMadMonks = new LinkedList<jeu.Coordonnees>();
		this.scoreFinal = 0;
		/* On met une musique par defaut */
		this.cheminFichierAudioAmbiance = "/ressources/Musiques/never_gonna_give_you_up.wav";
		// this.Identifiant = 0;
		// this.estEditable = estEditable;
	}

	public void setImageUrl(String url) {
		this.imageUrlPreview = url;
	}

	public void setMusiqueAmbiance(String cheminNewAudio){
		this.cheminFichierAudioAmbiance = cheminNewAudio;
	}
	
	public String getMusiqueAmbiance(){
		return this.cheminFichierAudioAmbiance;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String fond) {
		this.theme = fond;
	}
	
	public int getScoreFinal() {
		return new Integer(this.scoreFinal);
	}
	
	public bloc.BlocPortailFinNiveau getTheEnd() {
		return this.theEnd;
	}

		public void remplirNiveau(C[][] pattern) {
		jeu.Coordonnees pos;
		Bloc bloc;
		item.Item item;
		Random rd = new Random();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 30; j++) {
                            pos = new jeu.Coordonnees(j, i);
                            /* Si c'est un bloc a ajouter */
                            if(pattern[i][j].toString().startsWith("b")) {
                                switch(pattern[i][j]) { 
                                    case bx: bloc = new bloc.BlocBrique(pos);
                                        break;
                                    case bt: bloc = new BlocTerre(pos, false);
                                        break;
                                    case be: bloc = new bloc.BlocEchelle(pos);
                                        break;
                                    case bg: bloc = new bloc.BlocGlu(pos);
                                        break;
                                    case bf: bloc = new bloc.BlocBarreTraversee(pos);
                                        break;
                                    default: bloc = null;
                                }
                                this.ajouterBloc(bloc);
                            /* Si c'est un item a ajouter */
                            } else if(pattern[i][j].toString().startsWith("i")) {
                                switch(pattern[i][j]) { 
                                    case ib: item = new item.PetiteBombe(pos);
                                        break;
                                    case iB: item = new item.GrosseBombe(pos);
                                        break;
                                    case iz: item = new item.Gaz(pos);
                                        break;
                                    case ip: item = new item.Pioche(pos);
                                        break;
                                    case ig: item = new item.PotDeGlu(pos);
                                        break;
                                    case il: item = new item.Lingot(pos,rd.nextInt(7));
                                        this.scoreFinal += ((item.Lingot) item).getValeur();
                                        break;
                                    default: item = null;
                                }
                                this.ajouterItem(item);
                            /* Si c'est une objet special */
                            } else {
                                switch(pattern[i][j]) {
                                case pp: this.theEnd = new bloc.BlocPortailFinNiveau(pos);
                                    break;
                                case ph: this.origineHeros = pos;
                                    break;
                                case pm: this.originesMadMonks.add(pos);
                                    break;
                                default :
                                	break;
                                }
                            }
			}
		}
	}

	public void ajouterHeros(jeu.Coordonnees pos) {
		this.origineHeros = new jeu.Coordonnees(pos.getX(), pos.getY());
	}

	public void ajouterMadMonk(jeu.Coordonnees pos) {
		this.originesMadMonks.add(pos);
	}

	public void ajouterBloc(Bloc bloc) {
		this.blocs.add(bloc);
	}

	public Queue<Bloc> recupererListeBlocs() {
		return this.blocs;
	}

	public Queue<item.Item> recupererListeItems() {
		return this.items;
	}

	public Queue<jeu.Coordonnees> recupererPosMadMonk() {
		return originesMadMonks;
	}

	public jeu.Coordonnees posHeros() {
		return origineHeros;
	}

	public void ajouterItem(item.Item item) {
		this.items.add(item);
	}

    public JPanel getApercu() {
		// Should return a image of the world ?
		//return new ImagePanel(ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_custom.png");
		return new ImagePanel(this.imageUrlPreview);
    }
}
