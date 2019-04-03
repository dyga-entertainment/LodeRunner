package helper;
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
            url = Thread.currentThread().getContextClassLoader().getResource(imgfile);
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
            fall[i] = get(ResourcesPaths.ANIM_PLAYER_FALLS_PATH + "hero_fry_fall" + (i+1) + ".png") ;
            climbLadder[i] = get(ResourcesPaths.ANIM_PLAYER_STEERING_CLIMB_PATH + "hero_fry_ladder" + (i+1) + ".png") ;
        }
        for (int i = 0 ; i < 3 ; i++) {
            moveRight[i] = get(ResourcesPaths.ANIM_PLAYER_STEERING_WALK_PATH + "hero_fry_walk" + (i+1) + "_right.png") ;
            moveLeft[i] = get(ResourcesPaths.ANIM_PLAYER_STEERING_WALK_PATH + "hero_fry_walk" + (i+1) + "_left.png") ;
        }
        for (int i = 0 ; i < 4 ; i++) {
            gate[i] = get(ResourcesPaths.ANIM_BLOCK_PORTAL_PATH + "bloc_portail" + (i+1) + ".png");
        }
        for (int i = 0 ; i < blocExplosion.length-1 ; i++) {
            blocExplosion[i] = get(ResourcesPaths.ANIM_BLOCK_DESTRUCTION_PATH + "bloc_terre_destruction" + (i+1) + ".png") ;
        }
        blocExplosion[5] = get(ResourcesPaths.SPRITE_BLOCS_PATH + "bloc_vide.png") ;
        for (int i = 0 ; i < 5 ; i++) {
            explosion[i] = get(ResourcesPaths.ANIM_ITEMS_EXPLOSION_PATH + "explosion" + (i+1) + ".png");
        }
        explosion[5] = get(ResourcesPaths.SPRITE_BLOCS_PATH + "bloc_vide.png") ;
        for (int i =  0 ; i < 3 ; i++) {
            petiteBombeAllumee[i] = get(ResourcesPaths.ANIM_ITEMS_SMALLBOMB_PATH + "gadget_petite_bombe_allumee" + (i+1) +".png");
        }
        for (int i = 0 ; i < 8 ; i++) {
            deathRight[i] = get(ResourcesPaths.ANIM_PLAYER_DEATH_ENDING_RIGHT_PATH + "animation_mort_droite" + (i+1) + ".png");
            deathLeft[i] = get(ResourcesPaths.ANIM_PLAYER_DEATH_ENDING_LEFT_PATH + "animation_mort_gauche" + (i+1) + ".png");
        }
        for(int i = 0 ; i < 12; i++) {
            gaz[i] = get(ResourcesPaths.ANIM_ITEMS_GAZ_PATH + "bloc_gaz" + ((i%4)+1) + ".png");
        }
        gaz[12] = get(ResourcesPaths.SPRITE_BLOCS_PATH + "bloc_vide.png");
        win[0] = get(ResourcesPaths.ANIM_PLAYER_SUCCESS_ENDING_PATH + "animation_fin_1.png");
        for(int i = 1 ; i < 8 ; i++) {
        	win[i] = get(ResourcesPaths.ANIM_PLAYER_SUCCESS_ENDING_PATH + "animation_fin" + (i+1) +".png");
        }
    }

}
