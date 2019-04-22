package Main;

import MVC.Controler.MainControler;
import MVC.Model.MainModel;
import MVC.Model.Menu.ModelView;
import MVC.Model.MenuModel;
import MVC.View.MainView;

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

    /** Method that should be included in the controler
     * Should update the model and notify changes might happened **/
    public static void changeView(String nextView) {
        mainControler.changeView(nextView.toLowerCase());
    }

    public static void backLastView() {
        mainControler.returnLastView();
    }



}
