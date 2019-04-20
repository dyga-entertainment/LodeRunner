package MVC.Model;

import MVC.Model.Menu.ModelView;

import java.awt.*;
import java.util.Dictionary;
import java.util.Stack;

/**
 *  Sub-MVC.Model in charge of all the Menu.
 */
public class MenuModel {

    /**##############################**/

    /** Current display background image */
    private static String viewTitle;

    /** Current display background image */
    private static Image currentBackgroundImage;

    /** Should be fed by json file ? maybe ? */
    private static ModelView[] modelViews;

    /**##############################**/

    /** Current view name display to the player */
    private static ViewType currentView;

    /** Stack useful to comeback to previous views */
    private static Stack<ViewType> lastVisitedViews;

    private static String selectedWorld = "";

    public MenuModel() {
        this.currentView = ViewType.HomeMenu;
        this.lastVisitedViews = new Stack<ViewType>();
    }

    public Image getBackgroundImage() {
        return this.currentBackgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.currentBackgroundImage = backgroundImage;
    }

    public void ChangeView(ViewType newView, boolean isBackButton) {
        System.out.println("ICICICI" + newView + "" + isBackButton);
        //System.out.println("[Context Changement] from " + currentView.toString() + " to " + newView.toString());

        // Retrive possible parameters from the current view.
		/*Dictionary<String, Object> parameters = new Hashtable<>();
		if(this.views[currentView.ordinal()] != null) {
			parameters = this.views[currentView.ordinal()].getParameters();
		}*/

        if (!isBackButton) {
            this.lastVisitedViews.push(currentView);
        }
        this.currentView = newView;

        // Clean up everything
        //this.windowFrame.getContentPane().removeAll();

        // Prepare the next view to be printed
        //MVC.View nextView = getNextView();


        // Give the parameters to the next view if needed.
        //nextView.setParameters(parameters);

        // Setup the view to be display
        //nextView.setup(FenetrePrincipale.WINDOW_WIDTH, FenetrePrincipale.WINDOW_HEIGHT, FenetrePrincipale.CONTENT_PANEL_WIDTH,
        //	FenetrePrincipale.CONTENT_PANEL_HEIGHT);

        //
        // Could make a transition here ?
        //

        // Finally, Add the MainView to the windows
        //this.windowFrame.getContentPane().add(nextView);
        //this.windowFrame.repaint();
        //this.windowFrame.pack();
    }

    public void ReturnLastView() {
        ViewType view = null;
        if(!this.lastVisitedViews.isEmpty()) {
            view = this.lastVisitedViews.pop();
        }
        ChangeView(view, true);
    }

    public ViewType GetLastVisitedView() {
        ViewType view = null;
        if(!this.lastVisitedViews.isEmpty()){
            view = this.lastVisitedViews.peek();
        }
        return view;
    }

    public ViewType GetCurrentView() {
        return this.currentView;
    }

    public void Update(Dictionary<String, Object> parameters) {

        /*
        if() {
            // reset the transparency of all other buttons
            resetButtonsTransparancy();

            //StandardButton sourceButton = (StandardButton)e.getSource();
            //title.setText(sourceButton.getButtonName());
            if(parameters.get("viewTitle") != null) {
                this.viewTitle = parameters.get("viewTitle").toString();
            }

            // Update the list of parameters for the next view
            //parameters.put("worldName", sourceButton.getButtonName());
            if(parameters.get("worldName") != null) {
                this.selectedWorld = parameters.get("worldName").toString();
            }

            // Update the variable for the level screen
            //selectedWorld = sourceButton.getButtonName();

            // Change the background image to match the selected world
            backgroundImage = Images.getImageFromPath(jbutton.getActionCommand());

            // Change the transparency of the current button
            sourceButton.setTransparency(0.2f);
            repaint();
        }*/
    }
}
