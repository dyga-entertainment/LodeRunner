package View.Buttons;

import Model.ViewType;

import java.util.Dictionary;

public class ContextTransitionButton extends StandardButton {

    /** Interaction field */
    private ViewType nextView;

    /** Fields that will contains useful informations when changing views */
    private Dictionary<String, Object> parameters;

    public ContextTransitionButton(String text, String standardImage, String selectedImage) {
        this(text, standardImage, selectedImage, true, ViewType.None);
    }

    public ContextTransitionButton(String text, String standardImage, String selectedImage, boolean isEnable, ViewType nextView) {
        super(null, text, standardImage, selectedImage, isEnable);

        this.nextView = nextView;
    }

    public ViewType getNextView() {
        return this.nextView;
    }

    public void setNextView(ViewType nextView) {
        this.nextView = nextView;
    }

    public void setParameters(Dictionary<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Dictionary<String, Object> getParams() {
        return parameters;
    }
}
