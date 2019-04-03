package jeu.bloc;

import java.util.Observable;
import java.util.Observer;
import jeu.Coordonnees;
import jeu.Actions.Timer;
import jeu.Positionnable;
import ressources.Images;

/** Classe BlocTerre.
 * Implemente les blocs de terre.
 * @author Damien Maveyraud (modifications : Axel Grau)
 * @version 1.0
 *
 */
public class BlocTerre extends Bloc implements Observer {

    public BlocTerre(Coordonnees position, boolean trap){
        super(position, "/ressources/Images/bloc_terre.png");
        this.concret = !trap;
    }
	
    public void reconstruction(){
        System.out.println("coucou");
        this.setConcret(true);
        getSprite().modifierImage("/ressources/Images/bloc_terre.png");
        mettreAJour();
    }
	
    /** destruction
     * Permet de notifier au modele que le bloc se detruit.
     */
    public void creuser(){
        //this.getSprite().playSequence(Images.blocExplosion, false);
        this.setConcret(false);
        Positionnable.GESTION_ANIM.ajouterPositionnableAnime(this, Images.blocExplosion);
        //Thread t = new Thread(new ThreadCreuser());
        //t.start();
    }
    
    @Override
    public void destruction() {
        super.destruction();
        Timer t = new Timer(8000, this);
        t.addObserver(this);
        new Thread(t).start();
    }
    
    public boolean isConcret(){
    	return this.concret;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.reconstruction();
    }
}
