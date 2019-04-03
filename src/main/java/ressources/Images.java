package ressources;
import javax.imageio.* ;
import java.awt.image.* ;
import java.io.* ;
import java.net.URL;

public class Images {

    public static BufferedImage[] moveRight ;
    public static BufferedImage[] moveLeft ;
    public static BufferedImage[] climbLadder ;
    public static BufferedImage[] fall ;
    public static BufferedImage[] blocExplosion;
    public static BufferedImage[] gate;
    public static BufferedImage[] explosion;
    public static BufferedImage[] petiteBombeAllumee;
    public static BufferedImage[] deathRight;
    public static BufferedImage[] deathLeft;
    public static BufferedImage[] gaz;
    public static BufferedImage[] win;

    public BufferedImage get(String imgfile) {
	try {
	    // Read from a file       
            URL url;
            url = getClass().getResource(imgfile);
	    return ImageIO.read(url);
	} catch (IOException e) {
	    System.err.println("Le fichier " + imgfile + " est introuvable.") ;
	    System.exit(1) ;
	    return null ;
	}
    }

    public Images() {
        fall = new BufferedImage[2];
        climbLadder = new BufferedImage[2];
        moveRight = new BufferedImage[3] ;
        moveLeft = new BufferedImage[3] ;
        gate = new BufferedImage[4];
        blocExplosion = new BufferedImage[6];
        explosion = new BufferedImage[6];
        petiteBombeAllumee = new BufferedImage[3];
        deathRight = new BufferedImage[8];
        deathLeft = new BufferedImage[8];
        gaz = new BufferedImage[13];
        win = new BufferedImage[8];
        for (int i = 0; i< 2; i++) {
            fall[i] = get("/ressources/Images/hero_fry_fall" + (i+1) + ".png") ;
            climbLadder[i] = get("/ressources/Images/hero_fry_ladder" + (i+1) + ".png") ;
        }
        for (int i = 0 ; i < 3 ; i++) {
            moveRight[i] = get("/ressources/Images/hero_fry_walk" + (i+1) + "_right.png") ;
            moveLeft[i] = get("/ressources/Images/hero_fry_walk" + (i+1) + "_left.png") ;
        }
        for (int i = 0 ; i < 4 ; i++) {
            gate[i] = get("/ressources/Images/bloc_portail" + (i+1) + ".png");
        }
        for (int i = 0 ; i < blocExplosion.length-1 ; i++) {
            blocExplosion[i] = get("/ressources/Images/bloc_terre_destruction" + (i+1) + ".png") ;
        }
        blocExplosion[5] = get("/ressources/Images/bloc_vide.png") ;
        for (int i = 0 ; i < 5 ; i++) {
            explosion[i] = get("/ressources/Images/explosion" + (i+1) + ".png");
        }
        explosion[5] = get("/ressources/Images/bloc_vide.png") ;
        for (int i =  0 ; i < 3 ; i++) {
            petiteBombeAllumee[i] = get("/ressources/Images/gadget_petite_bombe_allumee" + (i+1) +".png");
        }
        for (int i = 0 ; i < 8 ; i++) {
            deathRight[i] = get("/ressources/Images/animation_mort_droite" + (i+1) + ".png");
            deathLeft[i] = get("/ressources/Images/animation_mort_gauche" + (i+1) + ".png");
        }
        for(int i = 0 ; i < 12; i++) {
            gaz[i] = get("/ressources/Images/bloc_gaz" + ((i%4)+1) + ".png");
        }
        gaz[12] = get("/ressources/Images/bloc_vide.png") ;
        win[0] = get("/ressources/Images/animation_fin_1.png");
        for(int i = 1 ; i < 8 ; i++) {
        	win[i] = get("/ressources/Images/animation_fin" + (i+1) +".png");
        }
    }

}
