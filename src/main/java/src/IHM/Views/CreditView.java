package IHM.Views;

import controleur.VueManager;
import helper.Images;
import helper.ResourcesPaths;
import utils.WrapLayout;

import javax.swing.*;
import java.awt.*;

public class CreditView extends View {
	
	public CreditView(VueManager vueManager) {
		super(vueManager, ResourcesPaths.SPRITE_UI_PATH + "credits.png");

		this.add(this.contentPanel, BorderLayout.CENTER);
		this.add(this.buttonsPanel, BorderLayout.SOUTH);
	}

	@Override
	public void setupButtonsPanel() {
		super.setupButtonsPanel();

		JButton backButton = AddContextTransitionButton("Back", "bouton_retour1.png", "bouton_retour2.png", VueManager.ViewType.HomeMenu, true);
		this.buttonsPanel.setLayout(new WrapLayout());
		this.buttonsPanel.add(backButton);
	}
}
