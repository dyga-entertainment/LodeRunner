package MVC.Model.Menu.Component;

import MVC.Model.Menu.ModelComponent;
import MVC.Model.Menu.ModelLayout;

import java.util.ArrayList;
import java.util.List;

public class ModelPanel extends ModelComponent {

    private ModelLayout layout;
    private List<ModelComponent> components;

    // ♥
    public ModelPanel() {
        super();
        this.components = new ArrayList<>();
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

    public ModelLayout getLayout() {
        return this.layout;
    }

    public void add(ModelComponent component) {
        this.components.add(component);
    }

    public List<ModelComponent> getChildrenComponents() {
        return this.components;
    }

    @Override
    public void addActionListener(String actionName) {

    }

    @Override
    public String toString() {
        return super.toString() + ", Background image = " + this.backgroundImageUrl + ", layout = " + layout +
            "Children = " + components.toString();
    }
}
