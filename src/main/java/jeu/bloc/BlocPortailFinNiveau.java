package jeu.bloc;

import jeu.Coordonnees;
import ressources.Images;

public class BlocPortailFinNiveau extends Bloc {
	
	public BlocPortailFinNiveau(Coordonnees position) {
		super(position, "/ressources/Images/bloc_portail1.png");
		this.setConcret(false);
		//this.getSprite().playSequence(Images.gate, true);
	}

	@Override
	public void destruction() {
            /* rien pour l'instant */
	}

}
