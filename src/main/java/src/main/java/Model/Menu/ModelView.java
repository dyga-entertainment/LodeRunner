package Model.Menu;

import Model.Menu.Component.ModelPanel;

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
        this.name = name;
        this.viewContent = viewContent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addView(ModelComponent viewContent) {
        this.viewContent = viewContent;
    }

    public ModelPanel getModelComponent() {
        return (ModelPanel)this.viewContent;
    }
}
