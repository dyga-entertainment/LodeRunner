package item;

import helper.ResourcesPaths;
import jeu.Coordonnees;

/** Classe Lingot.
 * 
 * @author Damien Maveyraud (modifications : Axel Grau)
 * @version 1.0
 *
 */
public class Lingot extends item.Item {
	
	private int valeur;
	
	public Lingot(Coordonnees position, int valeur){
		super(position, ResourcesPaths.SPRITE_ITEMS_GOLD_PATH + "lingot.png");
		String goldValue = "";
		switch (valeur){
		case 1 :
			goldValue = "lingot.png";
			break;
		case 2 :
			goldValue = "lingot_amethyste.png";
			break;
		case 3 :
			goldValue = "lingot_emeraude.png";
			break;
		case 4 :
			goldValue = "lingot_saphir.png";
			break;
		case 5 :
			goldValue = "lingot_rubis.png";
			break;
		case 6 :
			goldValue = "lingot_diamant.png";
			break;
		}
		this.setSprite(ResourcesPaths.SPRITE_ITEMS_GOLD_PATH + goldValue);
		this.valeur = valeur;
	}
	
	public int getValeur() {
		return new Integer(this.valeur);
	}

}
