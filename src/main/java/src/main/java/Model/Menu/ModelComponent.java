package Model.Menu;

public abstract class ModelComponent {

    private boolean isOpaque;
    private boolean enable;

    public ModelComponent() {
        this.isOpaque = true;
    }

    public abstract void addBackgroundImageUrl(String backgroundImage);

    public void setOpaque(boolean isOpaque) {
        this.isOpaque = isOpaque;
    }

    public abstract void setLayout(String layoutName);

    public void setEnable(boolean isEnable) {
        this.enable = isEnable;
    }

    public abstract void addText(String text);

    public abstract void add(ModelComponent component);

    public abstract void addActionListener(String actionName);

    @Override
    public String toString() {
        return "isOpaque = " + isOpaque +
            ", enable = " + enable;
    }
}
