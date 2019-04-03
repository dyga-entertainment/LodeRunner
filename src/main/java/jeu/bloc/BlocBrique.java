package jeu.bloc;

import jeu.Coordonnees;

public class BlocBrique extends Bloc {

	public BlocBrique(Coordonnees position) {
		super(position, "/ressources/Images/bloc_pierre.png");
		this.concret = true;
	}
        
        @Override
        public void destruction() {   
        }

}
