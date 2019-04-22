package Inputs;

import MVC.View.Menu.ViewButton;
import Main.Game;

public class ButtonsCallback {

    public ButtonsCallback(){
        System.out.println("This is a constructor");
    }

    public void dummy() {
        System.out.println("i'm a dummy");
    }

    public static void changeView(ViewButton sourceButton) {
        System.out.println("[Debug] React to button = " + sourceButton.getButtonName());

        switch (sourceButton.getButtonName()) {
            case "Solo":
                System.out.println("[Debug] ChangeView to WorldView");
                Game.changeView("WorldView");
                break;
            case "Back":
                System.out.println("[Debug] Back to the previous view");
                Game.backLastView();
            default:
                break;
        }
    }

    public static void onClick() {
        System.out.println("i'm a dummy");
    }
}
