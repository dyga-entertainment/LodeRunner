package View.MenuViews;

import Controler.VueManager;
import Utils.helper.ResourcesPaths;

public class CreditView extends View {
	
	public CreditView(VueManager vueManager) {
		super(vueManager, ResourcesPaths.SPRITE_UI_PATH + "credits.png");
	}
}
