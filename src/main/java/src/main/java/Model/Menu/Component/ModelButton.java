package Model.Menu.Component;

import Model.Menu.ModelComponent;
import Model.ViewType;

import java.awt.*;

/**
 * This class is used to make a model representation of a JButton.
 * It will only be present in the model side. And will be used by the view when updating
 */
public class ModelButton extends ModelComponent {

    private float transparency;
    private String pressedImageUrl;
    private String mouseOverImageUrl;
    private String text;

    private ViewType nextView;

    public ModelButton() {
        super();
        this.text = "";
        this.transparency = 1.0f;
        this.pressedImageUrl = "";
        this.mouseOverImageUrl = "";

        this.nextView = ViewType.None;
    }

    public void addText(String text) {
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public float getTransparency() {
        return this.transparency;
    }

    public String getPressedImageUrl() {
        return this.pressedImageUrl;
    }

    public String getMouseOverImageUrl() {
        return this.mouseOverImageUrl;
    }

    @Override
    public void setNextView(String viewType) {
        switch (viewType) {
            case "WorldSelection":
                this.nextView = ViewType.WorldSelection;
                break;
            case "HomeMenu":
                this.nextView = ViewType.HomeMenu;
                break;
            case "Credits":
                this.nextView = ViewType.Credits;
                break;
            case "LevelSelection":
                this.nextView = ViewType.LevelSelection;
                break;
            case "Loading":
                this.nextView = ViewType.Loading;
                break;
            case "Settings":
                this.nextView = ViewType.Settings;
                break;
            case "Profils":
                this.nextView = ViewType.Profils;
                break;
        }
    }

    public ViewType getNextView() {
        return this.nextView;
    }

    @Override
    public void addActionListener(String actionName) {
        // TODO
    }

    @Override
    public void addPressedImageUrl(String pressedImage) {
        this.pressedImageUrl = pressedImageUrl;
    }

    @Override
    public String toString() {
        return super.toString() + ", transparency = " + transparency + ", pressedImage = " + pressedImageUrl
            + ", mouseOverImage = " + mouseOverImageUrl + ", text = " + text;
    }

}
