package Main;

import MVC.Controler.MainControler;
import MVC.Model.MainModel;
import MVC.Model.Menu.Component.ModelComponent;
import MVC.Model.Menu.Component.ModelPanel;
import MVC.Model.Menu.Enums.ModelLayout;
import MVC.Model.Menu.ModelView;
import MVC.Model.MenuModel;
import MVC.View.MainView;
import MVC.View.Menu.Component.ViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * This class will add all the initialization of the game to the coder.
 * It will instantiate the MVC.Model-MVC.View-MVC.Controler (MVC) pattern back-stage.
 * --
 * Those functions can be called by the programmer when building his game.
 */
public class Game {

    /** MVC.Model **/
    private static MainModel mainModel;
    /** MVC.View **/
    private static MainView mainView;
    /** MVC.Controler **/
    private static MainControler mainControler;

    /** Others **/
    private static String gameName;
    private static String[] views;

    public Game(String gameName) {
        gameName = gameName;
    }

    public void addJsonViews(String[] strings) {
        views = strings.clone();
    }

    public void Run() {
        // Init the modele of the game
        mainModel = new MainModel(views);

        // Init the controller of the game
        // Init the link Controller -> MVC.Model in order to update the model if an input has been made.
        mainControler = new MainControler(mainModel);

        // Add the link MVC.View -> Controller when the user use inputs.
        //this.addKeyListener(gameControler);
        //this.addMouseListener(gameControler);
        //this.requestFocus(); // Needed ?

        // Init the view of the game with the windows (??)
        // and the controller to be able to add him on buttons for example
        mainView = new MainView(gameName, mainModel, mainControler);

        // Add the link MVC.Controler -> MVC.View to notify the view an input as been made and that, maybe the model changed.
        mainControler.addView(mainView);

        mainView.show();
    }

    /** Method useful in order to help the programmer script his desire behavior */
    public static ModelView getCurrentView() {
        return mainModel.getCurrentView();
    }

    public static void setSelectedWorld(String selectedWorldName) {
        mainModel.setSelectedWorld(selectedWorldName);
    }

    public static void setSelectedLevel(String selectedLevelName) {
        mainModel.setSelectedLevel(selectedLevelName);
    }

    /**
     * Find all the panels that contains a specific layout.
     * @param layoutType the layout type we want to find
     * @return a list of panel with the layout layoutType, null if there is none.
     */
    public static List<ModelPanel> FindPanelByLayout(ModelLayout layoutType) {
        return FindPanelByLayout(layoutType, getCurrentView().getModelComponent().getChildrenComponents());
    }

    private static List<ModelPanel> FindPanelByLayout(ModelLayout layoutType, List<ModelComponent> components) {
        List<ModelPanel> panels = new LinkedList<>();

        for(ModelComponent currentComponent : components) {
            if (currentComponent instanceof ModelPanel) {
                // Cast
                ModelPanel currentModelPanel = (ModelPanel) currentComponent;
                if (currentModelPanel.getLayout().name == layoutType){
                    panels.add(currentModelPanel);
                }
                // See if it also contains cardlayout as children
                List<ModelPanel> childrenPanels = FindPanelByLayout(layoutType, currentModelPanel.getChildrenComponents());
                if(childrenPanels != null) {
                    panels.addAll(childrenPanels);
                }
            }
        }
        return panels.size() > 0 ? panels : null;
    }

    public static JComponent getViewComponentByUuid(UUID uuid) {
        return mainView.getViewComponentByUuid(uuid);
    }

    public static void forceViewRepaint() {
        mainView.paint();
    }

    /** Method that should be included in the controler
     * Should update the model and notify changes might happened **/
    public static void changeView(String nextView) {
        mainControler.changeView(nextView.toLowerCase());
    }

    public static void backLastView() {
        mainControler.returnLastView();
    }



}
