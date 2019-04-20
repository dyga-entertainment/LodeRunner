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

    //private ModelView[] menuViews;
    private static Dictionary<String, ModelView> menuViews;
    private static ModelView activeModelView;

    public MainModel(String[] views) {

        this.menuViews = new Hashtable<>();

        List<String> list = Arrays.asList(views);
        Collections.reverse(list);
        views = list.toArray(views);

        for (int i = 0; i < views.length; i++) {
            try {
                URL url = Thread.currentThread().getContextClassLoader().getResource(views[i]);
                FileReader fileReader = new FileReader(url.getPath());
                // Create the view
                ModelView view = CreateNewView(fileReader);
                // Add the view to the data struct
                menuViews.put(view.getName(), view);
            } catch (ParseException | IOException e) {
                System.out.println("Cannot load the following json file = " + views[i]);
                e.printStackTrace();
            }
        }

        // Set the default active modelView
        Enumeration<String> enumeration = menuViews.keys();
        if(enumeration.hasMoreElements()) {
            this.activeModelView = menuViews.get(enumeration.nextElement());
        }
    }

    public ModelView getCurrentView() {
        return this.activeModelView;
    }

    public void updateView(String nextView) {
        this.activeModelView = menuViews.get(nextView);
    }
}
