package View.Buttons;

import Model.ViewType;
import Utils.helper.ResourcesPaths;
import View.MenuViews.View;

import javax.swing.*;
import java.awt.*;

import static Utils.helper.Images.getImageFromPath;

public class ViewButton extends JButton {

    /** Basic description of the button */
    private String name;
    private String standardImage;
    private String selectedImage;

    /** Control the transparancy of the button */
    private float alpha = 1.0f;

    /** Interaction field */
    private ViewType nextView;

    public ViewButton() {
        this("",
            "",
            "",
            true,
            ViewType.None);
    }

    public ViewButton(String text, String standardImage, String selectedImage, boolean isEnable, ViewType nextView) {
        super();

        this.name = text;
        this.standardImage = standardImage;
        this.selectedImage = selectedImage;
        this.nextView = nextView;

        this.setBorder(null);
        this.setEnabled(isEnable);
        this.setContentAreaFilled(false);

        if(!standardImage.isEmpty()) {
            this.setIcon(new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + standardImage)));
        }
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

    @Override
    public void paint(Graphics g) {
        //System.out.println("[Rendering] Repaint button " + this.name);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
        super.paint(g2);
        g2.dispose();

    }
}
