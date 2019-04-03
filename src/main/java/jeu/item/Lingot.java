package jeu.item;

import jeu.Coordonnees;

/** Classe Lingot.
 * 
 * @author Damien Maveyraud (modifications : Axel Grau)
 * @version 1.0
 *
 */
public class Lingot extends Item {
	
	private int valeur;
	
	public Lingot(Coordonnees position,int valeur){
		super(position, "/ressources/Images/lingot.png");
		switch (valeur){
		case 1 : 
			this.setSprite("/ressources/Images/lingot.png");
			break;
		case 2 :
			this.setSprite("/ressources/Images/lingot_amethyste.png");
			break;
		case 3 :
			this.setSprite("/ressources/Images/lingot_emeraude.png");
			break;
		case 4 :
			this.setSprite("/ressources/Images/lingot_saphir.png");
			break;
		case 5 :
			this.setSprite("/ressources/Images/lingot_rubis.png");
			break;
		case 6 :
			this.setSprite("/ressources/Images/lingot_diamant.png");
			break;
		}
		this.valeur = valeur;
	}
	
	public int getValeur() {
		return new Integer(this.valeur);
	}

}
