package View.MenuViews;

import Controler.ControleurJeu;
import View.ViewManager;
import Utils.helper.ResourcesPaths;

public class CreditView extends View {
	
	public CreditView(ViewManager vueManager, ControleurJeu controler) {
		super(vueManager, ResourcesPaths.SPRITE_UI_PATH + "credits.png", controler);
	}
}
