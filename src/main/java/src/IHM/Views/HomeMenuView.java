package IHM.Views;

import IHM.Buttons.ContextTransitionButton;
import controleur.VueManager;
import helper.Images;
import helper.ResourcesPaths;
import utils.WrapLayout;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomeMenuView extends View {

    public HomeMenuView(VueManager manager) {
        super(manager, ResourcesPaths.SPRITE_BACKGROUND_PATH + "background_menu.png");

        //this.add(this.contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void setupButtonsPanel() {
        super.setupButtonsPanel();

        JButton playButton = AddContextTransitionButton("Solo", "bouton_solo1.png", "bouton_solo2.png", VueManager.ViewType.WorldSelection);
        JButton multiplayerButton = AddContextTransitionButton("Coop", "bouton_coop1.png", "bouton_coop2.png", VueManager.ViewType.None, false);
        JButton settingsButton = AddContextTransitionButton("Options", "bouton_options1.png", "bouton_options2.png", VueManager.ViewType.Settings);
        JButton editorButton = AddContextTransitionButton("Editeur", "bouton_editeur1.png", "bouton_editeur2.png", VueManager.ViewType.None);
        JButton creditsButton = AddContextTransitionButton("Credits", "bouton_credits1.png", "bouton_credits2.png", VueManager.ViewType.Credits);

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

        this.buttonsPanel.setOpaque(true);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);
    }
}
