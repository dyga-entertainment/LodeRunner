package View.MenuViews;

import Controler.ControleurJeu;
import Utils.helper.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    /** Current display background image */
    protected Image backgroundImage;
    protected Point startingCoordinate;
    protected Dimension preferredSize;

    public ImagePanel(String urlImage) {
        super();
        this.startingCoordinate = null;
        this.preferredSize = null;

        BufferedImage image;
        if((image = Images.getImageFromPath(urlImage)) != null) {
            this.backgroundImage = image;
        }
    }

    public void setStartingCoordinate(int x, int y) {
        this.startingCoordinate = new Point(x, y);
    }

    public void setPreferredSize(int width, int height) {
        this.preferredSize = new Dimension(width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("[Rendering] PaintComponent call from ImagePanel");
        if(this.startingCoordinate == null || this.preferredSize == null) {
            g.drawImage(
                this.backgroundImage,
                0,
                0,
                this.getWidth(),
                this.getHeight(),
                this
            );
        } else {
            g.drawImage(
                this.backgroundImage,
                this.startingCoordinate.x,
                this.startingCoordinate.y,
                this.preferredSize.width,
                this.preferredSize.height,
                this
            );
        }

    }
}
