package IHM;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class Bandeau extends JPanel {
    
    private Image bg;
    
    public Bandeau() {
        try {
            URL url = getClass().getResource("/ressources/Images/bandeau_menu.png");
            bg = ImageIO.read(url);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public abstract int getNbButton();

    public abstract JPButton getButton(int i);

    public void paint(Graphics g) { 
    g.drawImage(bg,0,0,getSize().width, getSize().height, this);
    for (int i = 0; i < this.getNbButton(); i++) {
        this.getButton(i).repaint();
            this.getButton(i).setOpaque(true);
        }
    }
	
}