package bloc;

import helper.ResourcesPaths;
import jeu.Coordonnees;
import jeu.bloc.Bloc;

public class BlocPortailFinNiveau extends Bloc {
	
	public BlocPortailFinNiveau(Coordonnees position) {
		super(position, ResourcesPaths.ANIM_BLOCK_PORTAL_PATH + "bloc_portail1.png");
		this.setConcret(false);
		//this.getSprite().playSequence(Images.gate, true);
	}

	@Override
	public void destruction() {
            /* rien pour l'instant */
	}

}
