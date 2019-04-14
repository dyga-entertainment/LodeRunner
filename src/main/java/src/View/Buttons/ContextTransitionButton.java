package View.Buttons;

import View.ViewManager;

import java.util.Dictionary;

public class ContextTransitionButton extends StandardButton {

    /** Interaction field */
    private ViewManager.ViewType nextView;

    /** Fields that will contains useful informations when changing views */
    private Dictionary<String, Object> parameters;

    public ContextTransitionButton(String text, String standardImage, String selectedImage) {
        this(text, standardImage, selectedImage, true, ViewManager.ViewType.None);
    }

    public ContextTransitionButton(String text, String standardImage, String selectedImage, boolean isEnable, ViewManager.ViewType nextView) {
        super(text, standardImage, selectedImage, isEnable);

        this.nextView = nextView;
    }

    public ViewManager.ViewType getNextView() {
        return this.nextView;
    }

    public void setNextView(ViewManager.ViewType nextView) {
        this.nextView = nextView;
    }

    public void setParameters(Dictionary<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Dictionary<String, Object> getParams() {
        return parameters;
    }
}
