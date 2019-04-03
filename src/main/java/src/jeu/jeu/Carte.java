package jeu;

import item.Item;
import jeu.bloc.Bloc;

/**
 * Classe jeu.Carte. Permet d'organiser la matrice de jeu.
 * 
 * @author Axel Grau
 * @version 1.0
 *
 */
public class Carte {

	private Bloc[][] terrain;
	private Item[][] objets;

	public Carte(int hauteur, int largeur) {
		this.terrain = new Bloc[largeur][hauteur];
		this.objets = new Item[largeur][hauteur];
	}
	
	public Bloc[][] getTerrain() {
		return this.terrain;
	}
	
	public Item[][] getObjet() {
		return this.objets;
	}

	public Bloc getBloc(jeu.Coordonnees pos) {
		return terrain[pos.getX()][pos.getY()];
	}

	public void setBloc(Bloc bloc) {
		this.terrain[bloc.getPosition().getX()][bloc.getPosition().getY()] = bloc;
	}

	public Item getItem(jeu.Coordonnees pos) {
		return objets[pos.getX()][pos.getY()];
	}

	public void setItem(Item item) {
		this.objets[item.getPosition().getX()][item.getPosition().getY()] = item;
	}
	
	public void removeItem(Item item) {
		if (this.objets[item.getPosition().getX()][item.getPosition().getY()] == item) {
			this.objets[item.getPosition().getX()][item.getPosition().getY()] = null;
		}
	}

	public boolean noItem(jeu.Coordonnees coord) {
		return this.objets[coord.getX()][coord.getY()] == null;
	}
}
