package Model.Menu;

import Model.ViewType;

import java.awt.*;

public abstract class ModelComponent {

    /** Background image related info **/
    protected String backgroundImageUrl;
    private Point backgroundImageStartingPoint;
    private Dimension backgroundImagePreferredSize;

    /** Others settings info **/
    protected boolean opaque;
    protected boolean enable;

    /** Layout related */
    protected int layoutIndex;
    protected String borderLayoutConstraint;

    public ModelComponent() {
        this.backgroundImageUrl = "";
        this.opaque = true;
        this.enable = true;
        this.layoutIndex = -1;
        this.borderLayoutConstraint = "";
    }

    public void addBackgroundImageUrl(String backgroundImage) {
        this.backgroundImageUrl = backgroundImage;
    }

    public String getBackgroundImageUrl(){
        return this.backgroundImageUrl;
    }

    public void setOpaque(boolean isOpaque) {
        this.opaque = isOpaque;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public void setEnable(boolean isEnable) {
        this.enable = isEnable;
    }

    public boolean isEnable() {
        return this.enable;
    }

    @Override
    public String toString() {
        return "isOpaque = " + opaque +
            ", enable = " + enable;
    }

    public void setLayout(String layout) { }

    public void addText(String text){ }

    public abstract void addActionListener(String actionName);

    public void setNextView(String nextView) {
    }

    public boolean hasLayoutConstraint() {
        return !this.borderLayoutConstraint.isEmpty() || this.layoutIndex != -1;
    }

    public boolean hasBorderLayoutLayoutConstraint() {
        return !this.borderLayoutConstraint.isEmpty();
    }

    public int getIndexLayout() {
        return this.layoutIndex;
    }

    public void addBorderLayoutConstraints(String borderLayoutConstraints) {
        switch (borderLayoutConstraints) {
            case "NORTH":
                this.borderLayoutConstraint = BorderLayout.NORTH;
                break;
            case "WEST":
                this.borderLayoutConstraint = BorderLayout.WEST;
                break;
            case "EAST":
                this.borderLayoutConstraint = BorderLayout.EAST;
                break;
            case "SOUTH":
                this.borderLayoutConstraint = BorderLayout.SOUTH;
                break;
            default:
                this.borderLayoutConstraint = BorderLayout.CENTER;
                break;
        }
    }

    public void setBackgroundStartingPoint(int x, int y) {
        this.backgroundImageStartingPoint = new Point(x, y);
    }

    public Point getBackgroundStartingPoint() {
        return this.backgroundImageStartingPoint;
    }

    public void setBackgroundPreferredSize(int width, int height) {
        this.backgroundImagePreferredSize = new Dimension(width, height);
    }

    public Dimension getBackgroundPreferredSize() {
        return this.backgroundImagePreferredSize;
    }

    public String getBorderLayoutContraints() {
        return this.borderLayoutConstraint;
    }

    public void addPressedImageUrl(String pressedImage) { }
}
