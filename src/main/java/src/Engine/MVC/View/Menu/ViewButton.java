package MVC.View.Menu;

import MVC.Model.Menu.Enums.DisplayOption;
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

    private Background background;
    private DisplayOption option;

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
        this.background = new Background();

        this.name = text;
        this.standardImage = standardImage;
        this.nextView = nextView;

        this.setBorder(null);
        this.setEnabled(isEnable);
        //this.setContentAreaFilled(false);
    }

    public void setBackgroundImage(String standardImage, Point startingPoint, Dimension preferredSize) {
        //this.selectedImage = selectedImage;
        this.standardImage = standardImage;
        if(!standardImage.isEmpty()) {
            // Create the new image
            //ImageIcon image = new ImageIcon(getImageFromPath(ResourcesPaths.SPRITE_UI_BUTTONS_PATH + standardImage));
            ImageIcon image = new ImageIcon(getImageFromPath(standardImage));
            // Create a new image scaled
            Image scaledImage;
            if(this.option == DisplayOption.Scale && getPreferredSize().width != 0 && getPreferredSize().height != 0) {
                    scaledImage = image.getImage().getScaledInstance(getPreferredSize().width, getPreferredSize().height, Image.SCALE_DEFAULT);
            } else {
                scaledImage = image.getImage();
            }
            //scaledImage = image.getImage();
            // Finally add it to the button
            this.background.backgroundImage = new ImageIcon(scaledImage).getImage();
            //this.setIcon(new ImageIcon(scaledImage));
        }
    }

    public Image getImage() {
        return this.background.backgroundImage;
    }

    public String getImageUrl() {
        return this.standardImage;
    }

    public void setDisplayOption(DisplayOption option) {
        this.option = option;
    }

    @Override
    public void setPreferredSize(Dimension desiredDimension) {
        super.setPreferredSize(desiredDimension);
        this.dimension = desiredDimension;
    }

    public void setNextView(String nextViewName) {
        switch (nextViewName) {
            case "HomeMenu":
                this.nextView = ViewType.HomeView;
                break;
            case "Credits":
                this.nextView = ViewType.CreditsView;
                break;
            case "LevelSelection":
                this.nextView = ViewType.LevelSelectionView;
                break;
            case "Loading":
                this.nextView = ViewType.LoadingView;
                break;
            case "Settings":
                this.nextView = ViewType.SettingsView;
                break;
            case "WorldSelectionView":
                this.nextView = ViewType.WorldSelectionView;
                break;
            case "Profils":
                this.nextView = ViewType.ProfilsView;
                break;
            default:
                this.nextView = ViewType.None;
                break;
        }

    }

    public String getNextView() {
        return this.nextView.name();
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /** Delegate the work to the background class */
        if(background != null) {
            background.paintComponent(g, getWidth(), getHeight(), this);
        }
    }
}
