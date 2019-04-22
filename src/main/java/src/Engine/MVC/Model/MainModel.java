package MVC.Model;

import MVC.Model.Menu.ModelView;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static Utils.MenuLoader.CreateNewView;

/**
 * Main MVC.Model - MODEL
 * Takes care of making an abstract representation of the game that the view will use.
 * It will be notify by the controler when changes has to be made.
 */
public class MainModel {

    /** Data structure holding all the possible views */
    private static Dictionary<String, ModelView> menuViews;

    /** Current view name display to the player */
    private static ModelView activeModelView;

    /** Stack useful to comeback to previous views */
    private static Stack<String> lastVisitedViews;

    /**
     * Load, create and store all the UI views needed to navigate through the menu.
     * Assign the first one to the current active view.
     * @param views List of resources url of all the UI views.
     */
    public MainModel(String[] views) {

        this.menuViews = new Hashtable<>();
        this.lastVisitedViews = new Stack<>();

        /*
        List<String> list = Arrays.asList(views);
        Collections.reverse(list);
        views = list.toArray(views);*/

        for (int i = 0; i < views.length; i++) {
            try {
                URL url = Thread.currentThread().getContextClassLoader().getResource(views[i]);
                FileReader fileReader = new FileReader(url.getPath());
                // Create the view
                ModelView view = CreateNewView(fileReader);
                String keyName = view.getName().toLowerCase();

                // Add the view to the data struct
                menuViews.put(keyName, view);

                // Set the default active modelView
                if(this.activeModelView == null) {
                    this.activeModelView = menuViews.get(keyName);
                }
            } catch (ParseException | IOException e) {
                System.out.println("Cannot load the following json file = " + views[i]);
                e.printStackTrace();
            }
        }
    }

    public ModelView getCurrentView() {
        return this.activeModelView;
    }

    /**
     * This method let you changed the current view to the one identify by his name
     * @param nextView the name of the view the model will switch to.
     */
    public void updateView(String nextView) {
        this.lastVisitedViews.push(this.activeModelView.getName());
        this.activeModelView = menuViews.get(nextView);
    }

    /**
     * This method return to the last visited view before the current one
     */
    public void returnLastView() {
        if(!this.lastVisitedViews.isEmpty()) {
            this.activeModelView = menuViews.get(this.lastVisitedViews.pop());
        }
    }

    // TODO
    public boolean isDirty() {
        return true;
    }
}
