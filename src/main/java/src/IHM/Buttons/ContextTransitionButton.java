package IHM.Buttons;

import controleur.VueManager;
import helper.ResourcesPaths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static helper.Images.getImageFromPath;

public class ContextTransitionButton extends JButton {

    /** Basic description of the button */
    private String name;
    private String standardImage;
    private String selectedImage;

    /** Interaction field */
    private int nextView;

    public ContextTransitionButton(String text, String standardImage, String selectedImage) {
        this(text, standardImage, selectedImage, true);
    }

    public ContextTransitionButton(String text, String standardImage, String selectedImage, boolean isEnable) {
        super(text);

        this.standardImage = standardImage;
        this.selectedImage = selectedImage;

        this.setBorder(null);
        this.setEnabled(isEnable);
        this.setContentAreaFilled(false);
        this.setIcon(new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + standardImage)));

        this.name = text;

        this.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
                setIcon(new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_SELECTED_PATH + selectedImage)));
            }
            public void mouseReleased(MouseEvent e) {
                setIcon(new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + standardImage)));
            }
            public void mouseExited(MouseEvent e) {
            }
        });

        // Useful ?
        //this.repaint();
    }

    public String getNameO() {
        return this.name;
    }

    public String getStandardImage() {
        return this.standardImage;
    }

    public String getSelectedImage() {
        return this.selectedImage;
    }

    public int getNextView() {
        return this.nextView;
    }

    public void setNextView(VueManager.ViewType nextView) {
        this.nextView = nextView.ordinal();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("[Rendering] Repaint button " + this.name);
    }
}
