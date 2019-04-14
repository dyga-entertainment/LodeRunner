package IHM.MenuViews;

import controleur.VueManager;
import helper.ResourcesPaths;

public class CreditView extends View {
	
	public CreditView(VueManager vueManager) {
		super(vueManager, ResourcesPaths.SPRITE_UI_PATH + "credits.png");
	}
}
