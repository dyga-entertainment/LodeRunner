package MVC.View.MenuViews;

import MVC.Controler.MainControler;

public class CreditView extends View {
	
	public CreditView(ViewManager vueManager, MainControler controler) {
		//super(vueManager, ResourcesPaths.SPRITE_UI_PATH + "credits.png", controler);
		super(vueManager, "credits.png", controler);
	}
}
