package item;

import jeu.Coordonnees;
import jeu.Positionnable;

/** Classe abstraire Item.
 * 
 * @author Damien Maveyraud (modifications : Axel Grau)
 * @version 0.1
 *
 */
public abstract class Item extends Positionnable {

	/** Constructeur.
	 * 
	 * @param position position de l'item dans la matrice.
	 * @param img image de l'item.
	 */
	public Item(Coordonnees position, String img) {
		super(position, img);
	}

	/* Nom : ramasser
	 * Semantique : place l'objet dans l'inventaire du personnage
	 * Parametres : /
	 * Type retour : /
	 */
	public void ramasser(personnage.Personnage perso){
		perso.ramasser(this);
	}
	
	/* Nom : destruction
	 * Semantique : enleve l'objet du modele de jeu
	 * Parametres : /
	 * Type retour : /
	 */
	public void destruction(){
		/* notifier le modele qu'on retire l'item */
		this.notifyObservers();
	}
}
