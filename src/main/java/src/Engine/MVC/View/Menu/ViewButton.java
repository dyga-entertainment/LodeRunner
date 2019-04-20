package MVC.View.Menu;

import MVC.Model.ViewType;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

import static Utils.helper.Images.getImageFromPath;

public class ViewButton extends JButton {

    /** Basic description of the button */
    private String name;
    private String standardImage;
    private String selectedImage;
    private Dimension dimension;

    /** Control the transparancy of the button */
    private float alpha = 1.0f;

    /** Interaction field */
    private ViewType nextView;
    private Dictionary<String, String> listeners = new Hashtable<>();

    public ViewButton() {
        this("",
            "",
            true,
            ViewType.None);
    }

    public ViewButton(String text, String standardImage, boolean isEnable, ViewType nextView) {
        super();

        this.name = text;
        this.standardImage = standardImage;
        this.nextView = nextView;

        this.setBorder(null);
        this.setEnabled(isEnable);
        this.setContentAreaFilled(false);
    }

    public void setBackgroundImage(String standardImage) {
        this.selectedImage = selectedImage;
        if(!standardImage.isEmpty()) {
            // Create the new image
            //ImageIcon image = new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + standardImage));
            ImageIcon image = new ImageIcon(getImageFromPath(standardImage));
            // Create a new image scaled
            Image scaledImage;
            if(dimension != null) {
                scaledImage = image.getImage().getScaledInstance(this.dimension.width, this.dimension.height, Image.SCALE_SMOOTH);
            } else {
                scaledImage = image.getImage();
            }
            // Finally add it to the button
            this.setIcon(new ImageIcon(scaledImage));
        }
    }

    @Override
    public void setPreferredSize(Dimension desiredDimension) {
        super.setPreferredSize(desiredDimension);

        this.dimension = desiredDimension;


    }

    public String getButtonName() {
        return this.name;
    }

    public String getStandardImage() {
        return this.standardImage;
    }

    public String getSelectedImage() {
        return this.selectedImage;
    }

    public void setTransparency(float alpha) {
        this.alpha = alpha;
    }

    public void setListenerMethodsDict(Dictionary<String, String> dict) {
        this.listeners = dict;
    }

    public Dictionary<String, String> getListenersMap() {
        return this.listeners;
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println("[Rendering] Repaint button " + this.name);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
        super.paint(g2);
        g2.dispose();

    }
}
