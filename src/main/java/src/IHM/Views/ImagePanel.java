package IHM.Views;

import helper.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    protected Image backgroundImage;

    public ImagePanel(String urlImage) {
        BufferedImage image;
        if((image = Images.getImageFromPath(urlImage)) != null) {
            this.backgroundImage = image;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("[Rendering] PaintComponent call from View");

        g.drawImage(this.backgroundImage,0,0, this.getWidth(), this.getHeight(), this);
    }
}
