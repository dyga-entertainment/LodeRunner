package MVC.Model.Menu;

import MVC.Model.Menu.Component.ModelComponent;
import MVC.Model.Menu.Component.ModelPanel;

public class ModelView {

    private String name;

    private ModelComponent viewContent;

    public ModelView() {
        this("", null);
    }

    public ModelView(String name) {
        this(name, null);
    }

    public ModelView(String name, ModelComponent viewContent) {
        this.name = name.toLowerCase();
        this.viewContent = viewContent;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return this.name;
    }

    public void addView(ModelComponent viewContent) {
        this.viewContent = viewContent;
    }

    public ModelPanel getModelComponent() {
        return (ModelPanel)this.viewContent;
    }
}
