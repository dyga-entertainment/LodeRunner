package IHM;

import helper.Images;
import helper.ResourcesPaths;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IHMMenu extends JPanel {

    /** 
     * variable de classe contenant l'image à afficher en fond
     */ 
    private JPanel panelBoutons;

    /**
     * variable de classe contenant l'image à afficher en fond
     */ 
    private Image bg;
    
    
    private int nbBouton;
	
    private JPButton jouer;
    private JPButton multi;
    private JPButton options;
    private JPButton editeur;
    private JPButton credit;

    public JPButton getButton(int i) {
        JPButton b;
        switch(i) {
            case 0 :
                b = this.jouer;
                break;
            case 1 :
                b = this.multi;
                break;
            case 2 :
                b = this.editeur;
                break;
            case 3 :
                b = this.options;
                break;
            default : // case 4
                b = this.credit;
                break;
        }
        return b;
    }

    public IHMMenu() {
        this.bg = Images.getImageFromPath(ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_menu.png");
        this.setPreferredSize(new Dimension(960, 667));
        this.setLayout(new BorderLayout());

        this.nbBouton = 5;
        this.panelBoutons = new JPanel();
        JPanel r = new JPanel();
        r.setLayout(new BorderLayout());

        this.add(r, BorderLayout.SOUTH);
        r.add(this.panelBoutons, BorderLayout.CENTER);
        r.add(new JPanel(), BorderLayout.SOUTH);

        this.panelBoutons.setLayout(new GridLayout(2,1,0,10));
        

        JPanel jpa1 = new JPanel();
        jpa1.setLayout(new GridLayout(1,4,22,0));

        JPanel jpa2 = new JPanel();
        jpa2.setLayout(new GridLayout(1,2,155,0));

        this.jouer = new JPButton("Solo", "bouton_solo1.png", "bouton_solo2.png");
        this.jouer.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        
        });
        /*
        case "Coop" :
                if (profils.getNbJ() == 0) {
                        k = 3;	
                } else {
                        k = 5;
                }
                break;
        case "Editeur" :
                // Editeur
                break;
        case "Options" :
                k = 4;
                break;
        case "Credits" :
                k = 2;
                break;*/
        this.multi = new JPButton("Coop", "bouton_coop1.png", "bouton_coop2.png");
        this.multi.setEnabled(false);
        this.options = new JPButton("Options", "bouton_options1.png", "bouton_options2.png");
        this.options.setEnabled(false);
        this.editeur = new JPButton("Editeur", "bouton_editeur1.png", "bouton_editeur2.png");
        this.editeur.setEnabled(false);
        this.credit = new JPButton("Credits", "bouton_credits1.png", "bouton_credits2.png");

        jpa1.add(new JPanel());
        jpa1.add(this.jouer);
        jpa1.add(this.multi);
        jpa1.add(new JPanel());

        jpa2.add(this.options);
        jpa2.add(this.editeur);
        jpa2.add(this.credit);

        this.panelBoutons.add(jpa1);
        this.panelBoutons.add(jpa2);

        this.setVisible(true);
    }
	
    public void paint(Graphics g) { 
    	g.drawImage(bg,0,0,getSize().width, getSize().height, this);
        this.jouer.repaint();
        this.multi.repaint();
        this.credit.repaint();
        this.editeur.repaint();
        this.options.repaint();
    }
    
    public int getNbButton() {
        return this.nbBouton;
    }

}
