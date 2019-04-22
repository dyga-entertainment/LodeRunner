package MVC.Model.Menu.Component;

import MVC.Model.Menu.Enums.DisplayOption;
import MVC.Model.Menu.ModelComponent;
import MVC.Model.Menu.Enums.ModelLayout;
import MVC.Model.Menu.Structs.Font;
import MVC.Model.Menu.Structs.Layout;

import java.util.ArrayList;
import java.util.List;

public class ModelPanel extends ModelComponent {

    private Layout layout;
    private List<ModelComponent> components;
    private DisplayOption option;

    // ♥
    public ModelPanel() {
        super();
        this.components = new ArrayList<>();
        this.layout = new Layout();
        this.layout.name = ModelLayout.FlowLayout;
    }

    @Override
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public void add(ModelComponent component) {
        this.components.add(component);
    }

    public List<ModelComponent> getChildrenComponents() {
        return this.components;
    }

    @Override
    public String toString() {
        return super.toString() + ", Background image = " + this.backgroundImageUrl + ", layout = " + layout +
            "Children = " + components.toString();
    }

    /** Empty implementations */
    @Override
    public void addText(String text) { }
    @Override
    public void setNextView(String nextView) { }
    @Override
    public void setPreferredSize(int width, int height) { }
    @Override
    public void addPressedImageUrl(String pressedImage) { }
    @Override
    public void addFont(Font font) { }
}
