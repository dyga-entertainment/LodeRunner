package bloc;

import helper.ResourcesPaths;
import jeu.Coordonnees;
import jeu.bloc.Bloc;

public class BlocBrique extends Bloc {

	public BlocBrique(Coordonnees position) {
		super(position, ResourcesPaths.SPRITE_BLOCS_GROUND_PATH + "bloc_pierre.png");
		this.concret = true;
	}
        
        @Override
        public void destruction() {   
        }

}
