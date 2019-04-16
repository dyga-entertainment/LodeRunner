package Model.Menu;

import java.awt.*;

/**
 * This class is used to make a model representation of a JButton.
 * It will only be present in the model side. And will be used by the view when updating
 */
public class ModelButton extends ModelComponent {

    private float transparency;
    private Image image;
    private Image pressedImage;
    private Image mouseOverImage;

    public ModelButton() {
        super();
        this.transparency = 1.0f;
        this.transparency = 1.0f;
        this.image = null;
        this.pressedImage = null;
        this.mouseOverImage = null;
    }

}
