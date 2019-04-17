package Model.Menu;

import Utils.helper.Images;

import java.awt.*;

/**
 * This class is used to make a model representation of a JButton.
 * It will only be present in the model side. And will be used by the view when updating
 */
public class ModelButton extends ModelComponent {

    private float transparency;
    private Image pressedImage;
    private Image mouseOverImage;
    private String text;

    public ModelButton() {
        super();
        this.text = "";
        this.transparency = 1.0f;
        this.pressedImage = null;
        this.mouseOverImage = null;
    }

    @Override
    public void addBackgroundImageUrl(String backgroundImage) {

    }

    @Override
    public void setLayout(String layout) {

    }

    public void addText(String text) {
        this.text = text;
    }

    @Override
    public void add(ModelComponent component) {

    }

    @Override
    public void addActionListener(String actionName) {

    }

    @Override
    public String toString() {
        return super.toString() + ", transparency = " + transparency + ", pressedImage = " + pressedImage
            + ", mouseOverImage = " + mouseOverImage + ", text = " + text;
    }

}
