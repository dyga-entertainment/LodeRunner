package MVC.Model.bloc;

import MVC.Model.jeu.Coordonnees;

public class BlocBrique extends Bloc {

	public BlocBrique(Coordonnees position) {
		//super(position, ResourcesPaths.SPRITE_BLOCS_GROUND_PATH + "bloc_pierre.png");
		super(position, "bloc_pierre.png");
		this.concret = true;
	}
        
        @Override
        public void destruction() {   
        }

}
