package Inputs;

import MVC.View.Menu.ViewButton;
import Main.Game;

public class ButtonsCallback {

    public ButtonsCallback(){
        System.out.println("This is a constructor");
    }

    public void changeView(ViewButton sourceButton) {
        System.out.println("[Debug] React to button = " + sourceButton.getButtonName());
        System.out.println("[Debug] Next view " + sourceButton.getNextView());

        Game.changeView(sourceButton.getNextView());

        /*
        switch (sourceButton.getButtonName()) {
            // Wrong because we use button name... which sucks
            case "Solo":
                System.out.println("[Debug] ChangeView to WorldView");
                Game.changeView("worldView");
                break;
            case "Credits":
                Game.changeView("credits");
                break;
            default:
                break;
        }*/
    }

    public void back(ViewButton sourceButton) {
        System.out.println("[Debug] Back to the previous view");
        Game.backLastView();
    }

    public void changeWorld(ViewButton sourceButton) {
        System.out.println("[Debug] React to button = " + sourceButton.getButtonName());

        String buttonImageUrl = sourceButton.getImageUrl();
        Game.getCurrentView().getModelComponent().setBackgroundImageUrl(buttonImageUrl);
    }
}
