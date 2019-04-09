package jeu;

import java.util.ArrayList;
import java.util.List;

/** Classe jeu.Monde.
 * Un jeu.Monde regroupe plusieurs niveaux sur un theme commun.
 * @author Yann Tireau, Axel Grau
 * @version 1.0
 *
 */
public class Monde {
	
	/* ***** ***** ATTRIBUTS ***** ***** */
	/** Nom du jeu.Monde */
	private String Nom;
	/** Liste des niveaux du jeu.Monde */
	private List<jeu.Niveau> Niveaux;
	/** Fond du jeu.Monde */
	private String fond;
	/* ***** ***** ********* ***** ***** */
	
	/** Constructeur
	 * Permet de creer un monde vide avec un fond qui sera le fond
	 * de tous les niveaux de ce monde.
	 * @param nom le nom du jeu.Monde
	 * @param background l'image de fond
	 */
	public Monde(String nom, String background){
		this.Nom = nom;
		this.Niveaux = new ArrayList<jeu.Niveau>();
		this.fond = background;
	}
	
	public String getNom() {
		return this.Nom;
	}
	
	public int getNbNiveau() {
		return this.Niveaux.size();
	}
	
	public jeu.Niveau getNiveau(int i){
		return this.Niveaux.get(i);
	}
	
	public void addNiveau(jeu.Niveau lvl){
		lvl.setTheme(this.fond);
		this.Niveaux.add(lvl);
	}
	
	public void addNiveauTemp(jeu.Niveau lvl) {
		this.Niveaux.add(lvl);
	}
	
	public void removeNiveau(int i){
		this.Niveaux.remove(i);
	}
}
