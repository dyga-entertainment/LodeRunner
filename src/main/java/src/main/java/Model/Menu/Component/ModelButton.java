package Model.Menu.Component;

import Model.Menu.ModelComponent;
import Model.ViewType;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * This class is used to make a model representation of a JButton.
 * It will only be present in the model side. And will be used by the view when updating
 */
public class ModelButton extends ModelComponent {

    private String text;
    private float transparency;
    private String pressedImageUrl;
    private String mouseOverImageUrl;
    private Dimension dimension;

    private Dictionary<String, String> listeners = new Hashtable<>();
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

    public void setPreferredSize(int width, int height) {
        dimension = new Dimension(width, height);
    }

    public Dimension getPreferredSize() {
        return this.dimension;
    }

    public void addListenerMethod(String listenerName, String callbackName) {
        this.listeners.put(listenerName, callbackName);
    }

    public Dictionary<String, String> getListenersMap() {
        return this.listeners;
    }

    @Override
    public String toString() {
        return super.toString() + ", transparency = " + transparency + ", pressedImage = " + pressedImageUrl
            + ", mouseOverImage = " + mouseOverImageUrl + ", text = " + text;
    }

}
