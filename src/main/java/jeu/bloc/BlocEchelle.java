package jeu.bloc;

import jeu.Coordonnees;

public class BlocEchelle extends Bloc {
	
    public BlocEchelle(Coordonnees position){
        super(position, "/ressources/Images/bloc_echelle.png");
        this.setConcret(false);
    }
	
    /* Ne se reconstruit jamais */
    public void reconstruction(){ }

}
