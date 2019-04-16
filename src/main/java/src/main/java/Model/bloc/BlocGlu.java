package Model.bloc;

import Utils.helper.ResourcesPaths;
import Model.jeu.Coordonnees;

public class BlocGlu extends Bloc {
	
	public BlocGlu(Coordonnees position){
		super(position, ResourcesPaths.SPRITE_BLOCS_GLUE_PATH + "bloc_glu.png");
		this.concret = true;
	}

}
