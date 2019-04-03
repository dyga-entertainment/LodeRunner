package jeu.bloc;

import jeu.Coordonnees;

public class BlocGlu extends Bloc {
	
	public BlocGlu(Coordonnees position){
		super(position,"/ressources/Images/bloc_glu.png");
		this.concret = true;
	}

}
