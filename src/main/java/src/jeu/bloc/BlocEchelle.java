package bloc;

import helper.ResourcesPaths;
import jeu.Coordonnees;
import jeu.bloc.Bloc;

public class BlocEchelle extends Bloc {
	
    public BlocEchelle(Coordonnees position){
        super(position, ResourcesPaths.SPRITE_BLOCS_PATH + "bloc_echelle.png");
        this.setConcret(false);
    }
	
    /* Ne se reconstruit jamais */
    public void reconstruction(){ }

}
