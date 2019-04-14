package IHM.MenuViews;

import controleur.VueManager;
import helper.ResourcesPaths;
import utils.WrapLayout;

import javax.swing.*;

public class HomeMenuView extends View {

    public HomeMenuView(VueManager manager) {
        super(manager, ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_menu.png");
    }

    @Override
    public void setupButtonsPanel() {
        super.setupButtonsPanel();

        this.buttonsPanel.removeAll();
        this.buttonsPanel.validate();

        JButton playButton = NewContextTransitionButton("Solo", "bouton_solo1.png", "bouton_solo2.png", VueManager.ViewType.WorldSelection);
        JButton multiplayerButton = NewContextTransitionButton("Coop", "bouton_coop1.png", "bouton_coop2.png", VueManager.ViewType.None, false, false);
        JButton settingsButton = NewContextTransitionButton("Options", "bouton_options1.png", "bouton_options2.png", VueManager.ViewType.Settings);
        JButton editorButton = NewContextTransitionButton("Editeur", "bouton_editeur1.png", "bouton_editeur2.png", VueManager.ViewType.None, false, false);
        JButton creditsButton = NewContextTransitionButton("Credits", "bouton_credits1.png", "bouton_credits2.png", VueManager.ViewType.Credits);

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
