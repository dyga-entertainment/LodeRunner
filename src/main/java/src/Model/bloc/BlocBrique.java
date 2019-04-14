package Model.bloc;

import Utils.helper.ResourcesPaths;
import Model.jeu.Coordonnees;

public class BlocBrique extends Bloc {

	public BlocBrique(Coordonnees position) {
		super(position, ResourcesPaths.SPRITE_BLOCS_GROUND_PATH + "bloc_pierre.png");
		this.concret = true;
	}
        
        @Override
        public void destruction() {   
        }

}
