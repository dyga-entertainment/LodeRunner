package bloc;

import helper.ResourcesPaths;
import jeu.Coordonnees;
import jeu.bloc.Bloc;

public class BlocGlu extends Bloc {
	
	public BlocGlu(Coordonnees position){
		super(position, ResourcesPaths.SPRITE_BLOCS_GLUE_PATH + "bloc_glu.png");
		this.concret = true;
	}

}
