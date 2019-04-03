package bloc;

import helper.ResourcesPaths;
import jeu.Coordonnees;
import jeu.bloc.Bloc;

public class BlocBarreTraversee extends Bloc {

	public BlocBarreTraversee(Coordonnees position) {
		super(position, ResourcesPaths.SPRITE_BLOCS_PATH + "bloc_barre_traversee.png");
	}

}
