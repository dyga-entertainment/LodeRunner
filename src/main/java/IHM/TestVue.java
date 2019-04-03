package IHM;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import jeu.ModeleJeu;
import jeu.bloc.Bloc;
import jeu.personnage.Personnage;

public class TestVue extends JPanel implements Observer {

    private VueJeu vue;
    private HeadUpDisplay hud;

    public TestVue(ModeleJeu m) {
        this.vue = new VueJeu(m);
        this.hud = new HeadUpDisplay("MDR", 10, 0);
        //this.hud.setLayout(new GridLayout(1,2));

        this.setLayout(new BorderLayout());

        /* Laisons des objets entre eux */
        for(Bloc[] h : m.AccesNiveau()) {
            for(Bloc b : h) {
                if(b != null) {
                    b.addObserver(this);
                    b.getSprite().addObserver(this);
                }
            }
        }
        for (Personnage cour : m.getListPerso()) {
                cour.addObserver(this);
                cour.getSprite().addObserver(this);
        }
        this.add(this.vue, BorderLayout.CENTER);
        this.add(this.hud, BorderLayout.SOUTH);
        this.repaint();
    }
	
    public void paint(Graphics g) { 
    	//g.drawImage(bg,0,0,getSize().width, getSize().height, this); 
    	this.hud.repaint();
    	this.vue.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
            this.hud.update(o, arg);
            this.vue.update(o, arg);

    }
	
}
