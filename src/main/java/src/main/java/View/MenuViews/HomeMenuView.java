package View.MenuViews;

import Controler.ControleurJeu;
import Model.ViewType;
import View.ViewManager;
import Utils.helper.ResourcesPaths;
import Utils.WrapLayout;
import javax.swing.*;

public class HomeMenuView extends View {

    public HomeMenuView(ViewManager manager, ControleurJeu controler) {
        super(manager, ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_menu.png", controler);
    }

    @Override
    public void setupButtonsPanel() {
        super.setupButtonsPanel();

        this.buttonsPanel.removeAll();
        this.buttonsPanel.validate();

        JButton playButton = NewContextTransitionButton("Solo", "bouton_solo1.png", "bouton_solo2.png", ViewType.WorldSelection);
        JButton multiplayerButton = NewContextTransitionButton("Coop", "bouton_coop1.png", "bouton_coop2.png", ViewType.None, false, false);
        JButton settingsButton = NewContextTransitionButton("Options", "bouton_options1.png", "bouton_options2.png", ViewType.Settings);
        JButton editorButton = NewContextTransitionButton("Editeur", "bouton_editeur1.png", "bouton_editeur2.png", ViewType.None, false, false);
        JButton creditsButton = NewContextTransitionButton("Credits", "bouton_credits1.png", "bouton_credits2.png", ViewType.Credits);

        // Assign all the previously created buttons to the structure
        this.buttons = new JButton[] {
                playButton,
                multiplayerButton,
                settingsButton,
                editorButton,
                creditsButton,
        };

        //this.buttonsPanel.setLayout(new GridLayout(2,3,0,10));
        this.buttonsPanel.setLayout(new WrapLayout());
        for(int i = 0; i < this.buttons.length; i++) {
            System.out.println(this.buttons[i].getText());
            this.buttonsPanel.add(this.buttons[i]);
        }
    }
}
