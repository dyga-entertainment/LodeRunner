package Model.Menu;

import Utils.helper.Images;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModelPanel extends ModelComponent {

    private Image backgroundImage;
    private ModelLayout layout;
    private List<ModelComponent> components;

    // ♥
    public ModelPanel() {
        this.components = new ArrayList<>();
    }

    @Override
    public void addBackgroundImageUrl(String backgroundImage) {
        this.backgroundImage = Images.getImageFromPath(backgroundImage);
    }

    @Override
    public void setLayout(String layoutName) {
        switch (layoutName) {
            case "BorderLayout":
                this.layout = ModelLayout.BorderLayout; break;
            case "WrapLayout":
                this.layout = ModelLayout.WrapLayout; break;
            case "FlowLayout":
                this.layout = ModelLayout.FlowLayout; break;
            case "GridLayout":
                this.layout = ModelLayout.GridLayout; break;
            default:
                System.out.println("Layout unknown : " + layoutName);
                System.exit(1); // TODO raise an exception ?
                break;
        }

    }

    @Override
    public void addText(String text) {

    }

    @Override
    public void add(ModelComponent component) {
        this.components.add(component);
    }

    @Override
    public void addActionListener(String actionName) {

    }

    @Override
    public String toString() {
        return super.toString() + ", Background image = " + this.backgroundImage + ", layout = " + layout +
            "Children = " + components.toString();
    }

}
