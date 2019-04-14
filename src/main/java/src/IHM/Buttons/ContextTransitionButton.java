package IHM.Buttons;

import controleur.VueManager;
import helper.ResourcesPaths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Dictionary;

import static helper.Images.getImageFromPath;

public class ContextTransitionButton extends StandardButton {

    /** Interaction field */
    private VueManager.ViewType nextView;

    /** Fields that will contains useful informations when changing views */
    private Dictionary<String, Object> parameters;

    public ContextTransitionButton(String text, String standardImage, String selectedImage) {
        this(text, standardImage, selectedImage, true, VueManager.ViewType.None);
    }

    public ContextTransitionButton(String text, String standardImage, String selectedImage, boolean isEnable, VueManager.ViewType nextView) {
        super(text, standardImage, selectedImage, isEnable);

        this.nextView = nextView;
    }

    public VueManager.ViewType getNextView() {
        return this.nextView;
    }

    public void setNextView(VueManager.ViewType nextView) {
        this.nextView = nextView;
    }

    public void setParameters(Dictionary<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Dictionary<String, Object> getParams() {
        return parameters;
    }
}
