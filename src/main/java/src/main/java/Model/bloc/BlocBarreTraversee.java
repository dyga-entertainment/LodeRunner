package Model.bloc;

import Utils.helper.ResourcesPaths;
import Model.jeu.Coordonnees;

public class BlocBarreTraversee extends Bloc {

	public BlocBarreTraversee(Coordonnees position) {
		super(position, ResourcesPaths.SPRITE_BLOCS_PATH + "bloc_barre_traversee.png");
	}

}
