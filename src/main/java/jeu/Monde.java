package jeu;

import java.util.ArrayList;
import java.util.List;

/** Classe Monde.
 * Un Monde regroupe plusieurs niveaux sur un theme commun.
 * @author Yann Tireau, Axel Grau
 * @version 1.0
 *
 */
public class Monde {
	
	/* ***** ***** ATTRIBUTS ***** ***** */
	/** Nom du Monde */
	private String Nom;
	/** Liste des niveaux du Monde */
	private List<Niveau> Niveaux;
	/** Fond du Monde */
	private String fond;
	/* ***** ***** ********* ***** ***** */
	
	/** Constructeur
	 * Permet de creer un monde vide avec un fond qui sera le fond
	 * de tous les niveaux de ce monde.
	 * @param nom le nom du Monde
	 * @param background l'image de fond
	 */
	public Monde(String nom, String background){
		this.Nom = nom;
		this.Niveaux = new ArrayList<Niveau>();
		this.fond = background;
	}
	
	public String getNom() {
		return this.Nom;
	}
	
	public int getNbNiveau() {
		return this.Niveaux.size();
	}
	
	public Niveau getNiveau(int i){
		return this.Niveaux.get(i);
	}
	
	public void addNiveau(Niveau lvl){
		lvl.setTheme(this.fond);
		this.Niveaux.add(lvl);
	}
	
	public void addNiveauTemp(Niveau lvl) {
		this.Niveaux.add(lvl);
	}
	
	public void removeNiveau(int i){
		this.Niveaux.remove(i);
	}
}
